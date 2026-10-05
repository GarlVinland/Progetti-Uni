
    --tabella Utente

CREATE TABLE UTENTE(
    id_utente SERIAL NOT NULL,
    nome VARCHAR(20) NOT NULL,
    cognome VARCHAR(20) NOT NULL,
    email VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(100) NOT NULL,
    sesso CHAR(1) NOT NULL CHECK (sesso IN ('M', 'F', 'O')), 			---oppure Sesso ENUM('M', 'F', 'O'),
    data_nascita DATE NOT NULL,
    lingua_preferita CHAR(3) NOT NULL,
    stile_comunicativo VARCHAR(10) CHECK (stile_comunicativo IN ('formale', 'informale')),
    data_creazione TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    data_ultimo_accesso TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	
    CONSTRAINT UTENTE_PK PRIMARY KEY (id_utente)
);


--TABELLA PROFILO_UTENTE
CREATE TABLE PROFILO_UTENTE(
id_profilo SERIAL NOT NULL,
id_utente INT NOT NULL UNIQUE,				----"unique" un utente può avere un solo profilo
preferenze_comunicative TEXT,              
stato_emotivo_prevalente TEXT,             
tratti_personalita TEXT,      

data_aggiornamento TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
CONSTRAINT PROFILO_UTENTE_PK PRIMARY KEY (id_profilo),
CONSTRAINT PROFILO_UTENTE_FK_UTENTE FOREIGN KEY (id_utente) REFERENCES UTENTE (id_utente) ON DELETE CASCADE
);

-- Tabella Emozione
CREATE TABLE EMOZIONE(
    id_emozione SERIAL,
    nome_emozione TEXT UNIQUE NOT NULL,
    descrizione TEXT,
    valore_intensità DECIMAL(3, 2) CHECK (valore_intensità >= 0.00 AND valore_intensità <= 1.00),
    categoria TEXT,

	constraint EMOZIONE_PK PRIMARY KEY(id_emozione) 
);



-- Tabella Risposta
CREATE TABLE RISPOSTA(
    id_risposta SERIAL NOT NULL,
    testo_risposta TEXT NOT NULL,
    id_emozione_associata INT NOT NULL,
    lingua VARCHAR(3) NOT NULL,
    tono_risposta VARCHAR(20) CHECK (tono_risposta IN ('empatico', 'incoraggiante', 'neutro')),
    contesto_applicabile TEXT,
    data_creazione TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    data_aggiornamento TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT RISPOSTA_PK PRIMARY KEY (id_risposta),
    CONSTRAINT RISPOSTA_FK_EMOZIONE FOREIGN KEY (id_emozione_associata) REFERENCES EMOZIONE (id_emozione)
);

--TABELLA INTERAZIONE 

CREATE TABLE INTERAZIONE(
    id_interazione SERIAL NOT NULL,
    id_utente INT NOT NULL,
    timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    input_utente TEXT NOT NULL,               
    tipo_input VARCHAR(10) NOT NULL CHECK (tipo_input IN ('testo', 'voce', 'immagine')),
    id_emozione_rilevata INT,                
    id_risposta_generata INT,                
    contesto_conversazione TEXT,             
    canale VARCHAR(50),            
	
    CONSTRAINT INTERAZIONE_PK PRIMARY KEY (id_interazione),
    CONSTRAINT INTERAZIONE_FK_UTENTE FOREIGN KEY (id_utente) REFERENCES UTENTE (id_utente) ON DELETE CASCADE,
    CONSTRAINT INTERAZIONE_FK_EMOZIONE FOREIGN KEY (id_emozione_rilevata) REFERENCES Emozione (id_emozione),
    CONSTRAINT INTERAZIONE_FK_RISPOSTA FOREIGN KEY (id_risposta_generata) REFERENCES Risposta (id_risposta)
);


-- Tabella Storico_Emotivo
CREATE TABLE STORICO_EMOTIVO(
    id_storico SERIAL NOT NULL,
    id_utente INT NOT NULL,
    id_emozione INT NOT NULL ,
    timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    intensità_rilevata DECIMAL(3, 2) DEFAULT 0.00 CHECK (intensità_rilevata >= 0.00 AND intensità_rilevata <= 1.00),

	constraint STORICO_EMOTIVO_PK PRIMARY KEY (id_storico),
	constraint STORICO_EMOTIVO_FK_UTENTE FOREIGN KEY(id_utente) REFERENCES UTENTE(id_utente) ON DELETE CASCADE,
	constraint STORICO_EMOTIVO_FK_EMOZIONE FOREIGN KEY(id_emozione) REFERENCES EMOZIONE(id_emozione) ON DELETE CASCADE
);

-- Tabella Feedback
CREATE TABLE FEEDBACK(
    id_feedback SERIAL NOT NULL,
    id_interazione INT NOT NULL,
    valutazione INT CHECK (valutazione BETWEEN 1 AND 10),
    commento TEXT,
    timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

	CONSTRAINT FEEDBACK_PK PRIMARY KEY(id_feedback),
	CONSTRAINT FEEDBACK_FK_INTERAZIONE FOREIGN KEY (id_interazione) REFERENCES INTERAZIONE(id_interazione) ON DELETE CASCADE
);

-- Tabella Sessione
CREATE TABLE SESSIONE (
    id_sessione SERIAL NOT NULL,
    id_utente INT NOT NULL,
    token_sessione CHAR(32) ,
    data_inizio TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    data_fine TIMESTAMP NOT NULL,

	CONSTRAINT SESSIONE_PK PRIMARY KEY(id_sessione),
	CONSTRAINT SESSIONE_FK_UTENTE FOREIGN KEY(id_utente) REFERENCES UTENTE(id_utente) ON DELETE CASCADE
);

-- Tabella Contenuto_Multimediale 
CREATE TABLE CONTENUTO_MULTIMEDIALE(
    id_contenuto SERIAL NOT NULL,
    id_interazione INT NOT NULL,
    tipo_contenuto VARCHAR(50) NOT NULL CHECK (tipo_contenuto IN('audio','video','immagine')),
    url_contenuto TEXT NOT NULL,
    descrizione TEXT NOT NULL,
    timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

	CONSTRAINT CONTENUTO_MULTIMEDIALE_PK PRIMARY KEY (id_contenuto),
	CONSTRAINT CONTENUTO_MULTIMEDIALE_FK_INTERAZIONE FOREIGN KEY(id_interazione) 
									                  REFERENCES INTERAZIONE(id_interazione) ON DELETE CASCADE
);

-- Tabella Notifica
CREATE TABLE NOTIFICA (
    id_notifica SERIAL NOT NULL,
    id_utente INT NOT NULL,
    titolo TEXT NOT NULL,
    messaggio TEXT NOT NULL,
    tipo TEXT NOT NULL CHECK (tipo IN('sistema','promozionale')) ,
    stato_lettura BOOLEAN DEFAULT FALSE,
    timestamp_invio TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

	CONSTRAINT NOTIFICA_PK PRIMARY KEY(id_notifica),
	CONSTRAINT NOTIFICA_FK_UTENTE FOREIGN KEY(id_utente) REFERENCES UTENTE(id_utente) ON DELETE CASCADE
);

-- Tabella Traduzione
CREATE TABLE TRADUZIONE (
    id_traduzione SERIAL NOT NULL,
    id_risposta INT NOT NULL,
    lingua CHAR(3) NOT NULL,
    testo_tradotto TEXT NOT NULL,
    data_creazione TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

	CONSTRAINT TRADUZIONE_PK PRIMARY KEY(id_traduzione),
	CONSTRAINT TRADUZIONE_FK_RISPOSTA FOREIGN KEY(id_risposta) REFERENCES RISPOSTA(id_risposta) ON DELETE CASCADE
);


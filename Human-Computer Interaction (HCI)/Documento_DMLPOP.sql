
-- Inserimento nella tabella UTENTE
INSERT INTO UTENTE (nome, cognome, email, password, sesso, data_nascita, lingua_preferita, stile_comunicativo) VALUES
('YASSINE', 'EL HOSAYNY', 'yassine@example.com', MD5('password123'), 'M', '2001-01-01', 'ITA', 'formale'),
('ALBERTO RODOLFO', 'CULLA', 'alberto@example.com', MD5('password456'), 'M', '2002-02-15', 'ITA', 'informale'),
('KEVIN', 'KOLAVERI', 'kevin@example.com', MD5('password789'), 'M', '2002-06-10', 'ITA', 'informale'),
('MARIA', 'ROSSI', 'maria@example.com', MD5('password1234'), 'F', '1980-12-20', 'ESP', 'formale'),
('ELENA', 'GARCIA', 'elena@example.com', MD5('password5678'), 'F', '1992-03-05', 'ENG', 'informale');

-- Inserimento nella tabella EMOZIONE
INSERT INTO EMOZIONE (nome_emozione, descrizione, valore_intensità, categoria) VALUES
('paura', 'Stato di allerta e apprensione', 0.80, 'negativa'),
('sorpresa', 'Reazione a un evento inatteso', 0.70, 'neutrale'),
('tristezza', 'Sensazione di perdita o sconforto', 0.60, 'negativa'),
('disgusto', 'Reazione di avversione', 0.50, 'negativa'),
('rabbia', 'Sensazione di frustrazione', 0.90, 'negativa'),
('aspettativa', 'Attesa positiva o negativa', 0.70, 'neutrale'),
('gioia', 'Sensazione di felicità', 0.95, 'positiva'),
('fiducia', 'Sentimento di sicurezza', 0.85, 'positiva');

-- Inserimento nella tabella PROFILO_UTENTE
INSERT INTO PROFILO_UTENTE (id_utente, preferenze_comunicative, stato_emotivo_prevalente, tratti_personalita) VALUES
(1, 'Tono empatico, preferenza per risposte concise', 'fiducia', 'introverso'),
(2, 'Risposte dettagliate, uso di ironia', 'gioia', 'estroverso'),
(3, 'Stile diretto, preferenza per risposte veloci', 'rabbia', 'introverso'),
(4, 'Tono rassicurante, preferenza per formalità', 'tristezza', 'introverso'),
(5, 'Uso di battute, preferenza per informalità', 'sorpresa', 'estroverso');

-- Inserimento nella tabella RISPOSTA
INSERT INTO RISPOSTA (testo_risposta, id_emozione_associata, lingua, tono_risposta, contesto_applicabile) VALUES
('Tutto andrà bene, sono qui per aiutarti.', 1, 'ITA', 'empatico', 'Stress'),
('Sembra interessante, dimmi di più!', 2, 'ENG', 'neutro', 'Curiosità'),
('Mi dispiace sentirlo, posso fare qualcosa?', 3, 'ESP', 'empatico', 'Perdita'),
('Non preoccuparti, ci sono soluzioni.', 4, 'ITA', 'empatico', 'Problemi'),
('Rimani calmo, possiamo affrontarlo insieme.', 5, 'ENG', 'incoraggiante', 'Frustrazione');

-- Traduzioni delle risposte
INSERT INTO TRADUZIONE (id_risposta, lingua, testo_tradotto) VALUES
(1, 'ENG', 'Everything will be fine, I am here to help you.'),
(1, 'ESP', 'Todo estará bien, estoy aquí para ayudarte.'),
(1, 'ARA', 'كل شيء سيكون على ما يرام، أنا هنا لمساعدتك.'),
(2, 'ENG', 'It sounds interesting, tell me more!'),
(2, 'ESP', '¡Suena interesante, cuéntame más!'),
(2, 'ARA', 'يبدو مثيرًا للاهتمام، أخبرني المزيد!'),
(3, 'ENG', 'I am sorry to hear that, can I do something?'),
(3, 'ESP', 'Lo siento mucho, ¿puedo hacer algo?'),
(3, 'ARA', 'أنا آسف لسماع ذلك، هل يمكنني فعل شيء؟'),
(4, 'ENG', 'Do not worry, there are solutions.'),
(4, 'ESP', 'No te preocupes, hay soluciones.'),
(4, 'ARA', 'لا تقلق، هناك حلول.'),
(5, 'ENG', 'Stay calm, we can face this together.'),
(5, 'ESP', 'Mantén la calma, podemos afrontarlo juntos.'),
(5, 'ARA', 'ابق هادئًا، يمكننا مواجهة ذلك معًا.');

-- Inserimento nella tabella INTERAZIONE
INSERT INTO INTERAZIONE (id_utente, input_utente, tipo_input, id_emozione_rilevata, id_risposta_generata, contesto_conversazione, canale) VALUES
(1, 'Mi sento stressato oggi.', 'testo', 1, 1, 'Lavoro', 'chatbot'),
(2, 'Wow, non me lo aspettavo!', 'voce', 2, 2, 'Evento', 'assistente vocale'),
(3, 'Non mi piace questa situazione.', 'testo', 5, 5, 'Discussione', 'chatbot'),
(4, 'Sono molto triste oggi.', 'testo', 3, 3, 'Problemi personali', 'chatbot'),
(5, 'Questo mi sorprende davvero.', 'voce', 2, 2, 'Notizia', 'assistente vocale');

-- Inserimento nella tabella STORICO_EMOTIVO
INSERT INTO STORICO_EMOTIVO (id_utente, id_emozione, intensità_rilevata) VALUES
(1, 1, 0.85),
(2, 2, 0.75),
(3, 5, 0.90),
(4, 3, 0.65),
(5, 2, 0.70);

-- Inserimento nella tabella FEEDBACK
INSERT INTO FEEDBACK (id_interazione, valutazione, commento) VALUES
(1, 9, 'Molto utile, grazie!'),
(2, 8, 'Non male, ma potrebbe migliorare.'),
(3, 6, 'Risposta ok, ma non completa.'),
(4, 10, 'Perfetto, mi sento meglio.'),
(5, 7, 'Interessante, ma voglio più dettagli.');

-- Inserimento nella tabella SESSIONE
INSERT INTO SESSIONE (id_utente, token_sessione, data_fine) VALUES
(1, '30L30LEF9124537890ABCDEF91675670', '2024-08-01 03:58:00'),
(2, '30L30LL1234567890LOOOOP123456789', '2024-09-02 13:19:05'),
(3, '30L30L1234732540TRUE301234567894', '2023-08-03 12:53:09'),
(4, 'OKOKWX1AAAAAAAAASTUVWX1234567898', '2021-12-04 21:05:59'),
(5, 'OKOKCD1BBBBBBBBBYZABCD1234567897', '2020-01-05 09:31:19');

-- Inserimento nella tabella CONTENUTO_MULTIMEDIALE
INSERT INTO CONTENUTO_MULTIMEDIALE (id_interazione, tipo_contenuto, url_contenuto, descrizione) VALUES
(1, 'audio', 'https://example.com/audio1.mp3', 'Messaggio vocale di supporto per ridurre lo stress'),
(2, 'video', 'https://example.com/video1.mp4', 'Video motivazionale per incoraggiare nuove esperienze'),
(3, 'immagine', 'https://example.com/image1.png', 'Immagine rappresentativa dello stato emotivo'),
(4, 'audio', 'https://example.com/audio2.mp3', 'Audio rassicurante per affrontare situazioni difficili'),
(5, 'video', 'https://example.com/video2.mp4', 'Tutorial video su come gestire emozioni negative');

-- Inserimento nella tabella NOTIFICA
INSERT INTO NOTIFICA (id_utente, titolo, messaggio, tipo) VALUES
(1, 'Benvenuto!', 'Grazie per esserti registrato al nostro servizio.', 'sistema'),
(2, 'Aggiornamento disponibile', 'Scopri le nuove funzionalità della piattaforma.', 'promozionale'),
(3, 'Promozione speciale', 'Ottieni uno sconto del 20% sui nostri servizi premium.', 'promozionale'),
(4, 'Attenzione alla sicurezza', 'Aggiorna la tua password per garantire maggiore protezione.', 'sistema'),
(5, 'Suggerimento', 'Scopri come personalizzare le tue risposte con il nostro assistente.', 'promozionale');





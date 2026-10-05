


----1/registrazione di un nuovo utente
INSERT INTO UTENTE (nome, cognome, email, password, sesso, data_nascita, lingua_preferita, stile_comunicativo)
VALUES ('MAURI', 'FEDRICO', 'mauri@gmail.com', MD5('securepass'), 'F', '1995-07-22', 'ITA', 'informale');

--2 aautenticazione e gestione della sessione utente +Verifica delle credenziali utente 
SELECT email, password, MD5('securepass') = password AS verifica
FROM UTENTE
WHERE email = 'mauri@gmail.com';

--- creazione di una nuova sessione
INSERT INTO SESSIONE (id_utente, token_sessione, data_fine)
 VALUES (6, 'LDICJF1234567890OKOKOK1234567890', '2023-06-30 16:19:00');

----- 3// registrazione di una nuova interazione tra utente e robot
INSERT INTO INTERAZIONE (id_utente, input_utente, tipo_input, id_emozione_rilevata, id_risposta_generata, contesto_conversazione, canale)
VALUES (6, 'Sono molto felice oggi!', 'testo', 7, 1, 'Conversazione casuale', 'chatbot');

--- 4/ilevazione e memorizzazione delle emozioni durante l’interazione
INSERT INTO STORICO_EMOTIVO (id_utente, id_emozione, intensità_rilevata)
VALUES (6, 7, 0.90);

---5/ Recupero dello storico delle interazioni di un utente
SELECT * FROM INTERAZIONE WHERE id_utente = 6;

-- Recupero delle interazioni con un'emozione specifica
SELECT * FROM INTERAZIONE 
WHERE id_utente = 6 AND id_emozione_rilevata = (SELECT id_emozione FROM EMOZIONE WHERE nome_emozione = 'gioia');

--- 6/ Calcolo della media della valutazione dei feedback
SELECT AVG(valutazione) AS media_feedback FROM FEEDBACK;

--7// Contare il numero di interazioni per ogni emozione riconosciuta dal sistema
SELECT E.nome_emozione, COUNT(*) AS numero_interazioni
FROM INTERAZIONE I
JOIN EMOZIONE E ON I.id_emozione_rilevata = E.id_emozione
GROUP BY E.nome_emozione
ORDER BY numero_interazioni DESC;

----8/ Visualizzare le interazioni di un utente in un intervallo di tempo
SELECT * FROM INTERAZIONE 
WHERE id_utente = (SELECT id_utente FROM UTENTE WHERE nome = 'YASSINE' AND cognome = 'EL HOSAYNY')
AND timestamp BETWEEN '2021-01-08' AND '2025-01-11';

----9/ Visualizzare le interazioni di tutti gli utenti "tristi" ordinate per data decrescente
SELECT I.* FROM INTERAZIONE I
JOIN STORICO_EMOTIVO S ON I.id_utente = S.id_utente
WHERE S.id_emozione = (SELECT id_emozione FROM EMOZIONE WHERE nome_emozione = 'tristezza')
ORDER BY I.timestamp DESC;

---10/ aggiornare il profilo di un utente
UPDATE PROFILO_UTENTE
SET preferenze_comunicative = 'Risposte dettagliate e approfondite',
    stato_emotivo_prevalente = 'fiducia',
    tratti_personalita = 'riflessivo'
WHERE id_utente = 5;

---11/ agiornare l'ultima data di accesso di un utente
UPDATE UTENTE
SET data_ultimo_accesso = CURRENT_TIMESTAMP
WHERE id_utente = 3;

--12/ eliminare una sessione utente scaduta
DELETE FROM SESSIONE WHERE data_fine < CURRENT_TIMESTAMP;

--13/ Eliminare un utente e tutti i suoi dati (grazie a ON DELETE CASCADE)
DELETE FROM UTENTE WHERE id_utente = 6;

--14/ visualizzare il numero di notifiche non lette per ciascun utente
SELECT id_utente, COUNT(*) AS notifiche_non_lette
FROM NOTIFICA
WHERE stato_lettura = FALSE
GROUP BY id_utente;

--15/ Selezionare le risposte più utilizzate nelle interazioni
SELECT R.testo_risposta, COUNT(*) AS frequenza
FROM INTERAZIONE I
JOIN RISPOSTA R ON I.id_risposta_generata = R.id_risposta
GROUP BY R.testo_risposta
ORDER BY frequenza DESC;

PRAGMA foreign_keys=OFF;
BEGIN TRANSACTION;

--utente
CREATE TABLE utente (
  email TEXT PRIMARY KEY,
  nome TEXT NOT NULL,
  password TEXT NOT NULL, 
  password_hash TEXT, 
  password_salt TEXT, 
  id INTEGER
);
  INSERT INTO utente VALUES('y@gmail.com','yasssine','','9zudDYFv15l+Dpt35C/X1HXcfGrsFBF2rOBKaiU1rMw=','GRI1eETofbpXMC6RanFwqg==',2);
  INSERT INTO utente VALUES('k@gmail.com','kevin','','NLqOuR+59VwWcoI+qvwLF+OUmrXRNsO1eVebAPhO8Ic=','yJAzDKhYXHC8nUk8Kj/FNg==',3);
  INSERT INTO utente VALUES('a@gmail.com','alberto','','RAYmHRIsl+v7BuphhhiZ1qolQhXXrOEt7JfyscM2/JA=','2WF8syBbTchhjSmFODLz1Q==',4);
  INSERT INTO utente VALUES('s@gmail.com','Serena','','ZgDEozVwgdLsiR9/x3AtRPx5VaU6bf5kR+DY5LqZojc=','3fpXiXltQbJo1ivLdqQ18w==',5);
  INSERT INTO utente VALUES('abc@gmail.com','mpc','','RY+BeRxklch4Bsrz8p6iyH9XTb8vHCJaVAAGmD/iE8o=','L6lij2ZUAaUsimEOb2cH0Q==',6);
  INSERT INTO utente VALUES('utentex@gmail.com','c@gmail.com','','9vQTrrwY5ANv8qQuyT0IM5PMT1+34FmZ0hmP8S38DXs=','jjgxURMykNbb6uCVoCYAsQ==',7);

  --attivita
  CREATE TABLE attivita ( 
  id INTEGER PRIMARY KEY AUTOINCREMENT,
  descrizione TEXT NOT NULL,
  tipo TEXT NOT NULL, data_inizio TEXT NOT NULL, 
  data_fine TEXT NOT NULL, 
  data_notifica TEXT NOT NULL, 
  priorita INTEGER NOT NULL, 
  attivita_privata INTEGER NOT NULL, 
  context TEXT, 
  utente_email TEXT NOT NULL, 
  utente_id INTEGER, 
  notificata INTEGER DEFAULT 0, 
  completato INTEGER DEFAULT 0, 
  FOREIGN KEY (utente_email) REFERENCES utente(email)
  );
  INSERT INTO attivita VALUES(73,'pulizia cucina','DOMESTICA','2026-02-09T09:00','2026-02-09T10:00','2026-01-26T11:16',2,0,'cucina','y@gmail.com',2,1,0);
  INSERT INTO attivita VALUES(74,'spesa mercato','SPESA','2026-02-10T15:30','2026-02-10T16:10','2026-02-10T14:30',1,0,'mercato','y@gmail.com',2,0,0);
  INSERT INTO attivita VALUES(75,'studio algoritmi','STUDIO','2026-02-20T14:00','2026-02-20T16:00','2026-02-19T20:00',3,1,'algoritmi','y@gmail.com',2,0,0);
  INSERT INTO attivita VALUES(76,'pulizia soggiorno','DOMESTICA','2026-02-21T09:30','2026-02-21T10:30','2026-02-20T19:00',2,0,'soggiorno','y@gmail.com',2,0,0);
  INSERT INTO attivita VALUES(77,'spesa veloce','SPESA','2026-02-22T18:00','2026-02-22T18:30','2026-02-22T17:00',1,0,'mini market','y@gmail.com',2,0,0);
  INSERT INTO attivita VALUES(78,'report finale','STUDIO','2026-02-23T10:00','2026-02-23T11:30','2026-02-22T18:00',3,0,'capitolo conclusivo','y@gmail.com',2,0,0);
  INSERT INTO attivita VALUES(79,'spesa bio','SPESA','2026-02-24T17:00','2026-02-24T17:40','2026-02-24T16:00',1,0,'market bio','y@gmail.com',2,0,0);
  INSERT INTO attivita VALUES(80,'sistemare balcone','DOMESTICA','2026-02-25T09:00','2026-02-25T10:00','2026-02-24T19:00',2,1,'balcone','y@gmail.com',2,0,0);
  INSERT INTO attivita VALUES(81,'riordino garage','DOMESTICA','2026-02-18T16:00','2026-02-18T18:00','2026-02-17T18:00',2,0,'garage','k@gmail.com',3,0,0);
  INSERT INTO attivita VALUES(82,'spesa settimanale','SPESA','2026-02-19T11:00','2026-02-19T11:45','2026-02-18T18:00',1,0,'supermercato','k@gmail.com',3,0,0);
  INSERT INTO attivita VALUES(83,'studio data mining','STUDIO','2026-02-20T09:00','2026-02-20T11:00','2026-02-19T19:00',3,1,'capitolo 5','k@gmail.com',3,0,0);
  INSERT INTO attivita VALUES(84,'lavanderia','DOMESTICA','2026-02-21T08:30','2026-02-21T09:30','2026-02-20T19:30',2,1,'biancheria','k@gmail.com',3,0,0);
  INSERT INTO attivita VALUES(85,'pagare bollette','DOMESTICA','2026-02-24T11:00','2026-02-24T11:20','2026-02-23T18:00',2,0,'online','k@gmail.com',3,0,0);
  INSERT INTO attivita VALUES(86,'riepilogo sprint','STUDIO','2026-02-23T09:30','2026-02-23T10:30','2026-02-22T18:30',2,0,'team room','k@gmail.com',3,0,1);
  INSERT INTO attivita VALUES(87,'spesa detersivi','SPESA','2026-02-24T18:00','2026-02-24T18:30','2026-02-24T17:00',1,0,'detersivi','k@gmail.com',3,0,0);
  INSERT INTO attivita VALUES(88,'riordino libri','DOMESTICA','2026-02-25T11:00','2026-02-25T12:00','2026-02-24T20:00',2,1,'libreria','k@gmail.com',3,0,1);
  INSERT INTO attivita VALUES(89,'studio esame reti','STUDIO','2026-02-12T18:00','2026-02-12T20:00','2026-02-12T17:00',3,1,'reti','a@gmail.com',4,0,0);
  INSERT INTO attivita VALUES(90,'spesa casa','SPESA','2026-02-11T12:00','2026-02-11T13:00','2026-02-11T11:00',1,0,'discount','a@gmail.com',4,0,0);
  INSERT INTO attivita VALUES(91,'meeting progetto','STUDIO','2026-02-23T10:00','2026-02-23T11:00','2026-02-22T09:00',2,0,'aula 3','a@gmail.com',4,0,0);
  INSERT INTO attivita VALUES(92,'ripasso inglese','STUDIO','2026-02-25T15:00','2026-02-25T16:30','2026-02-24T19:00',3,1,'b2','a@gmail.com',4,0,0);
  INSERT INTO attivita VALUES(93,'cucina meal prep','DOMESTICA','2026-02-18T11:00','2026-02-18T12:30','2026-02-17T20:00',2,1,'cucina','a@gmail.com',4,0,0);
  INSERT INTO attivita VALUES(94,'studio statistica','STUDIO','2026-02-20T15:00','2026-02-20T17:00','2026-02-19T20:00',3,1,'capitolo 3','a@gmail.com',4,0,1);
  INSERT INTO attivita VALUES(95,'spesa frutta','SPESA','2026-02-22T12:30','2026-02-22T13:00','2026-02-22T11:30',1,0,'fruttivendolo','a@gmail.com',4,0,0);
  INSERT INTO attivita VALUES(96,'pulizia bagno','DOMESTICA','2026-02-24T08:30','2026-02-24T09:15','2026-02-23T19:30',2,1,'bagno','a@gmail.com',4,0,1);
  INSERT INTO attivita VALUES(174,'andare a palestra','DOMESTICA','2026-02-02T17:30','2026-02-02T20:00','2026-02-02T17:15',2,0,'non disturbati mi','y@gmail.com',2,1,1);
  INSERT INTO attivita VALUES(305,'studio flt','STUDIO','2026-02-04T08:10','2026-02-04T10:10','2026-02-04T08:00',1,0,'università','a@gmail.com',4,1,0);
  INSERT INTO attivita VALUES(306,'pulire stanza','DOMESTICA','2026-02-11T10:00','2026-02-11T10:30','2026-02-11T09:50',1,0,NULL,'a@gmail.com',4,0,0);
  INSERT INTO attivita VALUES(307,'studiare cyber','STUDIO','2026-02-03T14:00','2026-02-03T16:00','2026-02-03T13:50',1,0,NULL,'k@gmail.com',3,1,0);
  INSERT INTO attivita VALUES(328,'progetto reti m','STUDIO','2026-02-06T23:00','2026-02-06T23:59','2026-02-06T22:50',1,1,NULL,'y@gmail.com',2,1,1);
  INSERT INTO attivita VALUES(329,'visitare famiglia','DOMESTICA','2026-02-08T10:10','2026-02-08T12:00','2026-02-08T10:00',1,1,NULL,'utentex@gmail.com',7,0,0);
  INSERT INTO attivita VALUES(330,'esami','STUDIO','2026-02-13T00:00','2026-02-18T00:00','2026-02-12T23:50',1,1,NULL,'y@gmail.com',2,0,0);
  DELETE FROM sqlite_sequence;
  INSERT INTO sqlite_sequence VALUES('attivita',330);
  COMMIT;

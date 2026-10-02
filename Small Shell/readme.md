# Small Shell (Smallsh) - Lab Modifications: Background Execution

Questo repository contiene la soluzione e la documentazione per la modifica dell'esempio **Small Shell** (`Lab4/smallsh`), focalizzata sull'introduzione e la gestione dei comandi in esecuzione in background.

## Obiettivi e Requisiti

Le richieste principali affrontate in questa modifica del codice della shell sono le seguenti:

1. **Esecuzione in Background Iniziale:** 
   - Modificare temporaneamente il comportamento della shell affinché tutti i comandi vengano lanciati in background (senza attendere la terminazione del processo figlio).
2. **Gestione del simbolo `&`:** 
   - Affinare la logica affinché **solo** in presenza del carattere `&` alla fine (o all'interno) del comando, questo venga eseguito in background. Se il simbolo non è presente, la shell deve comportarsi normalmente in foreground (attendendo la terminazione).
3. **Sequenze miste Background / Foreground:** 
   - Assicurarsi che venga gestita correttamente l'esecuzione di un comando lanciato in background seguito immediatamente da un comando in foreground, evitando conflitti di sincronizzazione o attese indesiderate.

## Dettagli della Soluzione

- **Parsing dei comandi:** Il parser della shell è stato adattato per riconoscere la presenza dell'operatore `&`.
- **Chiamate di sistema:** Utilizzo di `fork()` e gestione del flag di attesa (`waitpid`) condizionato dalla presenza del simbolo di background.
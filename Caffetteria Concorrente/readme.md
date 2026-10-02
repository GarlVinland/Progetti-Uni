# Problema della Caffetteria Concorrente

In un bar, due baristi altamente specializzati lavorano in modo indipendente: uno si dedica esclusivamente a preparare espresso e l'altro cappuccino[cite: 2]. Entrambi condividono una macchina da caffè dotata di due moduli identici[cite: 2]. Ogni modulo può preparare sia espresso che cappuccino[cite: 2]. I clienti arrivano in modo casuale al bar e richiedono uno dei due tipi di bevanda[cite: 2].

Progettare un algoritmo che coordini le azioni dei baristi e dei clienti in modo che[cite: 2]:

### I baristi
* Entrano in un ciclo infinito, controllano se c'è un ordine in coda per la loro specialità[cite: 2].
* Se c'è un ordine e la macchina è libera, preparano la loro specialità (espresso o cappuccino) e liberano il modulo della macchina[cite: 2].
* Se non c'è un ordine oppure la macchina non è libera, aspettando il loro turno per utilizzare il modulo corrispondente della macchina da caffè[cite: 2].

### I clienti
* Effettuano un ordine specifico (espresso o cappuccino)[cite: 2].
* Aspettano che il loro ordine sia pronto[cite: 2].
* Una volta pronto, ritirano la loro bevanda[cite: 2].

### La macchina da caffè
* Ha due moduli identici, ognuno capace di preparare entrambi i tipi di bevande[cite: 2].
* Ogni modulo può essere utilizzato da un solo barista alla volta[cite: 2].

## Ulteriori considerazioni

* **Coda di ordini**: Se la macchina è occupata all'arrivo di un cliente, il suo ordine viene aggiunto alla coda[cite: 2]. Il barista prenderà l'ordine successivo dalla coda quando la macchina sarà libera[cite: 2].
* **Ordini**: il codice aggiunto (`Esercizio_caffe.c`) include una funzione progettata per simulare l'arrivo di ordini di caffè da parte dei clienti, con tipologia di bevanda e tempistica di arrivo generati in modo casuale[cite: 2].
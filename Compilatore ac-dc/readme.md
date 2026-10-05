# Compilatore da ac a dc (Java)

Progetto in Java di un compilatore dal linguaggio `ac` al linguaggio `dc`.

## Architettura del Compilatore

Il progetto è diviso in:

* **Analisi Lessicale**: per il linguaggio `ac`.
* **Analisi Sintattica e costruzione dell'AST**: per costruire l'Abstract Syntax Tree del codice sorgente.
* **Analisi Semantica (Type Checking)**: eseguita a partire dall'AST del programma.
* **Generazione del Codice target**: per tradurre l'AST in codice `dc` valido.

## Il Linguaggio Sorgente: `ac`

Il linguaggio `ac` è un linguaggio semplice con le seguenti caratteristiche:

* **Tipi di dato**: Supporta 2 tipi di dato: interi e floating point.
* **Letterali**: 
  * Un intero è formato da una sequenza di cifre. 
  * Un floating point è una sequenza di almeno una cifra, seguita da un punto (".") e da un massimo di 5 cifre.
* **Variabili**: Sono stringhe che iniziano con una lettera e possono contenere solo lettere minuscole dell'alfabeto inglese e cifre. 
* **Dichiarazioni**: Ogni variabile deve essere dichiarata specificando `float` o `int` prima di essere usata, con la possibilità di assegnare un'inizializzazione opzionale.
* **Espressioni e Operatori**: Possono includere letterali, variabili o operatori binari (`+`, `-`, `*`, `/`).
* **Regole di Tipo**: Gli operandi all'interno di una espressione binaria devono essere dello stesso tipo. Un tipo `int` può essere convertito automaticamente in `float` se necessario, ma non sono consentite altre conversioni.
* **Istruzioni**: 
  * **Assegnamento**: Sintassi supportata `variabile = espressione` oppure `variabile op= espressione`.
  * **Stampa**: Sintassi `print variabile`.

## Il Linguaggio Target: `dc`

L'output del compilatore è scritto nel linguaggio `dc`:

* `dc` è un calcolatore "stack based" (che utilizza la notazione polacca inversa).
* Rappresenta una delle primissime applicazioni scritte per i sistemi Unix.
* Gli operatori principali sono `+`, `-`, `*` e `/`.
* La precisione (numero di cifre decimali) è configurabile; tuttavia, il valore di default è 0 (ad esempio, `5 4 /` restituisce `0` di default).

## Esempio di Input/Output del Compilatore

**Programma Sorgente (ac):**
```ac
int tempa;
tempa = 5;
float tempb = tempa / 3.2;
tempb += 7;
print tempb;

**Output (dc):**
5 sa 0 k
la 5 k 3.2 / sb 0 k
lb 7 5 k + sb 0 k
lb p P
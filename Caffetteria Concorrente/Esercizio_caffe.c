#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>
#include <unistd.h>
#include <semaphore.h>
#include <pthread.h>

#define MAX 100

// Struttura per rappresentare un ordine
struct Ordine {
    char tipo_caffe[20]; // Tipo di caffè (espresso o cappuccino)
    int id_cliente;       // Identificativo univoco del cliente
};

sem_t sem_macchina;                 //singola macchina da 2 moduli
sem_t sem_espresso, sem_cappuccino; // semafori per code

struct Ordine coda_espresso[MAX];
int head_e = 0, tail_e = 0;

struct Ordine coda_cappuccino[MAX];
int head_c = 0, tail_c = 0;

pthread_mutex_t mutex_coda_e;
pthread_mutex_t mutex_coda_c;
pthread_mutex_t mutex_print;

// Funzione per generare un nuovo ordine
void generaOrdine(struct Ordine *ordine) {

    static int id_cliente_attuale = 1; // Contatore per gli ID dei clienti



    // Genera un tipo di caffè a caso
    int tipo = rand() % 2;
    strcpy(ordine->tipo_caffe, (tipo == 0) ? "espresso" : "cappuccino");

    // Assegna l'ID cliente
    ordine->id_cliente = id_cliente_attuale++;

    // Simula l'intervallo di tempo tra un cliente e l'altro (in secondi)
    int tempo_attesa = rand() % 5 + 1; // Tra 1 e 5 secondi
    sleep(tempo_attesa);
}

void* preparaEspresso(void *arg) {
    while(1){

        sem_wait(&sem_espresso);


        pthread_mutex_lock(&mutex_coda_e);
        struct Ordine ordine = coda_espresso[head_e];
        head_e = (head_e + 1 ) % MAX;
        pthread_mutex_unlock(&mutex_coda_e);

        sem_wait(&sem_macchina);

        pthread_mutex_lock(&mutex_print);
        printf("\t-Preparazione %s per il cliente %d\n", ordine.tipo_caffe, ordine.id_cliente);
        pthread_mutex_unlock(&mutex_print);

        sleep(5);

        sem_post(&sem_macchina);

        pthread_mutex_lock(&mutex_print);
        printf("\t\t%s pronto per il cliente %d\n", ordine.tipo_caffe, ordine.id_cliente);
        pthread_mutex_unlock(&mutex_print);

    }

    return NULL;
}

void* preparaCappuccino(void *arg) {

    while(1){

        sem_wait(&sem_cappuccino);


        pthread_mutex_lock(&mutex_coda_c);
        struct Ordine ordine = coda_cappuccino[head_c];
        head_c = (head_c + 1 ) % MAX;
        pthread_mutex_unlock(&mutex_coda_c);

        sem_wait(&sem_macchina);

        pthread_mutex_lock(&mutex_print);
        printf("\t-Preparazione %s per il cliente %d\n", ordine.tipo_caffe, ordine.id_cliente);
        pthread_mutex_unlock(&mutex_print);

        sleep(5);

        sem_post(&sem_macchina);

        pthread_mutex_lock(&mutex_print);
        printf("\t\t%s pronto per il cliente %d\n", ordine.tipo_caffe, ordine.id_cliente);
        pthread_mutex_unlock(&mutex_print);

    }

    return NULL;
}

int main() {

    // Inizializza il generatore di numeri casuali
    srand(time(NULL));

    sem_init(&sem_macchina, 0, 2);
    sem_init(&sem_espresso, 0, 0);
    sem_init(&sem_cappuccino, 0, 0);

    pthread_mutex_init(&mutex_coda_e, NULL);
    pthread_mutex_init(&mutex_coda_c, NULL);
    pthread_mutex_init(&mutex_print, NULL);

    pthread_t thread_barista_e, thread_barista_c;
    pthread_create(&thread_barista_e, NULL, preparaEspresso, NULL);
    pthread_create(&thread_barista_c, NULL, preparaCappuccino, NULL);

    printf("\n=== La Caffetteria Inizia ===\n\n");

    while (1) {
        struct Ordine nuovoOrdine;
        generaOrdine(&nuovoOrdine);

        printf("\n>È stato generato un ordine di %s per il cliente %d.\n", nuovoOrdine.tipo_caffe, nuovoOrdine.id_cliente);

        // thread per preparare il caffè espresso
        if (strcmp(nuovoOrdine.tipo_caffe, "espresso") == 0) {
            pthread_mutex_lock(&mutex_coda_e);
            coda_espresso[tail_e] = nuovoOrdine;
            tail_e = (tail_e + 1) % MAX;
            pthread_mutex_unlock(&mutex_coda_e);

            sem_post(&sem_espresso);

        }

        // thread per preparare il cappuccino
        if (strcmp(nuovoOrdine.tipo_caffe, "cappuccino") == 0) {
            pthread_mutex_lock(&mutex_coda_c);
            coda_cappuccino[tail_c] = nuovoOrdine;
            tail_c = (tail_c + 1) % MAX;
            pthread_mutex_unlock(&mutex_coda_c);

            sem_post(&sem_cappuccino);
        }

    }




    return 0;
}

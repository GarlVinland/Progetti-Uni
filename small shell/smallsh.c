//creazione processo (modalità), lanciare execvp
#include "smallsh.h"
#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <string.h>

char *prompt = "Scrivere un comando>";  //stringa stampata ogni volta al posto del pompt

void procline(void) 	/* tratta una riga di input */
{
  char *arg[MAXARG+1];	/* array di puntatori per runcommand */
  int toktype;  	/* tipo del simbolo nel comando */
  int narg;		/* numero di argomenti considerati finora, indice per il vettore */
  int background;

  narg=0;
  background=0;

  do {

    /* mette un simbolo in arg[narg] 
       ed esegue un'azione a seconda del tipo di simbolo */
	
    switch (toktype = gettok(&arg[narg])) { //gettok prende da inizio linea(inpbuf), riconosce il carattere e lo inserisce nel vettore
	
      case ARG:   
        /* se argomento: passa al prossimo simbolo */
        if (narg < MAXARG) narg++;
      break;


      case EOL:
      case SEMICOLON:
         /* se fine riga o ';' esegue il comando ora contenuto in arg, mettendo NULL per indicare la fine degli argomenti:
         serve a execvp */
        if (narg != 0) {
          arg[narg] = NULL;
          runcommand(arg, background);

          }
        /* se non fine riga (descrizione comando finisce con ';')
           bisogna ricominciare a riempire arg dall'indice 0 */

        if (toktype != EOL)  narg = 0; 
      break;

      case AMPERSAND:

        background=1;
        if(narg!=0)
        {
          arg[narg]=NULL;
          runcommand(arg, background);
        }
        if (toktype != EOL)  narg = 0;
        background=0;

      break;


     }

  }while (toktype != EOL);  /* fine riga, procline finita */

}

void runcommand(char **cline, int backg)	/* esegue un comando */
{
  pid_t pid;
  int exitstat,ret;

  pid = fork();
  if (pid == (pid_t) -1) {
     perror("smallsh: fork fallita");
     return;
  }

  if (pid == (pid_t) 0) { 	/* processo figlio */

    /* esegue il comando il cui nome e' il primo elemento di cline,
       passando cline come vettore di argomenti */
    execvp(*cline,cline);//primo elemento è il percorso eseguibile, il secondo elemento sono gli argomenti compreso l'eseguibile
    perror(*cline);
    exit(1);
  }

  /* non serve "else"... ma bisogna aver capito perche' :-)  */
  //else non si fa perchè c'è il exit(1) nel figlio
 
  /* qui aspetta sempre e comunque - i comandi in background 
     richiederebbero un trattamento diverso */

  //il padre fa la wait del figlio, ne lancia uno solo quindi non deve specificare quale figlio
  if(backg==0)
  {
    ret = waitpid(pid, &exitstat, 0); // modifica per vedere la terminazione di sleep

    if (ret == -1)
      perror("wait");

  }
  else
  {
    printf("\t>Eseguo in backgrund: %s\n",*cline);
  }
}


int main()
{
  while(userin(prompt) != EOF)  //continua a richiamare funzione userin, stampa prompt e parsifica tramite procline
    procline();
  return 0;
}

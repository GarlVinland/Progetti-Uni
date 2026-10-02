package scanner;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PushbackReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import errori.LexicalException;

import java.util.Arrays;

import token.*;

public class Scanner {
	final char EOF = (char) -1; 
	private int riga;
	private PushbackReader buffer;
	private Token nextTk;

	private List<Character> skpChars;
	private List<Character> letters;
	private List<Character> digits;

	private HashMap<Character, TokenType> operTkType;
	private HashMap<Character, TokenType> delimTkType;
	private HashMap<String, TokenType> keyWordsTkType;

	public Scanner(String fileName) throws FileNotFoundException {
		this.buffer = new PushbackReader(new FileReader(fileName));
		riga = 1;
		
		skpChars = Arrays.asList(' ', '\t', '\n', '\r', EOF);
		
		letters = Arrays.asList('a', 'b','c','d','e','f','g','h','i','j','k', 'l','m','n','o','p','q','r','s','t','u','v','w','x','y','z');
		digits = Arrays.asList('0', '1','2','3','4','5','6','7','8','9');
		
		operTkType = new HashMap<>(Map.of(
				'+', TokenType.PLUS,
				'-', TokenType.MINUS,
				'*', TokenType.MUL,
				'/', TokenType.DIV		
		));
		
		delimTkType = new HashMap<>(Map.of(
				'=', TokenType.ASSIGN,
				';', TokenType.SEMI				
		));
		
		keyWordsTkType = new HashMap<>(Map.of(
				"print", TokenType.PRINT,
				"float", TokenType.TYFLOAT,
				"int", TokenType.TYINT
		));
				
	}
	
	
	

	
  // nextToken ritorna il prossimo token nel file di input e legge 
  // i caratteri del token ritornato (avanzando fino al carattere
  // successivo all'ultimo carattere del token)
	public Token nextToken() throws LexicalException, IOException {
		
		//salva il singolo token tra una chiamata e la successiva
		if(nextTk != null) {
			Token t = nextTk;
			nextTk = null;
			return t;
		}

		
		char nextChar; 
		try {
			nextChar = peekChar();
		}catch(IOException e) {
			throw new LexicalException("Non ci sono prossimi char da prendere", e);
		}

		// Avanza nel buffer leggendo i carattere in skipChars
		// incrementando riga se leggi '\n'.
		// Se raggiungi la fine del file ritorna il Token EOF
		while(skpChars.contains(nextChar)) {
			if(nextChar == '\n') {
				riga++;
			}
			
			if(nextChar == EOF) {
				Token tk = new Token(TokenType.EOF, riga, "EOF");
				return tk;
			}
			
			readChar();
			nextChar = peekChar();
		}


		// Se nextChar e' in letters
		// return scanId()
		// che deve generare o un Token ID o parola chiave
		if(letters.contains(nextChar)) {
			return scanId();
		}

		// Se nextChar e' o in operators oppure delimitatore
		// ritorna il Token associato con l'operatore o il delimitatore
		// Attenzione agli operatori di assegnamento!
		if(operTkType.containsKey(nextChar)) {
			return scanOperator();
		}

		// Se nextChar e' ; o = 
		// ritorna il Token associato
		if(delimTkType.containsKey(nextChar)) {
			return scanOperator();
		}
		

		// Se nextChar e' in numbers
		// return scanNumber()
		// che legge sia un intero che un float e ritorna il Token INUM o FNUM
		// i caratteri che leggete devono essere accumulati in una stringa
		// che verra' assegnata al campo valore del Token
		if(digits.contains(nextChar)) {	
			return scanNumber();
		}

		// Altrimenti il carattere NON E' UN CARATTERE LEGALE sollevate una
		// eccezione lessicale dicendo la riga e il carattere che la hanno
		// provocata. 
		throw new LexicalException("Carattere: " + nextChar + " non legale alla riga " + riga);

	}
	


	//controlla la presenza di una keyword
	private Token scanId() throws IOException {
		String val = "";
		
		while(letters.contains(peekChar()) || digits.contains(peekChar())) {
			val += readChar();
		}
		
		if(keyWordsTkType.containsKey(val)){
			return new Token(keyWordsTkType.get(val), riga);
		}
		
		return new Token(TokenType.ID, riga, val);
	}
	
	//controlla se è presente un operatore aritmetico
	private Token scanOperator() throws IOException {
		//leggo
		char c = readChar();
		
		//se è un operatore ritorno oper
		if(operTkType.containsKey(c)) {
			//Controlloe la presenza di = per i casi +=, -=, *=, /=
			if(peekChar() == '=') {
				readChar();
				return new Token(TokenType.OP_ASSIGN, riga, String.valueOf(c));
			}
			return new Token(operTkType.get(c), riga);
		}
			
		//non è operatore quindi = o ;
		return new Token(delimTkType.get(c), riga);
	}
		
	
	//controlla il tipo tra int e float
	private Token scanNumber() throws IOException {
		String val = "";
		
		while(digits.contains(peekChar())) {
			val += readChar();
		}
		
		//se trovo il punto ho un float
		if(peekChar() == '.') {
			//consumo il punto
			val += readChar();
			//leggo i numeri successivi
			while(digits.contains(peekChar())) {
				val += readChar();
			}
			return new Token(TokenType.FNUM, riga, val);
		}else {
			return new Token(TokenType.INUM, riga, val);
		}

	}

	
	//legge e consuma, avanzando
	private char readChar() throws IOException {
		return ((char) this.buffer.read());
	}

	//legge e non consuma, ritorna cosa ha letto senza avanzare
	private char peekChar() throws IOException {
		char c = (char) buffer.read();
		buffer.unread(c);
		return c;
	}
	
	//legge e non consume token, ritora cosa ha letto senza avanzare
	public Token peekToken() throws LexicalException, IOException {
		if (nextTk == null) {
			nextTk = nextToken();
		}
		return nextTk;
	}
}

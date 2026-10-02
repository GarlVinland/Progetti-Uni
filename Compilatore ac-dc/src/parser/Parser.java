package parser;

import java.io.IOException;
import java.util.ArrayList;

import ast.LangOper;
import ast.LangType;
import ast.NodeAssign;
import ast.NodeBinOp;
import ast.NodeCost;
import ast.NodeDecSt;
import ast.NodeDecl;
import ast.NodeDeref;
import ast.NodeExpr;
import ast.NodeId;
import ast.NodePrint;
import ast.NodeProgram;
import ast.NodeStm;
import errori.LexicalException;
import errori.SyntacticException;
import scanner.Scanner;
import token.Token;
import token.TokenType;

public class Parser {

	private Scanner scanner;
	
	
	public Parser(Scanner scanner) {
		this.scanner = scanner;
	}
	
	
	//verifica e consuma un token terminale
	private Token match(TokenType type) throws LexicalException, SyntacticException {
		Token tk;
		
		try {    
			tk = scanner.peekToken();
		}catch(Exception e) {
			throw new LexicalException("Errore", e);
		}
		
		if(type.equals(tk.getTipo())) {
			try {
				return scanner.nextToken();
			}catch(Exception e) {
				throw new SyntacticException("Errore durante scansione sintassi", e);
			}
			
		} else {
		    //System.out.println("Atteso: " + type + " trovato: " + tk.getTipo() + " riga: " + tk.getRiga());
			throw new SyntacticException("Errore durante scansione sintassi");
		}
	}
	

	public NodeProgram parse() throws LexicalException, SyntacticException, IOException{
		return parsePrg();
	}
	
	//Simbolo iniziale
	private NodeProgram parsePrg() throws LexicalException, SyntacticException, IOException{
		Token tk;
		
		try {
			tk = scanner.peekToken();
		} catch(Exception e) {
			throw new LexicalException("Errore", e);
		}
		
		switch(tk.getTipo()) {
			
			case TYFLOAT, TYINT, ID, PRINT, EOF -> {
				ArrayList<NodeDecSt> prg = parseDSs();
				match(TokenType.EOF);
				return new NodeProgram(prg);
			}
			

			default -> throw new SyntacticException("Token " + tk.getTipo() + " riga: " + tk.getRiga() + " nnon è simbolo iniziale");
		}
	}
	
	//dichiarazioni float int, statement id print, EOF 
	private ArrayList<NodeDecSt> parseDSs() throws LexicalException, SyntacticException, IOException{
		
		Token tk;
		
		try {
			tk = scanner.peekToken();
		} catch(Exception e) {
			throw new LexicalException("Errore", e);
		}
		
		switch(tk.getTipo()) {
		
			case TYFLOAT, TYINT -> {
				NodeDecl decl = parseDcl();
				ArrayList<NodeDecSt> decSt = parseDSs();
				decSt.add(0, decl);
				return decSt;
			}
					
			case ID, PRINT -> {
				NodeStm stm = parseStm();
				ArrayList<NodeDecSt> decSt = parseDSs();
				decSt.add(0, stm);
				return decSt;
			}
			

			case EOF -> {
				return new ArrayList<NodeDecSt>();
			}
			default -> throw new SyntacticException("Unexpected Token: " + tk.getTipo() + " Riga: "+  tk.getRiga());
		}
	}
	
	//inizia con float o int
	private NodeDecl parseDcl() throws LexicalException, SyntacticException, IOException{	
		LangType ty = parseTy();
		NodeId id = new NodeId(match(TokenType.ID).getVal());
		NodeExpr dclP = parseDclP();
		return new NodeDecl(id, ty, dclP);	
	}
		
	//per parole chiave TYFLOAT E TYINT
	private LangType parseTy() throws LexicalException, SyntacticException, IOException{
		
		Token tk;
		
		try {
			tk = scanner.peekToken();
		} catch(Exception e) {
			throw new LexicalException("Errore", e);
		}
		
		switch(tk.getTipo()) {
		
			case TYFLOAT -> {
				match(TokenType.TYFLOAT);
				return LangType.FLOAT;
			}
			
			case TYINT -> {
				match(TokenType.TYINT);
				return LangType.INT;
			}
			
			default -> throw new SyntacticException("");

		}
	}
	
	//parte finale di una dichiarazione ;
	private NodeExpr parseDclP() throws LexicalException, SyntacticException, IOException{
		
		Token tk;
		
		try {
			tk = scanner.peekToken();
		} catch(Exception e) {
			throw new LexicalException("Errore", e);
		}
		
		switch(tk.getTipo()) {
		
			case SEMI -> {
				match(TokenType.SEMI);
				return null;
			}
			
			case ASSIGN -> {
				match(TokenType.ASSIGN);
				NodeExpr exp = parseExp();
				match(TokenType.SEMI);
				return exp;
			}
			
			default -> throw new SyntacticException("");

		}
			
	}
	
	//statement, assegna o print
	private NodeStm parseStm() throws LexicalException, SyntacticException, IOException{
		
		Token tk;
		
		try {
			tk = scanner.peekToken();
		} catch(Exception e) {
			throw new LexicalException("Errore", e);
		}
		
		switch(tk.getTipo()) {
		
			case PRINT -> {
				match(TokenType.PRINT);
				Token idTk = match(TokenType.ID);
				match(TokenType.SEMI);
				return new NodePrint(new NodeId(idTk.getVal()));
				
			}
			
			case ID -> {
				Token idTk = match(TokenType.ID);
				LangOper op = parseOp();
				NodeExpr exp = parseExp();
				match(TokenType.SEMI);
				NodeId id = new NodeId(idTk.getVal());
				if(op == null) {
					return new NodeAssign(id, exp);
				}
				return new NodeAssign(id, new NodeBinOp(op, new NodeDeref(id), exp));
			
			}

			
			default -> throw new SyntacticException("Unexpected Token: " + tk.getTipo() + " Riga: "+  tk.getRiga());

		}
			
	}
	
	//------------------------------------------------------------------------------------------------------------------
	
	
	//espressione
	private NodeExpr parseExp() throws LexicalException, SyntacticException, IOException{
		NodeExpr tr = parseTr();
		NodeExpr exp = parseExpP(tr);
		return exp;
	}
	
	//parte opzionale di espressione, per + e - 
	private NodeExpr parseExpP(NodeExpr left) throws LexicalException, SyntacticException, IOException{
		Token tk;
		
		try {
			tk = scanner.peekToken();
		} catch(Exception e) {
			throw new LexicalException("Errore", e);
		}
		
		switch(tk.getTipo()) {
		
			case PLUS -> {
				match(TokenType.PLUS);
				NodeExpr tr = parseTr();
				NodeExpr node = new NodeBinOp(LangOper.PLUS, left, tr);
				return parseExpP(node);
			}
			
			case MINUS -> {
				match(TokenType.MINUS);
				NodeExpr tr = parseTr();
				NodeExpr node = new NodeBinOp(LangOper.MINUS, left, tr);
				return parseExpP(node);
				
			}
			
			case SEMI -> {
				return left;
			}
			
			case EOF -> {
				return null;
			}
			default -> throw new SyntacticException("");
		}
	}
	
	//termine composto da un valore seguito da parte opzionale TrP (come Exp ma per * e /)
	private NodeExpr parseTr() throws LexicalException, SyntacticException, IOException{
		NodeExpr val = parseVal();
		NodeExpr tr = parseTrP(val);
		return tr;
	}
	
	
	//gestisce * e / (come ExpP ma per * e /)
	private NodeExpr parseTrP(NodeExpr left) throws LexicalException, SyntacticException, IOException{
		Token tk;
		
		try {
			tk = scanner.peekToken();
		} catch(Exception e) {
			throw new LexicalException("Errore", e);
		}
		
		switch(tk.getTipo()) {
		
			case DIV -> {
				match(TokenType.DIV);
				NodeExpr val = parseVal();
				NodeExpr trP = parseTrP(val);
				return new NodeBinOp(LangOper.DIV, left, trP);
			}
			
			case MUL -> {
				match(TokenType.MUL);
				NodeExpr val = parseVal();
				NodeExpr trP = parseTrP(val);
				return new NodeBinOp(LangOper.MUL, left, trP);
			}
			
			case EOF -> {
				return null;
			}
			
			case PLUS, MINUS, SEMI ->{
				return left;
			}
			default -> throw new SyntacticException("");
		}
	}
	
	//valore generico, puo essere un intero, float o identificatore id
	private NodeExpr parseVal() throws LexicalException, SyntacticException, IOException{
		Token tk;
		
		try {
			tk = scanner.peekToken();
		} catch(Exception e) {
			throw new LexicalException("Errore", e);
		}
		
		switch(tk.getTipo()) {
		
			case INUM -> {
				match(TokenType.INUM);
				return new NodeCost(tk.getVal(), LangType.INT);
			}
			
			case FNUM -> {
				match(TokenType.FNUM);
				return new NodeCost(tk.getVal(), LangType.FLOAT);
			}
			
			case ID -> {
				match(TokenType.ID);
				return new NodeDeref(new NodeId(tk.getVal()));
			}

			default -> throw new SyntacticException("Riga: "+  tk.getRiga());
		}
	}
	
	
	//assegnamento = o +=, -=, *=, /=
	private LangOper parseOp() throws LexicalException, SyntacticException, IOException{
		Token tk;
		
		try {
			tk = scanner.peekToken();
		} catch(Exception e) {
			throw new LexicalException("Errore", e);
		}
		
		switch(tk.getTipo()) {
		
			case ASSIGN -> {
				match(TokenType.ASSIGN);
				return null;
			}
			
			case OP_ASSIGN -> {
				Token op = match(TokenType.OP_ASSIGN);
				if(op.getVal().equals("+")) {
					return LangOper.PLUS;
				}
				
				if(op.getVal().equals("-")) {
					return LangOper.MINUS;
				}
				
				if(op.getVal().equals("*")) {
					return LangOper.MUL;
				}
				
				if(op.getVal().equals("/")) {
					return LangOper.DIV;
				}
				throw new SyntacticException("Operatore atteso Riga: "+  tk.getRiga());
			}

			default -> throw new SyntacticException("Operatore atteso Riga: "+  tk.getRiga());
		}
	}

}

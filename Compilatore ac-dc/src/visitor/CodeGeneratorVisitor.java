package visitor;

import ast.NodeAssign;
import ast.NodeBinOp;
import ast.NodeConvert;
import ast.NodeCost;
import ast.NodeDecSt;
import ast.NodeDecl;
import ast.NodeDeref;
import ast.NodeId;
import ast.NodePrint;
import ast.NodeProgram;
import errori.CodeGenerationException;
import symbolTable.Registri;
import symbolTable.SymbolTable;
import symbolTable.SymbolTable.Attributes;

public class CodeGeneratorVisitor implements IVisitor {
	
	private String codiceDc;
	private String log;
	
	public CodeGeneratorVisitor() {
		codiceDc = "";
		log = "";
		Registri.creaArr();
	}
	
	public String getLog() {
		return log;
	}
	
	public String getCodiceDc() {
		return codiceDc;
	}

	//genera codice dell'espressione e salva nel registro
	@Override
	public void visit(NodeAssign node) {
		if(!log.isEmpty()) {
			return;
		}
		
		node.getExpr().accept(this);
		Attributes at = SymbolTable.lookup(node.getId().getNome());
		codiceDc = codiceDc + " s" + at.getReg();
		
	}

	//genera left, right e op
	@Override
	public void visit(NodeBinOp node) {
		if(!log.isEmpty()) {
			return;
		}
		
		node.getLeft().accept(this);
		String sx = codiceDc;
		node.getRight().accept(this);
		String dx = codiceDc;
		
		String op;
		switch(node.getOp()) {
			
			case PLUS -> op = "+";
			case MINUS -> op = "-";
			case MUL -> op = "*";
			case DIV -> op = "/";
			case DIV_FLOAT -> op = "5 k / 0 k";
			default -> op = "";
		}
		
		codiceDc = sx + " " + dx + " " + op;
		
	}

	//push costante sullo stack
	@Override
	public void visit(NodeCost node) {
		if(!log.isEmpty()) {
			return;
		}
		
		codiceDc = node.getVal();
		
	}

	
	
	@Override
	public void visit(NodeDecl node) {
		Attributes at = SymbolTable.lookup(node.getId().getNome());
		char reg = 0;
		try {
			reg = Registri.newR();
		} catch (CodeGenerationException e) {
			log = "Errore registri esauriti " + node.getId().getNome();
			return;
		}

		at.setReg(reg);

		if (node.getInit() != null) {
			node.getInit().accept(this);
			codiceDc = codiceDc + " s" + reg;
		} else {
			codiceDc = "";
		}
		
	}

	//carica il registro associato all'identificatore
	@Override
	public void visit(NodeDeref node) {
		if(!log.isEmpty()) {
			return;
		}
		
		Attributes at = SymbolTable.lookup(node.getId().getNome());
		codiceDc = "l" + at.getReg();
	}

	
	@Override
	public void visit(NodeId node) {
		codiceDc = String.valueOf(SymbolTable.lookup(node.getNome()).getReg());
		
	}

	//carica il registro sullo stack
	@Override
	public void visit(NodePrint node) {
		
		if(!log.isEmpty()) {
			return;
		}
		
		Attributes at = SymbolTable.lookup(node.getId().getNome());
		codiceDc = "l" + at.getReg() + " p P";
		
	}

	//visita tutti i nodi e accumula codice
	@Override
	public void visit(NodeProgram node) {
		String temp = "";
		
		for (NodeDecSt decSt : node.getDecSt()) {
			if (!log.isEmpty()) {
				break;
			}
			
			decSt.accept(this);
			
			if(!codiceDc.isEmpty()) {
				temp += codiceDc + " ";
			}			
		}
		
		codiceDc = temp.trim();
		
	}

	//visita figlio
	@Override
	public void visit(NodeConvert node) {
		if(!log.isEmpty()) {
			return;
		}
		
		node.getExpr().accept(this);		
	}

	
	
}

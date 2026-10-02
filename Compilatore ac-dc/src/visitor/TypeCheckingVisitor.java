
package visitor;

import ast.LangOper;
import ast.LangType;
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
import symbolTable.SymbolTable;
import symbolTable.SymbolTable.Attributes;

public class TypeCheckingVisitor implements IVisitor {
	
	private TypeDescriptor resType; 
	private String log;
	
	
	public TypeCheckingVisitor() {
		log = "";
		SymbolTable.init();
	}
	
	public TypeDescriptor getResType() {
		return resType;
	}
	
	//visita il nodo, controlla che espressioni e dichiarazioni siano corrette(controlla eventuali FLOAT -> INT)
	@Override
	public void visit(NodeBinOp node) {
		node.getLeft().accept(this);
		TypeDescriptor leftTD = resType;
		node.getRight().accept(this);
		TypeDescriptor rightTD = resType;
		
		if(leftTD.getTipo() == TipoTD.ERROR) {
			resType = leftTD;
		} else if(rightTD.getTipo() == TipoTD.ERROR) {
			resType = rightTD;
		} else if (leftTD.getTipo() != rightTD.getTipo()) {
			
			if(leftTD.getTipo() == TipoTD.INT) {
				node.setLeft(new NodeConvert(node.getLeft()));
			} else {
				node.setRight(new NodeConvert(node.getRight()));
			}
			
			if(node.getOp() == LangOper.DIV) {
				node.setOp(LangOper.DIV_FLOAT);
			}
			
			resType = new TypeDescriptor(TipoTD.FLOAT);
			
		} else {

			if(leftTD.getTipo() == TipoTD.FLOAT) {
				resType = new TypeDescriptor(TipoTD.FLOAT);
			} else {
				resType = new TypeDescriptor(TipoTD.INT);
			}
		}
		
	}

	
	//visita il nodo e imposta il match
	@Override
	public void visit(NodeAssign node) {
		node.getId().accept(this);
		TypeDescriptor idTD = resType;
		node.getExpr().accept(this);
		TypeDescriptor exprTD = resType;
		
		if(idTD.getTipo() == TipoTD.ERROR) {
			resType = idTD;
		} else if (exprTD.getTipo() == TipoTD.ERROR) {
			resType = exprTD;
		} else if(!exprTD.compatibile(idTD)) {
			resType = new TypeDescriptor(TipoTD.ERROR, node.getId().getNome());
			log += "Errore semantico  " + resType.getMsg();
		} else {
			resType = new TypeDescriptor(TipoTD.OK);
		}
		
	}

	//visita il nodo e imposta il tipo
	@Override
	public void visit(NodeCost node) {
		if(node.getType() == LangType.INT) {
			resType = new TypeDescriptor(TipoTD.INT);
		} else {
			resType = new TypeDescriptor(TipoTD.FLOAT);
		}
		
	}

	//visita il nodo e controlla se è stato dichiarato nella SymbolTable, se si mette ERROR nel descriptor
	@Override
	public void visit(NodeDecl node) {	
		Attributes at = SymbolTable.lookup(node.getId().getNome());
		
		if(at != null) {
			resType = new TypeDescriptor(TipoTD.ERROR, "Variabile: " + node.getId().getNome() + " già dichiarata");
			log += "Errore " + resType.getMsg();
			return;
		}
		
			
		if(node.getInit() == null) {
			SymbolTable.enter(node.getId().getNome(), new Attributes(node.getType(), ' '));
			resType = new TypeDescriptor(TipoTD.OK);
			return;
		}
		
		node.getInit().accept(this);
		
		if(resType.getTipo() == TipoTD.ERROR) {
			return;
		}
		
		TypeDescriptor decl;
		
		if(node.getType() == LangType.INT) {
			decl = new TypeDescriptor(TipoTD.INT);
		} else {
			decl = new TypeDescriptor(TipoTD.FLOAT);
		}
		
		if(!resType.compatibile(decl)) {
			resType = new TypeDescriptor(TipoTD.ERROR);
			log += "Errore " + resType.getMsg();
			return;
		}
		
		SymbolTable.enter(node.getId().getNome(), new Attributes(node.getType(), ' '));
		resType = new TypeDescriptor(TipoTD.OK);
	
	}

	
	//visita il nodo e imposta il tipo al TypeDescriptor
	@Override
	public void visit(NodeDeref node) {
		node.getId().accept(this);		
	}

	
	//visita il nodo e contralla se è stato dichiarato nella SymbolTable
	@Override
	public void visit(NodeId node) {
		Attributes at = SymbolTable.lookup(node.getNome());
		
		if(at == null) {
			resType = new TypeDescriptor(TipoTD.ERROR, "Variabile: " + node.getNome() + " non dichiarata");
			log += "Errore " + resType.getMsg();
		}else {
			if(at.getTipo() == LangType.INT) {
				resType = new TypeDescriptor(TipoTD.INT);
			} else {
				resType = new TypeDescriptor(TipoTD.FLOAT);
			}
		}
	}

	//visita e imposta il nodo al tipo della variabile
	@Override
	public void visit(NodePrint node) {
		node.getId().accept(this);

		if (resType.getTipo() != TipoTD.ERROR)
			resType = new TypeDescriptor(TipoTD.OK);
		
	}

	
	//visita tutti i nodi del AST
	@Override
	public void visit(NodeProgram node) {
		for(NodeDecSt decSt : node.getDecSt()) {
			decSt.accept(this);
		}
		
	}
	
	//converte da int a float
	@Override
	public void visit(NodeConvert node) {
		
		resType = new TypeDescriptor(TipoTD.FLOAT);
		
	}

	public String getLog() {
		return log;
	}
	

		
}
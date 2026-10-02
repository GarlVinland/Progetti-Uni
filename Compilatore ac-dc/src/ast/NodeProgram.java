package ast;

import java.util.ArrayList;

import visitor.IVisitor;

public class NodeProgram extends NodeAst {
	private ArrayList<NodeDecSt> decSt;
	
	public NodeProgram(ArrayList<NodeDecSt> decSt) {
		this.decSt = decSt;
	}
	
	public ArrayList<NodeDecSt> getDecSt() {
		return decSt;
	}
	
	@Override
	public String toString() {
		return "[Program: " + decSt + " ]";
	}
	
	@Override
	public void accept(IVisitor visitor) {
		visitor.visit(this);
	}
	
}

package ast;

import visitor.IVisitor;

public class NodeId extends NodeAst{
	private String nome;
	
	public NodeId(String nome) {
		this.nome = nome;
	}
	
	public String getNome() {
		return nome;
	}
	
	@Override
	public String toString() {
		return "[ID: " + nome + " ]";
	}
	
	@Override
	public void accept(IVisitor visitor) {
		visitor.visit(this);
	}
}

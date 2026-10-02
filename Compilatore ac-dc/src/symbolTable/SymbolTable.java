package symbolTable;

import java.util.HashMap;

import ast.LangType;

public class SymbolTable {
	
	private static HashMap<String, Attributes> table;
	
	public static class Attributes{
		private LangType tipo;
		private char registro;
		
		public Attributes(LangType tipo, char registro) {
			this.tipo = tipo;
			this.registro = registro;
		}
		
		public LangType getTipo() {
			return tipo;
		}
		
		public void setTipo(LangType tipo) {
			this.tipo = tipo;
		}
		
		public char getReg() {
			return registro;
		}
		
		public void setReg(char registro) {
			this.registro = registro;
		}
	}
	
	//crea hashmap
	public static void init() {
		table = new HashMap<>();
	}
	
	//inserimento hashmap
	public static boolean enter(String id, Attributes entry) {
		
		if(table.containsKey(id)) {
			return false;
		}
		
		table.put(id, entry);
		return true;
	}
	
	//mostra contenuto di un id
	public static Attributes lookup(String id) {
		return table.get(id);
	}
	
	//ritorna stringa del symbol table in formato stringa
	public static String toStr() {
		String str = "";
		
		for(var e : table.entrySet()) {
			str += "Id: " + e.getKey() + ", Tipo: " + e.getValue().getTipo() + "\n";
		}
		
		return str;
		
	}
	
	
	//dimensione tabella
	public static int size() {
		return table.size();
	}
}

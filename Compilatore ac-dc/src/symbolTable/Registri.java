package symbolTable;

import java.util.ArrayList;

import errori.CodeGenerationException;

public class Registri {
	
	private static ArrayList<Character> arr;
	
	public static void creaArr() {
		arr = new ArrayList<>();
		char c;
		for(c = 'a'; c <= 'z'; c++) {
			arr.add(c);
		}
	}
	
	public static char newR() throws CodeGenerationException{
		
		if(arr.isEmpty()) {
			throw new CodeGenerationException(" ");
		}
		
		return arr.remove(0);

	}
}

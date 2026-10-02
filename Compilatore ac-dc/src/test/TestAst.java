package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import ast.NodeProgram;
import errori.SyntacticException;
import parser.Parser;
import scanner.Scanner;

class TestAst {

	private static final String path = "Compilatore/src/test/data/testAst/";


	@Test
	void testAstCorr1() throws Exception{
		
		Parser p = new Parser(new Scanner(path + "testAstCor1.txt"));
		
		NodeProgram np = p.parse();
		
		//System.out.println(np.toString());
		assertEquals("[Program: [[Decl: INT , [ID: temp ] ], [Print: [ID: temp ] ], [Decl: FLOAT , [ID: temp1 ] ], [Print: [ID: temp1 ] ]] ]", np.toString());
	}
	
	@Test
	void testAstErr1() throws Exception{
		
		Parser p = new Parser(new Scanner(path + "testAstErr1.txt"));
		
		assertThrows(SyntacticException.class, () -> {
			 p.parse();
		});
		
	}
	
	@Test
	void testAstErr2() throws Exception{
		
		Parser p = new Parser(new Scanner(path + "testAstErr2.txt"));
		
		assertThrows(SyntacticException.class, () -> {
			 p.parse();
		});
		
	}
	
	@Test
	void testAstCorr2() throws Exception{
		
		Parser p = new Parser(new Scanner(path + "testAstCor2.txt"));
		
		NodeProgram np = p.parse();
		
		//System.out.println(np.toString());
		assertEquals("[Program: [[Decl: INT , [ID: temp ] ], [Stm: [ID: temp ] -> [BinOp: PLUS , [Deref: [ID: temp ] ] , [Cost: 7 , INT ] ] ], [Stm: [ID: temp ] -> [BinOp: MINUS , [BinOp: PLUS , [Cost: 3 , INT ] , [BinOp: MUL , [Cost: 7 , INT ] , [Cost: 5 , INT ] ] ] , [Cost: 6 , INT ] ] ]] ]", np.toString());
	}
	
	@Test
	void testAstCorr3() throws Exception{
		
		Parser p = new Parser(new Scanner(path + "testAstCor3.txt"));
		
		NodeProgram np = p.parse();
		
		//System.out.println(np.toString());
		assertEquals("[Program: [[Decl: INT , [ID: temp ] ], [Stm: [ID: temp ] -> [BinOp: PLUS , [Deref: [ID: temp ] ] , [Cost: 10 , INT ] ] ], [Stm: [ID: temp ] -> [BinOp: MINUS , [BinOp: PLUS , [Cost: 3 , INT ] , [BinOp: DIV , [Cost: 7 , INT ] , [Cost: 4 , INT ] ] ] , [Cost: 2 , INT ] ] ], [Print: [ID: temp ] ]] ]", np.toString());
	}

}

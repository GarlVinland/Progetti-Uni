package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import ast.NodeProgram;
import parser.Parser;
import scanner.Scanner;
import visitor.CodeGeneratorVisitor;
import visitor.TypeCheckingVisitor;

class testCodiceGenerator {
	
	private static final String path = "Compilatore/src/test/data/testCodeGenerator/";

	@Test
	void testAssign() throws Exception{
		Parser p = new Parser(new Scanner(path + "1_assign.txt"));
		
		NodeProgram np = p.parse();
		
		TypeCheckingVisitor tcv = new TypeCheckingVisitor();
		np.accept(tcv);
		
		CodeGeneratorVisitor cgv = new CodeGeneratorVisitor();
		np.accept(cgv);
		
		//System.out.println(cgv.getLog());
		//System.out.println(cgv.getCodiceDc());
		assertEquals("", cgv.getLog());
		assertEquals("1 6 / sa la p P", cgv.getCodiceDc());
	}
	
	@Test
	void testDivisioni() throws Exception{
		Parser p = new Parser(new Scanner(path + "2_divsioni.txt"));
		
		NodeProgram np = p.parse();
		
		TypeCheckingVisitor tcv = new TypeCheckingVisitor();
		np.accept(tcv);
		
		CodeGeneratorVisitor cgv = new CodeGeneratorVisitor();
		np.accept(cgv);
		
		//System.out.println(cgv.getLog());
		//System.out.println(cgv.getCodiceDc());
		assertEquals("", cgv.getLog());
		assertEquals("0 sa la 1 + sa 6 sb 1.0 6 5 k / 0 k la lb / + sc la p P lb p P lc p P", cgv.getCodiceDc());
	}
	
	@Test
	void testGenerale() throws Exception{
		Parser p = new Parser(new Scanner(path + "3_generale.txt"));
		
		NodeProgram np = p.parse();
		
		TypeCheckingVisitor tcv = new TypeCheckingVisitor();
		np.accept(tcv);
		
		CodeGeneratorVisitor cgv = new CodeGeneratorVisitor();
		np.accept(cgv);
		
		//System.out.println(cgv.getLog());
		//System.out.println(cgv.getCodiceDc());
		assertEquals("", cgv.getLog());
		assertEquals("5 3 + sa la 0.5 + sb la p P lb 4 5 k / 0 k sb lb p P lb 1 - sc lc lb * sc lc p P", cgv.getCodiceDc());
	}
	
	@Test
	void testRegistriFiniti() throws Exception{
		Parser p = new Parser(new Scanner(path + "4_registriFiniti.txt"));
		
		NodeProgram np = p.parse();
		
		TypeCheckingVisitor tcv = new TypeCheckingVisitor();
		np.accept(tcv);
		
		CodeGeneratorVisitor cgv = new CodeGeneratorVisitor();
		np.accept(cgv);
		
		//System.out.println(cgv.getLog());
		//System.out.println(cgv.getCodiceDc());
		assertEquals("Errore registri esauriti uno", cgv.getLog());
		assertEquals("6 2 / sa la p P", cgv.getCodiceDc());
		
	}
	
	@Test
	void testDivisioneZero() throws Exception{
		Parser p = new Parser(new Scanner(path + "5_DivisioneZ.txt"));
		
		NodeProgram np = p.parse();
		
		TypeCheckingVisitor tcv = new TypeCheckingVisitor();
		np.accept(tcv);
		
		CodeGeneratorVisitor cgv = new CodeGeneratorVisitor();
		np.accept(cgv);
		
		System.out.println(cgv.getLog());
		System.out.println(cgv.getCodiceDc());
		//assertEquals("Errore registri esauriti uno", cgv.getLog());
		//assertEquals("6 2 / sa la p P", cgv.getCodiceDc());
		
	}

}

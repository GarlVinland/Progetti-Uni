package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import ast.NodeProgram;
import parser.Parser;
import scanner.Scanner;
import visitor.TipoTD;
import visitor.TypeCheckingVisitor;

class TestTypeChecking {
	
	private static final String path = "Compilatore/src/test/data/testTypeChecking/";

	@Test
	void testDicRipetute() throws Exception {
		Parser p = new Parser(new Scanner(path + "1_dicRipetute.txt"));
		NodeProgram np = p.parse();
		TypeCheckingVisitor tcv = new TypeCheckingVisitor();
		np.accept(tcv);
		assertEquals(TipoTD.ERROR, tcv.getResType().getTipo());
		//System.out.println(tcv.getLog());
	}
	
	@Test
	void test2IdNonDec() throws Exception {
		Parser p = new Parser(new Scanner(path + "2_idNonDec.txt"));
		NodeProgram np = p.parse();
		TypeCheckingVisitor tcv = new TypeCheckingVisitor();
		np.accept(tcv);
		assertEquals(TipoTD.ERROR, tcv.getResType().getTipo());
		//System.out.println(tcv.getLog());
	}
	
	@Test
	void test3IdNonDec() throws Exception {
		Parser p = new Parser(new Scanner(path + "3_idNonDec"));
		NodeProgram np = p.parse();
		TypeCheckingVisitor tcv = new TypeCheckingVisitor();
		np.accept(tcv);
		assertEquals(TipoTD.ERROR, tcv.getResType().getTipo());
		//System.out.println(tcv.getLog());
	}
	
	@Test
	void testTipoNonCompatibile() throws Exception {
		Parser p = new Parser(new Scanner(path + "4_tipoNonCompatibile.txt"));
		NodeProgram np = p.parse();
		TypeCheckingVisitor tcv = new TypeCheckingVisitor();
		np.accept(tcv);
		assertEquals(TipoTD.ERROR, tcv.getResType().getTipo());
		//System.out.println(tcv.getLog());
	}
	
	@Test
	void test5Corretto() throws Exception {
		Parser p = new Parser(new Scanner(path + "5_corretto.txt"));
		NodeProgram np = p.parse();
		TypeCheckingVisitor tcv = new TypeCheckingVisitor();
		np.accept(tcv);
		assertEquals(TipoTD.OK, tcv.getResType().getTipo());
	}
	
	@Test
	void test6Corretto() throws Exception {
		Parser p = new Parser(new Scanner(path + "6_corretto.txt"));
		NodeProgram np = p.parse();
		TypeCheckingVisitor tcv = new TypeCheckingVisitor();
		np.accept(tcv);
		assertEquals(TipoTD.OK, tcv.getResType().getTipo());
	}
	
	@Test
	void test7Corretto() throws Exception {
		Parser p = new Parser(new Scanner(path + "7_corretto.txt"));
		NodeProgram np = p.parse();
		TypeCheckingVisitor tcv = new TypeCheckingVisitor();
		np.accept(tcv);
		assertEquals(TipoTD.OK, tcv.getResType().getTipo());
	}

}

package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import errori.SyntacticException;
import parser.Parser;
import scanner.Scanner;

class TestParse {

	private static final String path = "Compilatore/src/test/data/testParser/";
	
	/*
	 * 	Primi test con solo:
	 * 
	 *  parsePrg
	 *  parseDSs
     *  parseDcl
     *  parseTy      
     *  parseDclP considerando SOLO la produzione 5: DclP → ;
     *  parseStm considerando SOLO la produzione 8: Stm → print id;
	 * 
	 */
	
	@Test
	void testSoloDich() throws Exception {
		Parser p = new Parser(new Scanner(path + "testSoloDich.txt"));
		assertDoesNotThrow(() ->{
			p.parse();
		});
	}
	
	@Test
	void testSoloDichPrint() throws Exception {
		Parser p = new Parser(new Scanner(path + "testSoloDichPrint.txt"));
		assertDoesNotThrow(() ->{
			p.parse();
		});
	}
	
	@Test
	void testDichCor1() throws Exception {
		Parser p = new Parser(new Scanner(path + "testDichCor1.txt"));
		assertDoesNotThrow(() ->{
			p.parse();
		});
	}
	
	@Test
	void testDichCor2() throws Exception {
		Parser p = new Parser(new Scanner(path + "testDichCor2.txt"));
		assertDoesNotThrow(() ->{
			p.parse();
		});
	}
	
	@Test
	void testDichErr1() throws SyntacticException, Exception{
		Parser p = new Parser(new Scanner(path + "testDichErr1.txt"));
		assertThrows(SyntacticException.class, () ->{
			p.parse();
		});
	}
	
	@Test
	void testDichErr2() throws SyntacticException, Exception{
		Parser p = new Parser(new Scanner(path + "testDichErr2.txt"));
		assertThrows(SyntacticException.class, () ->{
			p.parse();
		});
	}

	@Test
	void testDichErr4() throws SyntacticException, Exception{
		Parser p = new Parser(new Scanner(path + "testDichErr4.txt"));
		assertThrows(SyntacticException.class, () ->{
			p.parse();
		});
	}
	
	
	/*
	 * 	Seconda parte con:
	 * 
	 *  parseExp
	 *  parseExpP
     *  parseTr
     *  parseTrP      
     *  parseVal 
     *  parseOp
	 * 
	 */
	
	@Test
	void testDichErr3() throws SyntacticException, Exception{
		Parser p = new Parser(new Scanner(path + "testDichErr3.txt"));
		assertThrows(SyntacticException.class, () ->{
			p.parse();
		});
	}
	
	@Test
	void testParserCorretto1() throws Exception {
		Parser p = new Parser(new Scanner(path + "testParserCorretto1.txt"));
		assertDoesNotThrow(() ->{
			p.parse();
		});
	}
	
	@Test
	void testParserCorretto2() throws Exception {
		Parser p = new Parser(new Scanner(path + "testParserCorretto2.txt"));
		assertDoesNotThrow(() ->{
			p.parse();
		});
	}
	
	@Test
	void testParserEcc0() throws Exception {
		Parser p = new Parser(new Scanner(path + "testParserEcc_0.txt"));
		assertThrows(SyntacticException.class, () ->{
			p.parse();
		});
	}
	
	@Test
	void testParserEcc1() throws Exception {
		Parser p = new Parser(new Scanner(path + "testParserEcc_1.txt"));
		assertThrows(SyntacticException.class, () ->{
			p.parse();
		});
	}
	
	@Test
	void testParserEcc2() throws Exception {
		Parser p = new Parser(new Scanner(path + "testParserEcc_2.txt"));
		assertThrows(SyntacticException.class, () ->{
			p.parse();
		});
	}
	
	@Test
	void testParserEcc3() throws Exception {
		Parser p = new Parser(new Scanner(path + "testParserEcc_3.txt"));
		assertThrows(SyntacticException.class, () ->{
			p.parse();
		});
	}
	
	@Test
	void testParserEcc4() throws Exception {
		Parser p = new Parser(new Scanner(path + "testParserEcc_4.txt"));
		assertThrows(SyntacticException.class, () ->{
			p.parse();
		});
	}
	
	@Test
	void testParserEcc5() throws Exception {
		Parser p = new Parser(new Scanner(path + "testParserEcc_5.txt"));
		assertThrows(SyntacticException.class, () ->{
			p.parse();
		});
	}
	
	@Test
	void testParserEcc6() throws Exception {
		Parser p = new Parser(new Scanner(path + "testParserEcc_6.txt"));
		assertThrows(SyntacticException.class, () ->{
			p.parse();
		});
	}
	
	@Test
	void testParserEcc7() throws Exception {
		Parser p = new Parser(new Scanner(path + "testParserEcc_7.txt"));
		assertThrows(SyntacticException.class, () ->{
			p.parse();
		});
	}
	
	
	
	
	
	

	
}

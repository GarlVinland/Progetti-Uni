package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import errori.LexicalException;
import scanner.Scanner;
import token.Token;
import token.TokenType;

class TestScanner {

	private static final String path = "Compilatore/src/test/data/testScanner/";


	@Test
	void testCaratteriNonCaratteri() throws Exception {
		
		Scanner scanner = new Scanner(path + "caratteriNonCaratteri.txt");
	    assertThrows(LexicalException.class, () -> {
	    	scanner.nextToken();
	    });

	}
	
	@Test
	void testCaratteriSkip() throws Exception {
	
		Scanner scanner = new Scanner(path + "caratteriSkip");
		Token t;
		
		t = scanner.nextToken();
		assertEquals(TokenType.EOF, t.getTipo());
	}

	@Test
	void testErroriNumbers() throws Exception {
		

		Scanner scanner = new Scanner(path + "erroriNumbers.txt");
		Token t;
		
		t = scanner.nextToken();
		assertEquals(TokenType.INUM, t.getTipo());
		assertEquals("0", t.getVal());
		assertEquals(1, t.getRiga());
		
		Token t1;
		t1 = scanner.nextToken();
		assertEquals(TokenType.INUM, t1.getTipo());
		assertEquals("33", t1.getVal());
		assertEquals(1, t1.getRiga());
		

		Token t2;
		t2 = scanner.nextToken();
		assertEquals(TokenType.FNUM, t2.getTipo());
		assertEquals("123.121212", t2.getVal());
		assertEquals(3, t2.getRiga());
		
		Token t3;
		t3 = scanner.nextToken();
		assertEquals(TokenType.FNUM, t3.getTipo());
		assertEquals("123.123", t3.getVal());
		assertEquals(5, t3.getRiga());
		
		assertThrows(LexicalException.class, () ->{
			scanner.nextToken();
		});
	
	

	}
	
	@Test
	void testEof() throws Exception{

		Scanner s = new Scanner(path + "testEOF.txt");
		Token t;
		t = s.nextToken();

		assertEquals(TokenType.EOF, t.getTipo());

	}
	
	@Test
	void testFloat() throws Exception {
		Scanner scanner = new Scanner(path + "testFloat.txt");
		Token t;
		
		t = scanner.nextToken();
		assertEquals(TokenType.FNUM, t.getTipo());
		assertEquals("098.8095", t.getVal());
		assertEquals(1, t.getRiga());

	}
	

	@Test
	void peekGenerale() throws Exception{

		Scanner s = new Scanner(path + "testGenerale.txt");
		
		assertEquals(s.peekToken().getTipo(), TokenType.TYINT);
		assertEquals(s.nextToken().getTipo(), TokenType.TYINT);
		assertEquals(s.peekToken().getTipo(), TokenType.ID);
		assertEquals(s.peekToken().getTipo(), TokenType.ID);
		
		Token t = s.nextToken();
		assertEquals(t.getTipo(), TokenType.ID);
		assertEquals(t.getRiga(), 1);
		assertEquals(t.getVal(), "temp");

	}
	
	@Test
	void testId() throws Exception {
		Scanner scanner = new Scanner(path + "testId.txt");
		Token t;
		
		t = scanner.nextToken();
		assertEquals(TokenType.ID, t.getTipo());
		assertEquals("jskjdsf2jdshkf", t.getVal());
		assertEquals(1, t.getRiga());
		
		Token t1 = scanner.nextToken();
		assertEquals(TokenType.ID, t1.getTipo());
		assertEquals("printl", t1.getVal());
		assertEquals(2, t1.getRiga());
		
		
		assertNotEquals(TokenType.PRINT, t1.getTipo());
		assertEquals("printl", t1.getVal());
		assertEquals(2, t1.getRiga());
	
	}
	
	@Test
	void testIdKeyWords() throws Exception {
		Scanner scanner = new Scanner(path + "testIdKeyWords.txt");
		Token t;
		
		t = scanner.nextToken();
		assertEquals(TokenType.TYINT, t.getTipo());
		assertNull(t.getVal());
		assertEquals(1, t.getRiga());
		
		Token t1 = scanner.nextToken();
		assertEquals(TokenType.ID, t1.getTipo());
		assertEquals("inta", t1.getVal());
		assertEquals(1, t1.getRiga());
	
	}
	
	@Test
	void testInt() throws Exception {
		Scanner scanner = new Scanner(path + "testInt.txt");
		Token t;
		
		t = scanner.nextToken();
		assertEquals(TokenType.INUM, t.getTipo());
		assertEquals("0050", t.getVal());
		assertEquals(1, t.getRiga());
		
		Token t1 = scanner.nextToken();
		assertEquals(TokenType.INUM, t1.getTipo());
		assertEquals("698", t1.getVal());
		assertEquals(2, t1.getRiga());
		
		Token t2 = scanner.nextToken();
		assertEquals(TokenType.INUM, t2.getTipo());
		assertEquals("560099", t2.getVal());
		assertEquals(4, t2.getRiga());
		 		 
		Token t3 = scanner.nextToken();
		assertEquals(TokenType.INUM, t3.getTipo());
		assertEquals("1234", t3.getVal());
		assertEquals(5, t3.getRiga());
	}
	
	@Test
	void testKeyWords() throws Exception {
		Scanner scanner = new Scanner(path + "testKeyWords.txt");
		Token t;
		
		t = scanner.nextToken();
		assertEquals(TokenType.PRINT, t.getTipo());
		assertNull(t.getVal());
		assertEquals(2, t.getRiga());
		
		Token t1 = scanner.nextToken();
		assertEquals(TokenType.TYFLOAT, t1.getTipo());
		assertNull(t1.getVal());
		assertEquals(2, t1.getRiga());
		
		Token t2 = scanner.nextToken();
		assertEquals(TokenType.TYINT, t2.getTipo());
		assertNull(t2.getVal());
		assertEquals(5, t2.getRiga());
		
	}
	
	/*
	@Test
	void testOpsDels() throws Exception {
		Scanner scanner = new Scanner(path + "testOpsDels.txt");
		Token t;
		
		t = scanner.nextToken();
		assertEquals(TokenType.PLUS, t.getTipo());
		
		t = scanner.nextToken();
		assertEquals(TokenType.DIV_ASSIGN, t.getTipo());
		
		t = scanner.nextToken();
		assertEquals(TokenType.MINUS, t.getTipo());
		
		t = scanner.nextToken();
		assertEquals(TokenType.MUL, t.getTipo());
		
		t = scanner.nextToken();
		assertEquals(TokenType.DIV, t.getTipo());
		
		t = scanner.nextToken();
		assertEquals(TokenType.PLUS_ASSIGN, t.getTipo());
		
		t = scanner.nextToken();
		assertEquals(TokenType.ASSIGN, t.getTipo());
		
		t = scanner.nextToken();
		assertEquals(TokenType.MINUS_ASSIGN, t.getTipo());
		
		t = scanner.nextToken();
		assertEquals(TokenType.MINUS, t.getTipo());
		
		t = scanner.nextToken();
		assertEquals(TokenType.ASSIGN, t.getTipo());
		
		t = scanner.nextToken();
		assertEquals(TokenType.MUL_ASSIGN, t.getTipo());
		
		t = scanner.nextToken();
		assertEquals(TokenType.SEMI, t.getTipo());
	}*/

}

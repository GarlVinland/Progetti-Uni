package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import token.Token;
import token.TokenType;

class TestToken {


	@Test
	void testCostruttoreNoVal() {
		Token tok1 = new Token(TokenType.TYINT, 3);
		Token tok2 = new Token(TokenType.TYFLOAT, 5);
		
		//System.out.println(tok1.toString());
		//System.out.println(tok2.toString());
		assertEquals("<TYINT, r:3>", tok1.toString());
		assertEquals("<TYFLOAT, r:5>", tok2.toString());
		
		assertEquals(3, tok1.getRiga());
		assertEquals(TokenType.TYINT, tok1.getTipo());
		

		assertEquals(5, tok2.getRiga());
		assertEquals(TokenType.TYFLOAT, tok2.getTipo());
	}
	
	@Test
	void testCostruttoreVal() {
		Token tok1 = new Token(TokenType.TYINT, 6, "tempa");
	
		//System.out.println(tok1.toString());
		assertEquals("<TYINT, r:6 , tempa>", tok1.toString());
		
		assertEquals(6, tok1.getRiga());
		assertEquals(TokenType.TYINT, tok1.getTipo());
		assertEquals("tempa", tok1.getVal());
		
	}

}

package project_01;

import static org.junit.Assert.assertEquals;

import java.util.EmptyStackException;

import org.junit.Test;

/**
 * Testing JUnit class for the Expression Converter class.
 * 
 * @author Kevin Pineda
 * @class CMSC 350-6381
 * @Project 1
 * @Date 05/24/2023
 */
public class ExpressionConverterTest {

	@Test
	public void testPreFixToPostFix() {
		ExpressionConverter ec = new ExpressionConverter("* 2 + 2 - + 12 9 2");
		String result = ec.convertToPostFix();
		assertEquals(result, "2 2 12 9 + 2 - + *");
	}

	@Test
	public void testPostFixToPrefix() {
		ExpressionConverter ec = new ExpressionConverter("2 2 12 9 + 2 - + *");
		String result = ec.convertToPreFix();
		assertEquals(result, "* 2 + 2 - + 12 9 2");
	}

	@Test
	public void testWithoutSpacesPostFix() {
		ExpressionConverter ec = new ExpressionConverter("*2+2-+12 9 2");
		String result = ec.convertToPostFix();
		assertEquals(result, "2 2 12 9 + 2 - + *");
	}

	@Test
	public void testWithoutSpacesPreFix() {
		ExpressionConverter ec = new ExpressionConverter("2 2 12 9+2-+*");
		String result = ec.convertToPreFix();
		assertEquals(result, "* 2 + 2 - + 12 9 2");
	}

	@Test(expected = EmptyStackException.class)
	public void testEmptyStackPreFix() {
		ExpressionConverter ec = new ExpressionConverter("* 2 + 2 - + 12 9 2");
		String result = ec.convertToPreFix();
	}

	@Test(expected = EmptyStackException.class)
	public void testEmptyStackPostFix() {
		ExpressionConverter ec = new ExpressionConverter("2 2 12 9 + 2 - + *");
		String result = ec.convertToPostFix();
	}

	@Test(expected = StackSyntaxError.class)
	public void testNonEmptyStackPreFix() {
		ExpressionConverter ec = new ExpressionConverter("2 2 2 2 2");
		String result = ec.convertToPreFix();
	}

	@Test(expected = StackSyntaxError.class)
	public void testNonEmptyStackPostFix() {
		ExpressionConverter ec = new ExpressionConverter("2 2 2 2 2");
		String result = ec.convertToPostFix();
	}
}

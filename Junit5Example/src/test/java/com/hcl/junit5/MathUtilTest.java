package com.hcl.junit5;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestReporter;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class MathUtilTest {
	
	MathUtil mathUtil;
	TestInfo testInfo;
	TestReporter testReporter;
	
	@BeforeAll
	void beforeAllInti() {
		System.out.println("This method run before all the metho");
	}
	
	@BeforeEach
	void init(TestReporter testReporter,TestInfo testInfo) {
		mathUtil = new MathUtil();
		System.out.println(testInfo.getDisplayName()+" "+testInfo.getTestMethod());
		testReporter.publishEntry("Running"+" "+testInfo.getDisplayName()+" "+testInfo.getTestMethod());
		System.out.println("This needs to be run before all the methods");
	}
	
	@AfterEach
	void clean() {
		System.out.println("Clean Up activity");
	}
	@Nested
	@DisplayName("Add test Nested class")
	class Addtest{
		@Test
		@DisplayName("Add Test method for positivr numbers")
		void testAddPositiveNumbers() {
			assertEquals(2, mathUtil.add(1, 1),"The add method should be add two numbers");
		}
		
		@Test
		@DisplayName("Add Test method for Negative Numbers")
		void testAddNegativeNumbers() {
			assertEquals(-5,  mathUtil.add(-2, -3),"The add method should be add two numbers");
		}
		
		@Test
		@DisplayName("Add Test method for Negative Numbers")
		void testAddTwoNumbers() {
			//() -> "Expected is -6 but method returns "+mathUtil.add(-2, -3) //This comes only test case become failed
			assertEquals(-5,  mathUtil.add(-2, -3),() -> "Expected is -6 but method returns "+mathUtil.add(-2, -3));
		}
	}
	
	//This test case run 3 times
	@RepeatedTest(3)
	@Test
	@Disabled
	void computeAreaOfCircleTest(RepetitionInfo info) {
		double expected = 314.1592653589793;
		double actual = mathUtil.computAreaOfCircle(10);
		System.out.println("Currenct Repetition "+ info.getCurrentRepetition());
		System.out.println("Total Repetition "+ info.getTotalRepetitions());
		assertEquals(expected, actual,"The computeAreaOfCircle method should give area of circle");
	}
	
	@Test
	void testDivide() {
		assertThrows(ArithmeticException.class, () -> mathUtil.divide(1, 0),"Divid by Zero should throw Exception");
	}
	
	@Test
	@Disabled
	@DisplayName("Test case under construction")
	public void disableMethod() {
		fail("Test case under construction");
	}
	
	@Test
	@DisplayName("Multiply Test method")
	public void multiplyTest() {
		assertAll(
				() -> assertEquals(10, mathUtil.multiply(5, 2)),
				() -> assertEquals(0, mathUtil.multiply(5, 0)),
				() -> assertEquals(-5, mathUtil.multiply(5, -1))
				);
	}
}

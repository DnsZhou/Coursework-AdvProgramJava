
/**
 * 
 * @author: Tong Zhou b8027512@ncl.ac.uk
 * @created: 13:20 21-10-2018
 */
package uk.ac.ncl.tongzhou.advancedjava.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import uk.ac.ncl.tongzhou.advancedjava.model.CarRegistrationNumber;

/**
 *  CarRegistrationNumberTest
 * 
 * 
 */
public class CarRegistrationNumberTest {

	/**
	 * 
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * Test method for
	 * {@link uk.ac.ncl.tongzhou.advancedjava.model.CarRegistrationNumber#getInstance(java.lang.String)}.
	 */
	@Test
	public void test() throws IllegalArgumentException, IllegalStateException {
		CarRegistrationNumber crn = CarRegistrationNumber.getInstance("   MS03	  DSA  ");
		assertEquals("MS03", crn.getComp1());
		assertEquals("DSA", crn.getComp2());
	}
	
	/**
	 * Test method for NullArgument
	 * {@link uk.ac.ncl.tongzhou.advancedjava.model.CarRegistrationNumber#getInstance(java.lang.String)}.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testNullArgument() throws IllegalArgumentException, IllegalStateException {
		CarRegistrationNumber crn = CarRegistrationNumber.getInstance(null);
	}
	
	/**
	 * Test method for WrongFormat
	 * {@link uk.ac.ncl.tongzhou.advancedjava.model.CarRegistrationNumber#getInstance(java.lang.String)}.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testWrongFormat() throws IllegalArgumentException, IllegalStateException {
		CarRegistrationNumber crn = CarRegistrationNumber.getInstance("   MSs03	  DSA  ");
	}
	
	/**
	 * Test method for DuplicateCrn
	 * {@link uk.ac.ncl.tongzhou.advancedjava.model.CarRegistrationNumber#getInstance(java.lang.String)}.
	 */
	@Test(expected = IllegalStateException.class)
	public void testDuplicateCrn() throws IllegalArgumentException, IllegalStateException {
		CarRegistrationNumber crn1 = CarRegistrationNumber.getInstance("   MS03	  DSA  ");
		CarRegistrationNumber crn2 = CarRegistrationNumber.getInstance("   MS03	  DSA  ");
	}

}

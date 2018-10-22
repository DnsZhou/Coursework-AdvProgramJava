
/**
 * @Description: 
 * @author: Tong Zhou b8027512@ncl.ac.uk
 * @created: 13:20 21-10-2018
 */
package uk.ac.ncl.TongZhou.advProgmJava.Test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import uk.ac.ncl.TongZhou.advProgmJava.Entity.CarRegistrationNumber;

/**
 * @ClassName: CarRegistrationNumberTest 
 * @Description: 
 * 
 */
public class CarRegistrationNumberTest {

	/**   
	 * @Title: setUp   
	 * @Description: 
	 * @throws java.lang.Exception         
	 */
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() throws IllegalArgumentException, Exception {
		CarRegistrationNumber crn = CarRegistrationNumber.getInstance("   MS03	  DSA  ");
		assertEquals("MS03", crn.getComp1());
		assertEquals("DSA", crn.getComp2());
	}

}


/**
 * @Description: 
 * @author: Tong Zhou b8027512@ncl.ac.uk
 * @created: 23:48 16-10-2018
 */
package uk.ac.ncl.tongzhou.advancedjava.test;


import static org.junit.Assert.assertEquals;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.Before;
import org.junit.Test;

import uk.ac.ncl.tongzhou.advancedjava.model.DrivingLicence;

/**
 * @ClassName: TestDrivingLicence
 * @Description:
 * 
 */
public class DrivingLicenceTest {

	/**   
	 * @Title: setUp   
	 * @Description: 
	 * @throws java.lang.Exception         
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * Test method for testConstructor
	 * {@link uk.ac.ncl.tongzhou.advancedjava.model.DrivingLicence#getInstance()}.
	 * @throws Exception,IllegalArgumentException
	 */
	@Test
	public void test() throws Exception, IllegalArgumentException {
		Calendar date = new GregorianCalendar(1993, 9, 20);
		DrivingLicence dl = DrivingLicence.getInstance("MS-2000-98",date.getTime(),true);
		assertEquals("MS-2000-98", dl.getLicenceNumber());
		assertEquals(date.getTime(), dl.getIssueDate());
		assertEquals(true, dl.isFullLicence());
	}
	
	/**
	 * Test method for testDuplicatedNumber
	 * {@link uk.ac.ncl.tongzhou.advancedjava.model.DrivingLicence#getInstance()}.
	 * @throws Exception,IllegalArgumentException
	 */
	@Test(expected = Exception.class)
	public void testDuplicatedNumber() throws Exception,IllegalArgumentException {
		Calendar date = new GregorianCalendar(1993, 9, 20);
		DrivingLicence dl1 = DrivingLicence.getInstance("MS-2000-21",date.getTime(),true);
		DrivingLicence dl2 = DrivingLicence.getInstance("MS-2000-21",date.getTime(),true);
	}
	
	/**
	 * Test method for testNullArguments
	 * {@link uk.ac.ncl.tongzhou.advancedjava.model.DrivingLicence#getInstance()}.
	 * @throws Exception,IllegalArgumentException 
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testNullArguments() throws Exception,IllegalArgumentException {
		Calendar date = new GregorianCalendar(1993, 9, 20);
		DrivingLicence dl = DrivingLicence.getInstance(null,date.getTime(),true);
	}
	
	
	
}

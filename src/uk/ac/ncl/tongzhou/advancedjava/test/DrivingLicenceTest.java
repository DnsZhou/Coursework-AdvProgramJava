
/**
 * @Description: 
 * @author: Tong Zhou b8027512@ncl.ac.uk
 * @created: 23:48 16-10-2018
 */
package uk.ac.ncl.tongzhou.advancedjava.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.regex.Pattern;

import org.junit.Before;
import org.junit.Test;

import uk.ac.ncl.tongzhou.advancedjava.model.DrivingLicence;
import uk.ac.ncl.tongzhou.advancedjava.model.Person;

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
	 * {@link uk.ac.ncl.tongzhou.advancedjava.model.DrivingLicence#getInstance(String licenceNumber, Date issueDate, boolean isFullLicence)}.
	 * 
	 * @throws IllegalStateException
	 * @throws IllegalArgumentException
	 */
	@Test
	public void test() throws IllegalStateException, IllegalArgumentException {
		Calendar date = new GregorianCalendar(1993, 9, 20);
		DrivingLicence dl = DrivingLicence.getInstance("MS-1993-98", date.getTime(), true);
		assertEquals("MS-1993-98", dl.getLicenceNumber());
		assertEquals(date.getTime(), dl.getIssueDate());
		assertEquals(true, dl.isFullLicence());
	}

	/**
	 * Test method for testConstructor
	 * {@link uk.ac.ncl.tongzhou.advancedjava.model.DrivingLicence#getInstance(Person driver, Date issueDate, boolean isFullLicence)}.
	 * 
	 * @throws IllegalStateException
	 * @throws IllegalArgumentException
	 */
	@Test
	public void testGetInstanceWithPerson() throws IllegalStateException, IllegalArgumentException {
		Calendar date = new GregorianCalendar(1993, 9, 20);
		Person person = new Person("Tong", "Zhou", date.getTime());

		DrivingLicence dl = DrivingLicence.getInstance(person, date.getTime(), false);
		Pattern p = Pattern.compile("TZ-1993-\\d{2}");
		assertTrue(p.matcher(dl.getLicenceNumber()).matches());

		assertEquals(date.getTime(), dl.getIssueDate());
		assertEquals(false, dl.isFullLicence());

	}

	/**
	 * Test method for testDuplicatedNumber
	 * {@link uk.ac.ncl.tongzhou.advancedjava.model.DrivingLicence#getInstance(String licenceNumber, Date issueDate, boolean isFullLicence)}.
	 * 
	 * @throws IllegalStateException
	 * @throws IllegalArgumentException
	 */
	@Test(expected = IllegalStateException.class)
	public void testDuplicatedNumber() throws IllegalStateException, IllegalArgumentException {
		Calendar date = new GregorianCalendar(1993, 9, 20);
		DrivingLicence dl1 = DrivingLicence.getInstance("MS-1993-21", date.getTime(), true);
		DrivingLicence dl2 = DrivingLicence.getInstance("MS-1993-21", date.getTime(), true);
	}

	/**
	 * Test method for testNullArguments
	 * {@link uk.ac.ncl.tongzhou.advancedjava.model.DrivingLicence#getInstance(String licenceNumber, Date issueDate, boolean isFullLicence)}.
	 * 
	 * @throws IllegalStateException
	 * @throws IllegalArgumentException
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testNullArguments() throws IllegalStateException, IllegalArgumentException {
		DrivingLicence dl = DrivingLicence.getInstance("", null, true);
	}

}


/**
 * 
 * @author: Tong Zhou b8027512@ncl.ac.uk
 * @created: 23:48 16-10-2018
 */
package uk.ac.ncl.tongzhou.advancedjava.test;

import static org.junit.Assert.assertEquals;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.Before;
import org.junit.Test;

import uk.ac.ncl.tongzhou.advancedjava.model.Person;

/**
 * TestPerson
 * 
 * 
 */
public class PersonTest {

	/**
	 * 
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * Test method for
	 * {@link uk.ac.ncl.tongzhou.advancedjava.model.Person#Person(String firstName, String lastName, Date date)}.
	 */
	@Test
	public void testConstructorNormal() {
		// use Calendar to generate a new date and convert to java.util.Date.
		Calendar date = new GregorianCalendar(1993, 10, 20);
		Person person = new Person("Tong", "Zhou", date.getTime());
		assertEquals("Person [firstName=Tong, lastName=Zhou, dateOfBirth=1993-11-20]", person.toString());
	}

	/**
	 * Test null Argument exception for
	 * {@link uk.ac.ncl.tongzhou.advancedjava.model.Person#Person(String firstName, String lastName, Date date)}.
	 * 
	 * @throws IllegalArgumentException
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testConstructorException() throws IllegalArgumentException {
		Calendar date = new GregorianCalendar(1993, 9, 20);
		Person person = new Person("Tong", null, date.getTime());
	}
}

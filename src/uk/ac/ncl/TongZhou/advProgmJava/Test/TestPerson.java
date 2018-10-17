
/**
 * @Description: 
 * @author: Tong Zhou b8027512@ncl.ac.uk
 * @created: 23:48 16-10-2018
 */
package uk.ac.ncl.TongZhou.advProgmJava.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uk.ac.ncl.TongZhou.advProgmJava.Entity.Person;

/**
 * @ClassName: TestPerson
 * @Description:
 * 
 */
class TestPerson {

	/**
	 * @Title: setUp
	 * @Description: 
	 * @throws java.lang.Exception
	 * @return: void
	 */
	@BeforeEach
	void setUp() throws Exception {
	}

	/**
	 * Test method for
	 * {@link uk.ac.ncl.TongZhou.advProgmJava.Entity.Person#Person()}.
	 */
	@Test
	void testConstructorNormal() {
		// use Calendar to generate a new date and convert to java.util.Date.
		Calendar date = new GregorianCalendar(1993, 10, 20);
		Person person = new Person("Tong", "Zhou", date.getTime());
		assertEquals("Person [firstName=Tong, lastName=Zhou, dateOfBirth=1993-11-20]",
				person.toString());
	}

	/**
	 * Test null Argument exception for
	 * {@link uk.ac.ncl.TongZhou.advProgmJava.Entity.Person#Person()}.
	 */
	@Test
	void testConstructorException() {
		Calendar date = new GregorianCalendar(1993, 10, 20);
		assertThrows(IllegalArgumentException.class, () -> {
			Person person = new Person(null, "Zhou", date.getTime());
		});
		assertThrows(IllegalArgumentException.class, () -> {
			Person person = new Person("Tong", null, date.getTime());
		});
		assertThrows(IllegalArgumentException.class, () -> {
			Person person = new Person("Zhou", "Zhou", null);
		});
	}

}

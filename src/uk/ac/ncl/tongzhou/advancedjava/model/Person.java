/**
 * @Description: 
 * @author: Tong Zhou b8027512@ncl.ac.uk
 * @created: 15:10 16-10-2018
 */
package uk.ac.ncl.tongzhou.advancedjava.model;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @ClassName: Person
 * @Description: A person (or customer) with name and a date of birth.
 * 
 */
public class Person {
	private String firstName;
	private String lastName;
	private Date dateOfBirth;

	/**
	 * @Title: Constructor for Person
	 * @param firstName
	 * @param lastName
	 * @param date
	 * @throws IllegalArgumentException
	 */
	public Person(String firstName, String lastName, Date date) throws IllegalArgumentException {
		super();
		if (firstName == null || lastName == null || date == null)
			throw new IllegalArgumentException("Negative First Name, Last Name or Date of Birth");
		this.firstName = firstName;
		this.lastName = lastName;

		// for immutable purpose.
		this.dateOfBirth = new Date(date.getTime());
	}

	/**
	 * Return the firstName.
	 *
	 * @return firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Return the lastName.
	 *
	 * @return lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Return the dateOfBirth.
	 *
	 * @return dateOfBirth
	 */
	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	/**
	 * @Title: toString
	 * @Description:
	 * @return
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Person [firstName=" + firstName + ", lastName=" + lastName + ", dateOfBirth="
				+ DateFormat.getDateInstance().format(dateOfBirth) + "]";
	}
}

/**
 * 
 * @author: Tong Zhou b8027512@ncl.ac.uk
 * @created: 15:10 16-10-2018
 */
package uk.ac.ncl.tongzhou.advancedjava.model;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 *  Person
 * A person (or customer) with name and a date of birth.
 * 
 */
public class Person {
	private String firstName;
	private String lastName;
	private Date dateOfBirth;

	/**
	 * @param firstName
	 * @param lastName
	 * @param dateOfBirth
	 * @throws IllegalArgumentException
	 */
	public Person(String firstName, String lastName, Date dateOfBirth) throws IllegalArgumentException {
		super();
		if (firstName == null || lastName == null || dateOfBirth == null)
			throw new IllegalArgumentException("Negative First Name, Last Name or Date of Birth");
		this.firstName = firstName;
		this.lastName = lastName;

		// for immutable purpose.
		this.dateOfBirth = new Date(dateOfBirth.getTime());
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
	 * 
	 * @return String
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Person [firstName=" + firstName + ", lastName=" + lastName + ", dateOfBirth="
				+ DateFormat.getDateInstance().format(dateOfBirth) + "]";
	}

	/**
	 * override the equals function; Two people are considered to be
	 *              the same if they have the same name and the same date of birth.
	 * @param obj
	 * @return the equals function
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		else if (obj instanceof Person) {
			Person person = (Person) obj;
			return (this.firstName == person.firstName && this.lastName == person.lastName
					&& this.dateOfBirth.equals(person.dateOfBirth));
		}
		return super.equals(obj);
	}

	/**
	 * override the hashCode function;
	 * @return  override the hashCode function
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		int hc = 17;
		int multiplier = 37;
		hc = multiplier * hc + firstName.hashCode();
		hc = multiplier * hc + lastName.hashCode();
		return multiplier * hc + dateOfBirth.hashCode();
	}
}

/**
 * @Description: 
 * @author: Tong Zhou b8027512@ncl.ac.uk
 * @created: 15:10 16-10-2018
 */
package uk.ac.ncl.TongZhou.advProgmJava.Entity;

/**
 * @ClassName: Person 
 * @Description: 
 * 
 */
public class Person {
	private String firstName;
	private String lastName;
	
	/** 
	 * Return the firstName.
	 *
	 * @return firstName 
	 */
	public String getFirstName() {
		return firstName;
	}
	
	/** 
	 * Set the value of firstName
	 *
	 * @param firstName: firstName to be set.
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
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
	 * Set the value of lastName
	 *
	 * @param lastName: lastName to be set.
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

}

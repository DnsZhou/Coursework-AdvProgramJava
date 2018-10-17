/**
 * @Description: 
 * @author: Tong Zhou b8027512@ncl.ac.uk
 * @created: 15:51 16-10-2018
 */
package uk.ac.ncl.TongZhou.advProgmJava.Entity;

import java.sql.Date;

/**
 * @ClassName: DrivingLicence 
 * @Description: A driving licence has a unique number, a date of issue, and an indication whether the licence is a full
 * driving licence or not.
 * 
 */
public class DrivingLicence {
	private String licenceNumber;
	private Date issueDate;
	private boolean isFullLicence;
	
	public DrivingLicence(Person driver, Date issueDate, boolean isFullLicence) {
		
	};
	
}

/**
 * @Description 
 * @author Tong Zhou b8027512@ncl.ac.uk
 * @created 15:51 16-10-2018
 */
package uk.ac.ncl.tongzhou.advancedjava.model;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * @ClassName DrivingLicence
 * @Description A driving licence has a unique number, a date of issue, and an
 *               indication whether the licence is a full driving licence or
 *               not.
 * 
 */
public class DrivingLicence {

	/**
	 * @Fields licenceNumber : TODO
	 */
	private String licenceNumber;
	private Date issueDate;
	private boolean isFullLicence;
	private static Map<String, DrivingLicence> drivingLicences;

	/**
	 * @Title Constructor for DrivingLicence
	 * @Description
	 * @param licenceNumber
	 * @param issueDate
	 * @param isFullLicence
	 */
	private DrivingLicence(String licenceNumber, Date issueDate, boolean isFullLicence) {
		super();
		this.licenceNumber = licenceNumber;
		this.issueDate = new Date(issueDate.getTime());
		this.isFullLicence = isFullLicence;
	}

	/**
	 * @Title getInstance
	 * @Description
	 * @param licenceNumber
	 * @param issueDate
	 * @param isFullLicence
	 * @return
	 * @throws Exception
	 */
	public static DrivingLicence getInstance(String licenceNumber, Date issueDate, boolean isFullLicence)
			throws IllegalStateException, IllegalArgumentException {
		if (licenceNumber == null || issueDate == null)
			throw new IllegalArgumentException("Null Argument for licenceNumber or issueDate.");

		String licenceNumberTrim = licenceNumber.trim();
		if (!validateDrivingLicenceNumber(licenceNumberTrim)) {
			throw new IllegalArgumentException("Wrong format for Driving Licence Number.");
		}

		if (drivingLicences == null) {
			drivingLicences = new HashMap<String, DrivingLicence>();
		}
		DrivingLicence dl = drivingLicences.get(licenceNumberTrim);
		if (dl != null) {
			throw new IllegalStateException("Duplicate Licence Number, please check.");
		} else {
			dl = new DrivingLicence(licenceNumberTrim, issueDate, isFullLicence);
			drivingLicences.put(licenceNumberTrim, dl);
			return dl;
		}
	}

	private static boolean validateDrivingLicenceNumber(String drvLicNum) {
		Pattern p = Pattern.compile("[A-Za-z]{2}-\\d{4}-\\d{2}");
		return p.matcher(drvLicNum).matches();
	}

	/**
	 * Return the licenceNumber.
	 *
	 * @return licenceNumber
	 */
	public String getLicenceNumber() {
		return licenceNumber;
	}

	/**
	 * Return the issueDate.
	 *
	 * @return issueDate
	 */
	public Date getIssueDate() {
		return issueDate;
	}

	/**
	 * Return the isFullLicence.
	 *
	 * @return isFullLicence
	 */
	public boolean isFullLicence() {
		return isFullLicence;
	}

	/**
	 * @Title equals
	 * @Description override the equals function;
	 * @param obj
	 * @return
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		else if (obj instanceof DrivingLicence) {
			DrivingLicence dl = (DrivingLicence) obj;
			return (this.licenceNumber == dl.licenceNumber);
		}
		return super.equals(obj);
	}

	/**
	 * @Title hashCode
	 * @Description override the hashCode function;
	 * @return
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		int hc = 17;
		int multiplier = 37;
		return multiplier * hc + licenceNumber.hashCode();
	}

}

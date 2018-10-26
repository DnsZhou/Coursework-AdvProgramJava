/**
 * 
 * @author Tong Zhou b8027512@ncl.ac.uk
 * @created 15:51 16-10-2018
 */
package uk.ac.ncl.tongzhou.advancedjava.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import nl.flotsam.xeger.Xeger;

/**
 *  DrivingLicence
 * A driving licence has a unique number, a date of issue, and an
 *              indication whether the licence is a full driving licence or not.
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
	 * getInstance function with defined licenceNumber.
	 * @param licenceNumber
	 * @param issueDate
	 * @param isFullLicence
	 * @return DrivingLicence
	 * @throws IllegalStateException
	 * @throws IllegalArgumentException
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

	/**
	 * getInstance function with a person, and automatically generate
	 *              the driver licence number that aligns to the rules.
	 * @param driver
	 * @param issueDate
	 * @param isFullLicence
	 * @return DrivingLicence
	 * @throws IllegalStateException
	 * @throws IllegalArgumentException
	 */
	public static DrivingLicence getInstance(Person driver, Date issueDate, boolean isFullLicence)
			throws IllegalStateException, IllegalArgumentException {
		if (driver == null || issueDate == null)
			throw new IllegalArgumentException("Null Argument for driver or issueDate.");
		if (drivingLicences == null) {
			drivingLicences = new HashMap<String, DrivingLicence>();
		}

		String initial = driver.getFirstName().substring(0, 1) + driver.getLastName().substring(0, 1);

		SimpleDateFormat df = new SimpleDateFormat("yyyy");
		int issueYear = Integer.parseInt(df.format(issueDate));

		String licenceNumber;
		String regex = "[0-9]{2}";
		Xeger generator = new Xeger(regex);

		// guarantees the uniqueness of the licence number as a whole.
		do {
			String serial = generator.generate();
			licenceNumber = initial + "-" + issueYear + "-" + serial;
		} while (drivingLicences.get(licenceNumber) != null);

		DrivingLicence dl = new DrivingLicence(licenceNumber, issueDate, isFullLicence);
		drivingLicences.put(licenceNumber, dl);
		return dl;
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
	 * override the equals function;
	 * @param obj
	 * @return override the equals function
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
	 * override the hashCode function;
	 * @return override the hashCode function
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		int hc = 17;
		int multiplier = 37;
		return multiplier * hc + licenceNumber.hashCode();
	}

}

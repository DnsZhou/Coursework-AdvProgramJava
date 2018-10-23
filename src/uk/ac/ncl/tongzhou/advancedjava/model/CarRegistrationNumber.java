
/**
 * @Description: 
 * @author: Tong Zhou b8027512@ncl.ac.uk
 * @created: 23:52 16-10-2018
 */
package uk.ac.ncl.tongzhou.advancedjava.model;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * @ClassName: CarRegistrationNumber
 * @Description: A car registration number has two components. The first
 *               component is two letters followed by two digits. The second
 *               component is three letters. Registration numbers are unique. No
 *               two cars have the same registration number.
 * 
 */
public final class CarRegistrationNumber {
	private static Map<String, CarRegistrationNumber> allCarRegistrationNumbers;
	private final String comp1;
	private final String comp2;

	/**
	 * @Title: Constructor for CarRegistrationNumber
	 * @param comp1
	 * @param comp2
	 */
	private CarRegistrationNumber(String comp1, String comp2) {
		super();
		this.comp1 = comp1;
		this.comp2 = comp2;
	}

	/**
	 * @Title: getInstance
	 * @param String
	 *            carRegistrationNumber
	 * @throws IllegalArgumentException
	 * @return: CarRegistrationNumber
	 */
	public static CarRegistrationNumber getInstance(String carRegistrationNumber)
			throws IllegalArgumentException, Exception {

		if (carRegistrationNumber == null)
			throw new IllegalArgumentException("carRegistrationNumber should not be null.");
		
		String carRegistrationNumbertrim = carRegistrationNumber.trim();
		if (!validateCarRegNumber(carRegistrationNumbertrim))
			throw new IllegalArgumentException("carRegistrationNumber : argument format not validated");

		if (allCarRegistrationNumbers == null)
			allCarRegistrationNumbers = new HashMap<String, CarRegistrationNumber>();

		
		String comp1 = carRegistrationNumbertrim.substring(0, 4);
		String comp2 = carRegistrationNumbertrim.substring(carRegistrationNumbertrim.length() - 3);
		String key = comp1 + comp2;

		CarRegistrationNumber crn = allCarRegistrationNumbers.get(key);
		if (crn != null) {
			throw new Exception("Duplicate Car Registration Number, please check.");
		} else {
			crn = new CarRegistrationNumber(comp1, comp2);
			allCarRegistrationNumbers.put(key, crn);
		}
		return crn;
	}
	
	

	
	/** 
	 * Return the allCarRegistrationNumbers.
	 *
	 * @return allCarRegistrationNumbers 
	 */
	public static Map<String, CarRegistrationNumber> getAllCarRegistrationNumbers() {
		return allCarRegistrationNumbers;
	}

	
	/** 
	 * Return the comp1.
	 *
	 * @return comp1 
	 */
	public String getComp1() {
		return comp1;
	}

	
	/** 
	 * Return the comp2.
	 *
	 * @return comp2 
	 */
	public String getComp2() {
		return comp2;
	}

	/**
	 * @Title: toString
	 * @Description:
	 * @return
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CarRegistrationNumber [comp1=" + comp1 + ", comp2=" + comp2 + "]";
	}

	private static boolean validateCarRegNumber(String carRegistrationNumber) {
		Pattern p = Pattern.compile("[A-Za-z]{2}\\d{2}\\s*[A-Za-z]{3}");
		return p.matcher(carRegistrationNumber).matches();
	}
}

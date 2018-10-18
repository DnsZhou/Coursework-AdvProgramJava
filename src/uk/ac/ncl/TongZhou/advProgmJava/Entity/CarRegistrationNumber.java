
/**
 * @Description: 
 * @author: Tong Zhou b8027512@ncl.ac.uk
 * @created: 23:52 16-10-2018
 */
package uk.ac.ncl.TongZhou.advProgmJava.Entity;

import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.regex.Pattern;

import org.junit.jupiter.params.converter.ArgumentConversionException;

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
	 * @param String carRegistrationNumber
	 * @throws IllegalArgumentException      
	 * @return: CarRegistrationNumber      
	 */
	public static CarRegistrationNumber getInstance(String carRegistrationNumber) throws IllegalArgumentException {
		if (allCarRegistrationNumbers == null)
			allCarRegistrationNumbers = new HashMap<String, CarRegistrationNumber>();

		if (carRegistrationNumber == null)
			throw new IllegalArgumentException("carRegistrationNumber should not be null.");

		String carRegistrationNumbertrim = carRegistrationNumber.trim();
		if (!validateCarRegNumber(carRegistrationNumbertrim))
			throw new IllegalArgumentException("carRegistrationNumber : argument format not validated");

		String comp1 = carRegistrationNumbertrim.substring(0, 4);
		String comp2 = carRegistrationNumbertrim.substring(carRegistrationNumbertrim.length() - 3);
		String key = comp1 + comp2;

		CarRegistrationNumber crn = allCarRegistrationNumbers.get(key);
		if (crn == null) {
			crn = new CarRegistrationNumber(comp1, comp2);
			allCarRegistrationNumbers.put(key, crn);
		} else {
			
		}
		return crn;
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
		Pattern p = Pattern.compile("[A-Z]{2}\\d{2}\\s*[A-Z]{3}");
		return p.matcher(carRegistrationNumber).matches();
	}
}

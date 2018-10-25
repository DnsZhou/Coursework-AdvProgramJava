
/**
 * @Description  
 * @author Tong Zhou b8027512@ncl.ac.uk
 * @created 03:24 24-10-2018
 */
package uk.ac.ncl.tongzhou.advancedjava.test;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import uk.ac.ncl.tongzhou.advancedjava.model.CarRentalCompany;
import uk.ac.ncl.tongzhou.advancedjava.model.TypeOfCar;

/**
 * @ClassName TestCarRentalCompany
 * @Description
 * 
 */
public class CarRentalCompanyTest {

	/**
	 * @Title setUp
	 * @Description
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}
	
	/**
	 * Test method for testConstructor
	 * {@link uk.ac.ncl.tongzhou.advancedjava.model.DrivingLicence#getInstance(String licenceNumber, Date issueDate, boolean isFullLicence)}.
	 * 
	 * @throws IllegalStateException
	 * @throws IllegalArgumentException
	 */
	@Test
	public void test() {
		CarRentalCompany carRentalCompany = new CarRentalCompany();
		carRentalCompany.initializeCarFleet();
		assertEquals(20,carRentalCompany.availableCars(TypeOfCar.SMALL_CAR));
		assertEquals(10,carRentalCompany.availableCars(TypeOfCar.LARGE_CAR));
	}

}

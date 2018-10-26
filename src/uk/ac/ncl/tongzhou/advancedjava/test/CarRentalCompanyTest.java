
/**
 * @Description  
 * @author Tong Zhou b8027512@ncl.ac.uk
 * @created 03:24 24-10-2018
 */
package uk.ac.ncl.tongzhou.advancedjava.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.Before;
import org.junit.Test;

import uk.ac.ncl.tongzhou.advancedjava.model.CarRentalCompany;
import uk.ac.ncl.tongzhou.advancedjava.model.DrivingLicence;
import uk.ac.ncl.tongzhou.advancedjava.model.Person;
import uk.ac.ncl.tongzhou.advancedjava.model.TypeOfCar;
import uk.ac.ncl.tongzhou.advancedjava.model.car.Car;

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
	 * Test method for initializeCarFleet
	 * {@link uk.ac.ncl.tongzhou.advancedjava.model.CarRentalCompany#initializeCarFleet()}.
	 * 
	 */
	@Test
	public void testInitializeCarFleet() {
		CarRentalCompany carRentalCompany = new CarRentalCompany();
		carRentalCompany.initializeCarFleet();
		assertEquals(20, carRentalCompany.availableCars(TypeOfCar.SMALL_CAR));
		assertEquals(10, carRentalCompany.availableCars(TypeOfCar.LARGE_CAR));
	}

	/**
	 * Test method for availableCars null argument exception
	 * {@link uk.ac.ncl.tongzhou.advancedjava.model.CarRentalCompany#availableCars(TypeOfCar type)}.
	 * 
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testAvailableCars() {
		CarRentalCompany carRentalCompany = new CarRentalCompany();
		carRentalCompany.initializeCarFleet();
		carRentalCompany.availableCars(null);
	}

	/**
	 * Test method for getRentedCars
	 * {@link uk.ac.ncl.tongzhou.advancedjava.model.CarRentalCompany#getRentedCars()}.
	 * 
	 */
	@Test
	public void testGetRentedCars() {
		CarRentalCompany carRentalCompany = new CarRentalCompany();
		carRentalCompany.initializeCarFleet();
	}

	/**
	 * Test method for getCar
	 * {@link uk.ac.ncl.tongzhou.advancedjava.model.CarRentalCompany#getCar(Person person)}.
	 * 
	 */
	@Test
	public void testGetCar() {
		CarRentalCompany carRentalCompany = new CarRentalCompany();
		carRentalCompany.initializeCarFleet();
	}

	/**
	 * Test method for issueCar
	 * {@link uk.ac.ncl.tongzhou.advancedjava.model.CarRentalCompany#issueCar(Person person, DrivingLicence drivingLicence, TypeOfCar type)}.
	 * 
	 */
	@Test
	public void testIssueCar() {
		CarRentalCompany carRentalCompany = new CarRentalCompany();
		carRentalCompany.initializeCarFleet();
		Calendar dateBirth = new GregorianCalendar(1993, 9, 20);
		Calendar dateIssue = new GregorianCalendar(2012, 9, 20);
		Person person = new Person("Tong", "Zhou", dateBirth.getTime());
		DrivingLicence dl1 = DrivingLicence.getInstance(person, dateIssue.getTime(), true);
		carRentalCompany.issueCar(person, dl1, TypeOfCar.LARGE_CAR);
		Car rentedCar = carRentalCompany.getCar(person);
		assertTrue(rentedCar != null && rentedCar.getRenter().equals(person));
	}

	/**
	 * Test method for issueCar with renter not satisfy the requirement for Small
	 * cars. To rent a small car, they must be at least 20 years old and must have
	 * held their licence for at least 1 year
	 * {@link uk.ac.ncl.tongzhou.advancedjava.model.CarRentalCompany#issueCar(Person person, DrivingLicence drivingLicence, TypeOfCar type)}.
	 * 
	 */
	@Test
	public void testIssueCarUnsatisfySmallCar() {
		CarRentalCompany carRentalCompany = new CarRentalCompany();
		carRentalCompany.initializeCarFleet();
		Calendar date19Year = new GregorianCalendar(1999, 9, 20);
		Calendar date20Year = new GregorianCalendar(1998, 9, 20);
		Calendar date1Year = new GregorianCalendar(2017, 9, 20);
		Calendar date0Year = new GregorianCalendar(2018, 9, 20);

		Person person19 = new Person("Tong", "Zhou1", date19Year.getTime());
		Person person20 = new Person("Tong", "Zhou2", date20Year.getTime());

		DrivingLicence licence1year = DrivingLicence.getInstance(person20, date1Year.getTime(), true);
		DrivingLicence licence0year = DrivingLicence.getInstance(person20, date0Year.getTime(), true);

		// To rent a small car, they must be at least 20 years old and must have held
		// their licence for at least 1 year
		assertFalse(carRentalCompany.issueCar(person19, licence0year, TypeOfCar.SMALL_CAR));
		assertFalse(carRentalCompany.issueCar(person19, licence1year, TypeOfCar.SMALL_CAR));
		assertFalse(carRentalCompany.issueCar(person20, licence0year, TypeOfCar.SMALL_CAR));
		assertTrue(carRentalCompany.issueCar(person20, licence1year, TypeOfCar.SMALL_CAR));
	}

	/**
	 * Test method for issueCar with renter not satisfy the requirement for Large
	 * cars. To rent a large car, they must be at least 25 years old and must have
	 * held their licence for at least 5 years
	 * {@link uk.ac.ncl.tongzhou.advancedjava.model.CarRentalCompany#issueCar(Person person, DrivingLicence drivingLicence, TypeOfCar type)}.
	 * 
	 */
	@Test
	public void testIssueCarUnsatisfyLargeCar() {
		CarRentalCompany carRentalCompany = new CarRentalCompany();
		carRentalCompany.initializeCarFleet();
		Calendar date24Year = new GregorianCalendar(1994, 9, 20);
		Calendar date25Year = new GregorianCalendar(1993, 9, 20);
		Calendar date5Year = new GregorianCalendar(2013, 9, 20);
		Calendar date4Year = new GregorianCalendar(2014, 9, 20);

		Person person24 = new Person("Tong", "Zhou1", date24Year.getTime());
		Person person25 = new Person("Tong", "Zhou2", date25Year.getTime());

		DrivingLicence licence5year = DrivingLicence.getInstance(person25, date5Year.getTime(), true);
		DrivingLicence licence4year = DrivingLicence.getInstance(person25, date4Year.getTime(), true);

		// To rent a large car, they must be at least 25 years old and must have held
		// their licence for at least 5 years
		assertFalse(carRentalCompany.issueCar(person24, licence4year, TypeOfCar.LARGE_CAR));
		assertFalse(carRentalCompany.issueCar(person24, licence5year, TypeOfCar.LARGE_CAR));
		assertFalse(carRentalCompany.issueCar(person25, licence4year, TypeOfCar.LARGE_CAR));
		assertTrue(carRentalCompany.issueCar(person25, licence5year, TypeOfCar.LARGE_CAR));
	}

	/**
	 * Test method for issueCar IllegalArgumentException
	 * {@link uk.ac.ncl.tongzhou.advancedjava.model.CarRentalCompany#issueCar(Person person, DrivingLicence drivingLicence, TypeOfCar type)}.
	 * 
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testIssueCarIllegalArgumentException() {
		CarRentalCompany carRentalCompany = new CarRentalCompany();
		carRentalCompany.initializeCarFleet();
		Calendar date = new GregorianCalendar(1993, 9, 20);
		DrivingLicence dl1 = DrivingLicence.getInstance("MS-1993-31", date.getTime(), true);
		carRentalCompany.issueCar(null, dl1, TypeOfCar.LARGE_CAR);
	}

	/**
	 * Test method for terminateRental
	 * {@link uk.ac.ncl.tongzhou.advancedjava.model.CarRentalCompany#terminateRental(Person person)}.
	 * 
	 */
	@Test
	public void testTerminateRental() {
		CarRentalCompany carRentalCompany = new CarRentalCompany();
		carRentalCompany.initializeCarFleet();

		Calendar date25Year = new GregorianCalendar(1993, 9, 20);
		Calendar date5Year = new GregorianCalendar(2013, 9, 20);
		Person person25 = new Person("Tong", "Zhou2", date25Year.getTime());
		DrivingLicence licence5year = DrivingLicence.getInstance(person25, date5Year.getTime(), true);

		carRentalCompany.issueCar(person25, licence5year, TypeOfCar.LARGE_CAR);
		Car rentedCar = carRentalCompany.getCar(person25);
		rentedCar.drive(200);
		rentedCar.addFuelToTank(2);
		assertEquals(13, carRentalCompany.terminateRental(person25));
	}

	/**
	 * Test method for terminateRental illegal argument
	 * {@link uk.ac.ncl.tongzhou.advancedjava.model.CarRentalCompany#terminateRental(Person person)}.
	 * 
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testTerminateRentalIllegalArgument() {
		CarRentalCompany carRentalCompany = new CarRentalCompany();
		carRentalCompany.initializeCarFleet();

		Calendar date25Year = new GregorianCalendar(1993, 9, 20);
		Calendar date5Year = new GregorianCalendar(2013, 9, 20);
		Person person25 = new Person("Tong", "Zhou2", date25Year.getTime());
		DrivingLicence licence5year = DrivingLicence.getInstance(person25, date5Year.getTime(), true);

		carRentalCompany.issueCar(person25, licence5year, TypeOfCar.LARGE_CAR);
		Car rentedCar = carRentalCompany.getCar(person25);
		rentedCar.drive(200);
		rentedCar.addFuelToTank(2);
		carRentalCompany.terminateRental(null);
	}

}

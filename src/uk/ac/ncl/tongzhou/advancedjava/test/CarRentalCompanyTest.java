
/**
 *  
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
 * TestCarRentalCompany
 * 
 */
public class CarRentalCompanyTest {

	/**
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

		Calendar date19Year = Calendar.getInstance();
		date19Year.add(Calendar.YEAR, -19);
		Calendar date21Year = Calendar.getInstance();
		date21Year.add(Calendar.YEAR, -21);

		// Calendar test = Calendar.getInstance();
		// test.set(Calendar.DATE, 1);
		// System.out.println(test.getTime());
		// test.add(Calendar.DATE, -2);
		// System.out.println(test.getTime());
		
		// Calendar.add() works perfectly with any parameter, even current date is 1,
		// you can still execute .add(Calendar.DATE, -1).

		Calendar date0Year = Calendar.getInstance();
		date0Year.add(Calendar.DATE, -1);
		Calendar date2Year = Calendar.getInstance();
		date2Year.add(Calendar.YEAR, -2);

		Person person19 = new Person("Tong", "Zhou1", date19Year.getTime());
		Person person21 = new Person("Tong", "Zhou2", date21Year.getTime());

		DrivingLicence licence2year = DrivingLicence.getInstance(person21, date2Year.getTime(), true);
		DrivingLicence licence0year = DrivingLicence.getInstance(person21, date0Year.getTime(), true);

		// To rent a small car, they must be at least 20 years old and must have held
		// their licence for at least 1 year
		assertFalse(carRentalCompany.issueCar(person19, licence0year, TypeOfCar.SMALL_CAR));
		assertFalse(carRentalCompany.issueCar(person19, licence2year, TypeOfCar.SMALL_CAR));
		assertFalse(carRentalCompany.issueCar(person21, licence0year, TypeOfCar.SMALL_CAR));
		assertTrue(carRentalCompany.issueCar(person21, licence2year, TypeOfCar.SMALL_CAR));
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

		Calendar date24Year = Calendar.getInstance();
		date24Year.add(Calendar.YEAR, -24);
		Calendar date26Year = Calendar.getInstance();
		date26Year.add(Calendar.YEAR, -26);
		Calendar date4Year = Calendar.getInstance();
		date4Year.add(Calendar.YEAR, -4);
		Calendar date6Year = Calendar.getInstance();
		date6Year.add(Calendar.YEAR, -6);

		Person person24 = new Person("Tong", "Zhou1", date24Year.getTime());
		Person person26 = new Person("Tong", "Zhou2", date26Year.getTime());

		DrivingLicence licence6year = DrivingLicence.getInstance(person26, date6Year.getTime(), true);
		DrivingLicence licence4year = DrivingLicence.getInstance(person26, date4Year.getTime(), true);

		// To rent a large car, they must be at least 25 years old and must have held
		// their licence for at least 5 years
		assertFalse(carRentalCompany.issueCar(person24, licence4year, TypeOfCar.LARGE_CAR));
		assertFalse(carRentalCompany.issueCar(person24, licence6year, TypeOfCar.LARGE_CAR));
		assertFalse(carRentalCompany.issueCar(person26, licence4year, TypeOfCar.LARGE_CAR));
		assertTrue(carRentalCompany.issueCar(person26, licence6year, TypeOfCar.LARGE_CAR));
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

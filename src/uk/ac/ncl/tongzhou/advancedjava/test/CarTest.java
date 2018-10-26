
/**
 * 
 * @author: Tong Zhou b8027512@ncl.ac.uk
 * @created: 02:19 23-10-2018
 */
package uk.ac.ncl.tongzhou.advancedjava.test;

import static org.junit.Assert.assertEquals;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.Before;
import org.junit.Test;

import uk.ac.ncl.tongzhou.advancedjava.model.CarRegistrationNumber;
import uk.ac.ncl.tongzhou.advancedjava.model.Person;
import uk.ac.ncl.tongzhou.advancedjava.model.TypeOfCar;
import uk.ac.ncl.tongzhou.advancedjava.model.car.Car;
import uk.ac.ncl.tongzhou.advancedjava.model.car.CarFactory;

/**
 *  CarTest
 * 
 * 
 */
public class CarTest {

	/**
	 * 
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * Test method for getInstance
	 * {@link uk.ac.ncl.tongzhou.advancedjava.model.car.CarFactory#getInstance(TypeOfCar typeOfCar, CarRegistrationNumber carRegistrationNumber)}.
	 * 
	 * @throws IllegalArgumentException
	 * @throws IllegalStateException
	 */
	@Test
	public void testGetInstance() throws IllegalArgumentException, IllegalStateException {
		CarRegistrationNumber crnL1 = CarRegistrationNumber.getInstance("LG01 DNS");
		CarRegistrationNumber crnS1 = CarRegistrationNumber.getInstance("SM01 DNS");
		Car largeCar1 = CarFactory.getInstance(TypeOfCar.LARGE_CAR, crnL1);
		Car smallCar1 = CarFactory.getInstance(TypeOfCar.SMALL_CAR, crnS1);
		assertEquals("LargeCar(LG01 DNS) [Fuel=60/60, renter=null]", largeCar1.toString());
		assertEquals("SmallCar(SM01 DNS) [Fuel=49/49, renter=null]", smallCar1.toString());
	}

	/**
	 * Test method for getInstance DuplicateCrn Error
	 * {@link uk.ac.ncl.tongzhou.advancedjava.model.car.CarFactory#getInstance(TypeOfCar typeOfCar, CarRegistrationNumber carRegistrationNumber)}.
	 * 
	 * @throws IllegalArgumentException
	 * @throws IllegalStateException
	 */
	@Test(expected = IllegalStateException.class)
	public void testGetInstanceDuplicateCrn() throws IllegalArgumentException, IllegalStateException {
		CarRegistrationNumber crnL1 = CarRegistrationNumber.getInstance("LG02 DNS");
		Car largeCar1 = CarFactory.getInstance(TypeOfCar.LARGE_CAR, crnL1);
		Car largeCar2 = CarFactory.getInstance(TypeOfCar.LARGE_CAR, crnL1);
	}

	/**
	 * Test method for getInstance NullArgument Error
	 * {@link uk.ac.ncl.tongzhou.advancedjava.model.car.CarFactory#getInstance(TypeOfCar typeOfCar, CarRegistrationNumber carRegistrationNumber)}.
	 * 
	 * @throws IllegalArgumentException
	 * @throws IllegalStateException
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testGetInstanceNullArgument() throws IllegalArgumentException, IllegalStateException {
		Car largeCar1 = CarFactory.getInstance(TypeOfCar.LARGE_CAR, null);
	}

	/*
	 * Note: Following test methods are for the required function of Cars in course
	 * work.
	 */

	/**
	 * Test method for getCarRegistrationNumber
	 * {@link uk.ac.ncl.tongzhou.advancedjava.model.car.CarFactory#getCarRegistrationNumber()}.
	 */
	@Test
	public void testGetCarRegistrationNumber() {
		CarRegistrationNumber crnL = CarRegistrationNumber.getInstance("LG03 DNS");
		Car largeCar = CarFactory.getInstance(TypeOfCar.LARGE_CAR, crnL);
		assertEquals(crnL, largeCar.getCarRegistrationNumber());
	}

	/**
	 * Test method for getFuelTankCapacity
	 * {@link uk.ac.ncl.tongzhou.advancedjava.model.car.CarFactory#getFuelTankCapacity()}.
	 */
	@Test
	public void testGetFuelTankCapacity() {
		CarRegistrationNumber crnL = CarRegistrationNumber.getInstance("LG04 DNS");
		CarRegistrationNumber crnS = CarRegistrationNumber.getInstance("SM04 DNS");
		Car largeCar = CarFactory.getInstance(TypeOfCar.LARGE_CAR, crnL);
		Car smallCar = CarFactory.getInstance(TypeOfCar.SMALL_CAR, crnS);
		assertEquals(60, largeCar.getFuelTankCapacity());
		assertEquals(49, smallCar.getFuelTankCapacity());
	}

	/**
	 * Test method for getCurrentFuelAmount
	 * {@link uk.ac.ncl.tongzhou.advancedjava.model.car.CarFactory#getCurrentFuelAmount()}.
	 */
	@Test
	public void testGetCurrentFuelAmount() {
		CarRegistrationNumber crnL = CarRegistrationNumber.getInstance("LG05 DNS");
		CarRegistrationNumber crnS = CarRegistrationNumber.getInstance("SM05 DNS");
		Car largeCar = CarFactory.getInstance(TypeOfCar.LARGE_CAR, crnL);
		Car smallCar = CarFactory.getInstance(TypeOfCar.SMALL_CAR, crnS);
		assertEquals(60, largeCar.getCurrentFuelAmount());
		assertEquals(49, smallCar.getCurrentFuelAmount());
	}

	/**
	 * Test method for isFullFuelInTank
	 * {@link uk.ac.ncl.tongzhou.advancedjava.model.car.CarFactory#isFullFuelInTank()}.
	 */
	@Test
	public void testIsFullFuelInTank() {
		CarRegistrationNumber crnL = CarRegistrationNumber.getInstance("LG06 DNS");
		CarRegistrationNumber crnS = CarRegistrationNumber.getInstance("SM06 DNS");
		Car largeCar = CarFactory.getInstance(TypeOfCar.LARGE_CAR, crnL);
		Car smallCar = CarFactory.getInstance(TypeOfCar.SMALL_CAR, crnS);
		Calendar date = new GregorianCalendar(1993, 9, 20);
		Person person = new Person("Tong", "Zhou", date.getTime());
		smallCar.setRenter(person);
		smallCar.drive(1);
		assertEquals(true, largeCar.isFullFuelInTank());
		assertEquals(false, smallCar.isFullFuelInTank());
	}

	/**
	 * Test method for addFuelToTank
	 * {@link uk.ac.ncl.tongzhou.advancedjava.model.car.CarFactory#addFuelToTank(int fuelToAdd)}.
	 */
	@Test
	public void testAddFuelToTank() {
		CarRegistrationNumber crnS = CarRegistrationNumber.getInstance("SM07 DNS");
		Car smallCar = CarFactory.getInstance(TypeOfCar.SMALL_CAR, crnS);
		Calendar date = new GregorianCalendar(1993, 9, 20);
		Person person = new Person("Tong", "Zhou", date.getTime());
		smallCar.setRenter(person);

		// drove 200Km, which consume 10L fuel, the tank will have 39L rest
		smallCar.drive(200);

		// add 4L fuel to tank
		assertEquals(4, smallCar.addFuelToTank(4));
		assertEquals(43, smallCar.getCurrentFuelAmount());

		// add 10L more fuel to tank, only 6L succeed to add.
		assertEquals(6, smallCar.addFuelToTank(10));
		assertEquals(49, smallCar.getCurrentFuelAmount());
	}

	/**
	 * Test method for addFuelToTank
	 * {@link uk.ac.ncl.tongzhou.advancedjava.model.car.CarFactory#addFuelToTank(int fuelToAdd)}.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testAddFuelToTankIllegalArgument() {
		CarRegistrationNumber crnS = CarRegistrationNumber.getInstance("SM08 DNS");
		Car smallCar = CarFactory.getInstance(TypeOfCar.SMALL_CAR, crnS);
		Calendar date = new GregorianCalendar(1993, 9, 20);
		Person person = new Person("Tong", "Zhou", date.getTime());
		smallCar.setRenter(person);

		// drove 200Km, which consume 10L fuel, the tank will have 39L rest
		smallCar.drive(200);

		smallCar.addFuelToTank(-1);
	}

	/**
	 * Test method for drive
	 * {@link uk.ac.ncl.tongzhou.advancedjava.model.car.CarFactory#drive(int distanceInKms)}.
	 */
	@Test
	public void testDrive() {
		CarRegistrationNumber crnS = CarRegistrationNumber.getInstance("SM09 DNS");
		CarRegistrationNumber crnL = CarRegistrationNumber.getInstance("LG09 DNS");
		Car smallCar = CarFactory.getInstance(TypeOfCar.SMALL_CAR, crnS);
		Car largeCar = CarFactory.getInstance(TypeOfCar.LARGE_CAR, crnL);
		Calendar date = new GregorianCalendar(1993, 9, 20);
		Person person = new Person("Tong", "Zhou", date.getTime());
		smallCar.setRenter(person);
		largeCar.setRenter(person);
		// drove 100Km for small car, which consume 5L fuel
		assertEquals(5, smallCar.drive(100));
		assertEquals(44, smallCar.getCurrentFuelAmount());
		// drove 11Km for large car, which consume 2L fuel
		assertEquals(2, largeCar.drive(11));
		assertEquals(58, largeCar.getCurrentFuelAmount());
		// drove 65Km for large car, which consume 6L fuel
		assertEquals(6, largeCar.drive(65));
		assertEquals(52, largeCar.getCurrentFuelAmount());
	}

	/**
	 * Test method for drive Illegal Argument
	 * {@link uk.ac.ncl.tongzhou.advancedjava.model.car.CarFactory#drive(int fuelToAdd)}.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testDriveIllegalArgument() throws Exception {
		CarRegistrationNumber crnS = CarRegistrationNumber.getInstance("SM10 DNS");
		Car smallCar = CarFactory.getInstance(TypeOfCar.SMALL_CAR, crnS);
		Calendar date = new GregorianCalendar(1993, 9, 20);
		Person person = new Person("Tong", "Zhou", date.getTime());
		smallCar.setRenter(person);

		smallCar.drive(-1);
	}

	/**
	 * Test method for drive Not Rented
	 * {@link uk.ac.ncl.tongzhou.advancedjava.model.car.CarFactory#drive(int fuelToAdd)}.
	 */
	@Test(expected = IllegalStateException.class)
	public void testDriveIllegalNotRented() {
		CarRegistrationNumber crnS = CarRegistrationNumber.getInstance("SM11 DNS");
		Car smallCar = CarFactory.getInstance(TypeOfCar.SMALL_CAR, crnS);

		smallCar.drive(10);
	}

	/**
	 * Test method for drive when negative amounts of fuel, boundary test
	 * {@link uk.ac.ncl.tongzhou.advancedjava.model.car.CarFactory#drive(int fuelToAdd)}.
	 */
	@Test(expected = IllegalStateException.class)
	public void testDriveNegativeFuel() {
		CarRegistrationNumber crnS = CarRegistrationNumber.getInstance("SM11 DNS");
		Car smallCar = CarFactory.getInstance(TypeOfCar.SMALL_CAR, crnS);
		Calendar date = new GregorianCalendar(1993, 9, 20);
		Person person = new Person("Tong", "Zhou", date.getTime());
		smallCar.setRenter(person);

		assertEquals(50000000, smallCar.drive(999999999));
		assertEquals(-49999951, smallCar.getCurrentFuelAmount());
		smallCar.drive(10);
	}

}

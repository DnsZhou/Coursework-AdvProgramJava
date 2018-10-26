
/**
 * 
 * @author: Tong Zhou b8027512@ncl.ac.uk
 * @created: 00:01 24-10-2018
 */
package uk.ac.ncl.tongzhou.advancedjava.model.car;

import java.util.HashMap;
import java.util.Map;

import uk.ac.ncl.tongzhou.advancedjava.model.CarRegistrationNumber;
import uk.ac.ncl.tongzhou.advancedjava.model.Person;
import uk.ac.ncl.tongzhou.advancedjava.model.TypeOfCar;

/**
 * CarFactory
 * 
 * 
 */
public abstract class CarFactory implements Car {
	private CarRegistrationNumber crn;
	protected int currentFuelAmount;
	protected int fuelTankCapacity;
	private Person renter;
	private static final Map<CarRegistrationNumber, Car> allCars = new HashMap<>();

	/**
	 * get one car instance according to the type of car and carRegistrationNumber.
	 * 
	 * @param typeOfCar
	 * @param carRegistrationNumber
	 * @return new instance of Car
	 */
	public static Car getInstance(TypeOfCar typeOfCar, CarRegistrationNumber carRegistrationNumber) {
		if (carRegistrationNumber == null)
			throw new IllegalArgumentException("carRegistrationNumber should not be null.");
		// enforce single instance per CarRegistrationNumber
		Car car = allCars.get(carRegistrationNumber);
		if (car != null)
			throw new IllegalStateException(
					"Duplicate car registration number in system : Car-" + carRegistrationNumber.toString());

		if (typeOfCar == TypeOfCar.LARGE_CAR)
			car = new LargeCar(carRegistrationNumber);
		else if (typeOfCar == TypeOfCar.SMALL_CAR)
			car = new SmallCar(carRegistrationNumber);
		else
			throw new IllegalArgumentException("Invalid car type: " + typeOfCar);

		allCars.put(carRegistrationNumber, car);

		return car;

	}

	/**
	 * 
	 * @param crn
	 */
	CarFactory(CarRegistrationNumber crn) {
		this.crn = crn;
	}

	/**
	 * 
	 * @return CarRegistrationNumber
	 * @see uk.ac.ncl.tongzhou.advancedjava.model.car.Car#getCarRegistrationNumber()
	 */
	@Override
	public CarRegistrationNumber getCarRegistrationNumber() {
		return crn;
	}

	/**
	 * 
	 * @return FuelTankCapacity
	 * @see uk.ac.ncl.tongzhou.advancedjava.model.car.Car#getFuelTankCapacity()
	 */
	@Override
	public int getFuelTankCapacity() {
		return fuelTankCapacity;
	}

	/**
	 * 
	 * @return CurrentFuelAmount
	 * @see uk.ac.ncl.tongzhou.advancedjava.model.car.Car#getCurrentFuelAmount()
	 */
	@Override
	public int getCurrentFuelAmount() {
		return currentFuelAmount;
	}

	/**
	 * 
	 * @return isFullFuelInTank
	 * @see uk.ac.ncl.tongzhou.advancedjava.model.car.Car#isFullFuelInTank()
	 */
	@Override
	public boolean isFullFuelInTank() {
		return fuelTankCapacity == currentFuelAmount;
	}

	/**
	 * 
	 * @param fuelToAdd
	 * @return actual added fuel
	 * @see uk.ac.ncl.tongzhou.advancedjava.model.car.Car#addFuelToTank(int)
	 */
	@Override
	public int addFuelToTank(int fuelToAdd) {
		if (fuelToAdd < 0)
			throw new IllegalArgumentException("fuelToAdd should not be a negative number.");
		int fuelNeeded = fuelTankCapacity - currentFuelAmount;
		if (fuelToAdd > fuelNeeded) {
			currentFuelAmount = fuelTankCapacity;
			return fuelNeeded;
		} else {
			currentFuelAmount += fuelToAdd;
			return fuelToAdd;
		}
	}

	/**
	 * Return the renter of this car. If the car is not rented, return null.
	 *
	 * @return renter
	 */
	public Person getRenter() {
		return renter;
	}

	/**
	 * Set the value of renter
	 *
	 * @param renter:
	 *            renter to be set.
	 */
	public void setRenter(Person renter) {
		this.renter = renter;
	}

	/**
	 * 
	 * @return String
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "(" + crn + ") [Fuel=" + currentFuelAmount + "/" + fuelTankCapacity + ", renter=" + renter + "]";
	}
}

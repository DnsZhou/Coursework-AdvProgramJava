
/**
 * 
 * @author: Tong Zhou b8027512@ncl.ac.uk
 * @created: 01:24 23-10-2018
 */
package uk.ac.ncl.tongzhou.advancedjava.model.car;

import uk.ac.ncl.tongzhou.advancedjava.model.CarRegistrationNumber;
import uk.ac.ncl.tongzhou.advancedjava.model.Person;

/**
 * Car
 * 
 * 
 */
public interface Car {

	/**
	 * a method to get the car's registration number
	 * 
	 * @return CarRegistrationNumber
	 */
	CarRegistrationNumber getCarRegistrationNumber();

	/**
	 * a method to get the capacity in whole Litres of the car's fuel tank
	 * 
	 * @return FuelTankCapacity
	 */
	int getFuelTankCapacity();

	/**
	 * a method to get the amount of fuel in whole Litres currently in the fuel tank
	 * 
	 * @return CurrentFuelAmount
	 */
	int getCurrentFuelAmount();

	/**
	 * a method that indicates whether the car's fuel tank is full or not
	 * 
	 * @return isFullFuelInTank
	 */
	boolean isFullFuelInTank();

	/**
	 * a method to add a given number of whole Litres to the fuel tank (up to the
	 * tank's capacity) and which, after execution, indicates how much fuel was
	 * added to the tank
	 * 
	 * @param fuelToAdd
	 * @return actual added fuel
	 */
	int addFuelToTank(int fuelToAdd);

	/**
	 * a method to "drive" the car for a given number of whole Kilometres that
	 * returns the number of whole Litres of fuel consumed during the journey
	 * 
	 * @param distanceInKms
	 * @return the number of whole Litres of fuel consumed during the journey
	 */
	int drive(int distanceInKms);

	/**
	 * additional method in order to get the renter of the car.
	 * 
	 * @return the renter of the car
	 */
	public Person getRenter();

	/**
	 * additional method in order to bind a renter to the car.
	 * 
	 * @param renter
	 */
	public void setRenter(Person renter);
}

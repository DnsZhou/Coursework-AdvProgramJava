
/**
 * 
 * @author: Tong Zhou b8027512@ncl.ac.uk
 * @created: 01:25 23-10-2018
 */
package uk.ac.ncl.tongzhou.advancedjava.model.car;

import uk.ac.ncl.tongzhou.advancedjava.model.CarRegistrationNumber;

/**
 * SmallCar
 * 
 * 
 */
public class SmallCar extends CarFactory {
	/**
	 * @Fields LARGE_CAR_FUEL_RATE : The fuel rate of driving (Kilometres/Litre)
	 */
	private final int LARGE_CAR_FUEL_RATE = 20;

	/**
	 * initial a new car with full tank of fuel
	 * 
	 * @param carRegistrationNumber
	 */
	SmallCar(CarRegistrationNumber carRegistrationNumber) {
		super(carRegistrationNumber);
		this.fuelTankCapacity = 49;
		this.currentFuelAmount = 49;
	}

	/**
	 * A small car consumes fuel at the rate of 20 Kilometres/Litre.
	 * 
	 * @param distanceInKms
	 * @return the number of whole Litres of fuel consumed during the journey
	 * @throws IllegalStateException
	 *             if Car not rented.
	 * @throws IllegalArgumentException
	 *             if <code>distanceInKms</code> is empty
	 * @see uk.ac.ncl.tongzhou.advancedjava.model.car.Car#drive(int)
	 */
	@Override
	public int drive(int distanceInKms) throws IllegalStateException, IllegalArgumentException {
		// A car cannot be driven if it is not currently rented.
		if (this.getRenter() == null) {
			throw new java.lang.IllegalStateException("No journey has been undertaken: Car not rented");
		}

		if (distanceInKms <= 0)
			throw new java.lang.IllegalArgumentException("No journey has been undertaken: Illegal argument detected");
		// A car cannot be driven if it has 0 or less Litres of fuel in its tank.
		if (this.currentFuelAmount <= 0)
			throw new java.lang.IllegalStateException("No journey has been undertaken: Out of Fuel.");

		// Calculate the consumed fuel in whole Litres
		int consumedFuel = (int) Math.ceil((double) distanceInKms / (double) LARGE_CAR_FUEL_RATE);
		// if (this.currentFuelAmount < consumedFuel) {
		// throw new java.lang.IllegalStateException(
		// "No journey has been undertaken: Fuel insufficient for this journey:
		// Required:" + consumedFuel
		// + " Remaining:" + currentFuelAmount);
		// } else {
		this.currentFuelAmount -= consumedFuel;
		return consumedFuel;
		// }
	}

	/**
	 * @return String
	 * @see uk.ac.ncl.tongzhou.advancedjava.model.car.CarFactory#toString()
	 */
	@Override
	public String toString() {
		return "SmallCar" + super.toString();
	}

}

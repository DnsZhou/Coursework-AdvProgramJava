
/**
 * @Description: 
 * @author: Tong Zhou b8027512@ncl.ac.uk
 * @created: 01:25 23-10-2018
 */
package uk.ac.ncl.tongzhou.advancedjava.model.car;

import uk.ac.ncl.tongzhou.advancedjava.model.CarRegistrationNumber;

/**
 * @ClassName: LargeCar
 * @Description:
 * 
 */
public class LargeCar extends CarFactory {

	/**
	 * @Fields LARGE_CAR_FUEL_RATE1 : The fuel rate for first stage of driving
	 *         (Kilometres/Litre)
	 */
	private final int LARGE_CAR_FUEL_RATE1 = 10;

	/**
	 * @Fields LARGE_CAR_FUEL_RATE2 : The fuel rate for second stage of driving
	 *         (Kilometres/Litre)
	 */
	private final int LARGE_CAR_FUEL_RATE2 = 15;

	/**
	 * @Fields LARGE_CAR_FUEL_RATE1_DISTANCE : The threshold between fuel rate 1 and
	 *         fuel rate 2 (Kilometres)
	 */
	private final int LARGE_CAR_FUEL_RATE1_THRESHOLD = 50;

	/**
	 * @Title Constructor for LargeCar
	 * @Description initial a new car with full tank of fuel.
	 * @param carRegistrationNumber
	 */
	LargeCar(CarRegistrationNumber carRegistrationNumber) {
		super(carRegistrationNumber);
		this.fuelTankCapacity = 60;
		this.currentFuelAmount = 60;
	}

	/**
	 * @Title: driveCar
	 * @Description: A large car consumes fuel at the rate of 10 Kilometres/Litre
	 *               for the first 50 Kilometres of a journey and then at the rate
	 *               of 15 Kilometres/Litre for the remainder of the journey.
	 * @param distanceInKms
	 * @return
	 * @throws IllegalStateException
	 * @throws IllegalArgumentException
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
		int consumedFuel;
		if (distanceInKms <= LARGE_CAR_FUEL_RATE1_THRESHOLD)
			consumedFuel = (int) Math.ceil((double) distanceInKms / (double) LARGE_CAR_FUEL_RATE1);
		else

			consumedFuel = (int) Math.ceil((double) LARGE_CAR_FUEL_RATE1_THRESHOLD / (double) LARGE_CAR_FUEL_RATE1
					+ (double) (distanceInKms - LARGE_CAR_FUEL_RATE1_THRESHOLD) / (double) LARGE_CAR_FUEL_RATE2);

//		if (this.currentFuelAmount < consumedFuel) {
//			throw new java.lang.IllegalStateException(
//					"No journey has been undertaken: Fuel insufficient for this journey: Required:" + consumedFuel
//							+ " Remaining:" + currentFuelAmount);
//		} else {
		this.currentFuelAmount -= consumedFuel;
		return consumedFuel;
//		}
	}

	/**
	 * @Title toString
	 * @Description
	 * @return
	 * @see uk.ac.ncl.tongzhou.advancedjava.model.car.CarFactory#toString()
	 */
	@Override
	public String toString() {
		return "LargeCar" + super.toString();
	}

}

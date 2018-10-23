
/**
 * @Description: 
 * @author: Tong Zhou b8027512@ncl.ac.uk
 * @created: 01:25 23-10-2018
 */
package uk.ac.ncl.tongzhou.advancedjava.model.car;

/**
 * @ClassName: SmallCar
 * @Description:
 * 
 */
public class SmallCar extends AbstractCar {
	/**
	 * @Fields LARGE_CAR_FUEL_RATE : The fuel rate of driving (Kilometres/Litre)
	 */
	private final int LARGE_CAR_FUEL_RATE = 20;

	/**
	 * @Title: driveCar
	 * @Description: A small car consumes fuel at the rate of 20 Kilometres/Litre.
	 * @param distanceInKms
	 * @return
	 * @throws IllegalStateException
	 * @throws IllegalArgumentException
	 * @see uk.ac.ncl.tongzhou.advancedjava.model.car.Car#driveCar(int)
	 */
	@Override
	public int driveCar(int distanceInKms) throws IllegalStateException, IllegalArgumentException {
		// A car cannot be driven if it is not currently rented.
		if (this.getRenter() == null) {
			throw new java.lang.IllegalStateException("No journey has been undertaken: Car not rented");
		}
		
		if (distanceInKms <= 0)
			throw new java.lang.IllegalArgumentException("No journey has been undertaken: Illegal argument detected");
		// A car cannot be driven if it has 0 or less Litres of fuel in its tank.
		if (this.currentFuelAmount == 0)
			throw new java.lang.IllegalStateException("No journey has been undertaken: Out of Fuel.");

		// Calculate the consumed fuel in whole Litres
		int consumedFuel = (int) Math.ceil((double) distanceInKms / (double) LARGE_CAR_FUEL_RATE);
		if (this.currentFuelAmount < consumedFuel) {
			throw new java.lang.IllegalStateException(
					"No journey has been undertaken: Fuel insufficient for this journey: Required:" + consumedFuel
							+ " Remaining:" + currentFuelAmount);
		} else {
			this.currentFuelAmount -= consumedFuel;
			return consumedFuel;
		}
	}

}

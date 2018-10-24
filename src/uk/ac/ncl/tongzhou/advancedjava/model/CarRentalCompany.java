
/**
 * @Description: 
 * @author: Tong Zhou b8027512@ncl.ac.uk
 * @created: 01:28 23-10-2018
 */
package uk.ac.ncl.tongzhou.advancedjava.model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import nl.flotsam.xeger.Xeger;
import uk.ac.ncl.tongzhou.advancedjava.model.car.Car;
import uk.ac.ncl.tongzhou.advancedjava.model.car.CarFactory;
import uk.ac.ncl.tongzhou.advancedjava.model.car.LargeCar;
import uk.ac.ncl.tongzhou.advancedjava.model.car.SmallCar;

/**
 * @ClassName: CarRentalCompany
 * @Description: The main class for a Car Rental Company
 * 
 */
public class CarRentalCompany {
	private List<Car> allCars;

	/**
	 * @Title Constructor for CarRentalCompany
	 * @Description
	 * @param allCars
	 */
	public CarRentalCompany() {
		super();
		this.allCars = new ArrayList<>();
	}

	/**
	 * @Title availableCars
	 * @Description This method returns the number of cars of the specified type
	 *              that are available to rent.
	 * @param type
	 * @return
	 */
	public long availableCars(TypeOfCar type) {
		switch (type) {
		case LARGE_CAR:
			return allCars.stream().filter(car -> car instanceof LargeCar && car.getRenter() == null).count();
		case SMALL_CAR:
			return allCars.stream().filter(car -> car instanceof SmallCar && car.getRenter() == null).count();
		}
		return 0;
	}

	/**
	 * @Title getRentedCars
	 * @Description This method returns a collection of all the cars currently
	 *              rented out (if any).
	 * @return
	 */
	public List<Car> getRentedCars() {
		return allCars.stream().filter(car -> car.getRenter() != null).collect(Collectors.toList());
	}

	/**
	 * @Title getCar
	 * @Description Given a person, this method returns the car they are currently
	 *              renting (if any).
	 * @param person
	 * @return
	 */
	public Car getCar(Person person) {
		return allCars.stream().filter(car -> car.getRenter().equals(person)).findFirst().get();
	}

	/**
	 * @Title issueCar
	 * @DescriptionThis Given a Person (the renter), the person's DrivingLicence and
	 *                  a specification of the type of car required (small or
	 *                  large), this method determines whether the person is
	 *                  eligible to rent a car of the specified type and, if there
	 *                  is a car available, issues a car of the specified type. The
	 *                  car has a full tank of petrol at the start of the rental.
	 *                  The method associates the car with the person renting it (so
	 *                  that the company has a record of cars out for rent and the
	 *                  people renting them). If a car cannot be issued, the method
	 *                  returns an appropriate indication of the failure to issue a
	 *                  car. Note, this does not have to indicate why a car cannot
	 *                  be issued; it simply indicates that a car cannot be issued.
	 * @param person
	 * @param drivingLicence
	 * @param type
	 * @return
	 */
	public boolean issueCar(Person person, DrivingLicence drivingLicence, TypeOfCar type) {
		if (person == null || drivingLicence == null || type == null)
			throw new IllegalArgumentException("person, drivingLicence and type can not be null");

		if (this.availableCars(type) == 0)
			return false;
		// the person renting the car must have a full driving licence
		if (!drivingLicence.isFullLicence())
			return false;

		// they cannot rent more than one car at a time
		if (this.getCar(person) != null)
			return false;

		SimpleDateFormat df = new SimpleDateFormat("yyyy");
		int yearofBirth = Integer.parseInt(df.format(person.getDateOfBirth()));
		int yearOfIssue = Integer.parseInt(df.format(drivingLicence.getIssueDate()));
		int currentYear = Integer.parseInt(df.format(new Date()));
		/*
		 * to rent a small car, they must be at least 20 years old and must have held
		 * their licence for at least 1 year.to rent a large car, they must be at least
		 * 25 years old and must have held their licence for at least 5 years
		 */
		if ((type.equals(TypeOfCar.SMALL_CAR) && currentYear - yearofBirth >= 20 && currentYear - yearOfIssue >= 1)
				|| (type.equals(TypeOfCar.LARGE_CAR) && currentYear - yearofBirth >= 25
						&& currentYear - yearOfIssue >= 5)) {
			Car oneCar = getOneCar(type);
			oneCar.setRenter(person);
			return true;
		} else
			return false;

	}

	/**
	 * @Title terminateRental
	 * @Description This method terminates the given person's rental contract. In
	 *              effect, the person is returning the car. The car is then
	 *              available for rent by someone else. The method removes the
	 *              record of the rental from the company's records (disassociating
	 *              the car from the person) and returns the amount of fuel in
	 *              Litres required to fill the car's tank. The person returning the
	 *              car must either have returned the car with a full tank or will
	 *              be liable for the number of Litres required to fill the tank.
	 *              This terminateRental method is not responsible for managing
	 *              charges for the required fuel. It just reports the amount of
	 *              fuel required to fill the tank. If a person attempts to
	 *              terminate a non-existent contract, this method does nothing.
	 * @param person
	 * @return
	 */
	public int terminateRental(Person person) {
		Car currentCar = this.getCar(person);
		if (currentCar == null)
			return 0;
		currentCar.setRenter(null);
		return currentCar.getFuelTankCapacity() - currentCar.getCurrentFuelAmount();
	}

	/**
	 * @Title getOneCar
	 * @Description additional method for getting one available car from fleet.
	 * @param type
	 * @return
	 */
	private Car getOneCar(TypeOfCar type) {
		switch (type) {
		case LARGE_CAR:
			return allCars.stream().filter(car -> car instanceof LargeCar && car.getRenter() == null).findFirst().get();
		case SMALL_CAR:
			return allCars.stream().filter(car -> car instanceof SmallCar && car.getRenter() == null).findFirst().get();
		}
		return null;
	}

	public void initializeCarFleet() {
		addCars(TypeOfCar.SMALL_CAR, 20);
		addCars(TypeOfCar.LARGE_CAR, 10);
	}

	private void addCars(TypeOfCar type, int amount) {
		String regex = "[A-Z]{2}[0-9]{2} [A-Z]{3}";
		Xeger generator = new Xeger(regex);
		String crn;
		for (int i = 0; i < amount; i++) {
			try {
				crn = generator.generate();
				this.allCars.add(CarFactory.getInstance(type, CarRegistrationNumber.getInstance(crn)));
			} catch (IllegalStateException e) {
				addCars(type, 1);
			}

		}

	}
}


/**
 * 
 * @author: Tong Zhou b8027512@ncl.ac.uk
 * @created: 01:28 23-10-2018
 */
package uk.ac.ncl.tongzhou.advancedjava.model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import nl.flotsam.xeger.Xeger;
import uk.ac.ncl.tongzhou.advancedjava.model.car.Car;
import uk.ac.ncl.tongzhou.advancedjava.model.car.CarFactory;
import uk.ac.ncl.tongzhou.advancedjava.model.car.LargeCar;
import uk.ac.ncl.tongzhou.advancedjava.model.car.SmallCar;

/**
 * CarRentalCompany The main class for a Car Rental Company
 * 
 */
public class CarRentalCompany {
	private List<Car> allCars;

	/**
	 * Constructor for CarRentalCompany
	 * 
	 */
	public CarRentalCompany() {
		super();
		this.allCars = new ArrayList<>();
	}

	/**
	 * This method returns the number of cars of the specified type that are
	 * available to rent.
	 * 
	 * @param type
	 * @return Returns the number of cars of the specified type that are available
	 *         to rent.
	 */
	public long availableCars(TypeOfCar type) {
		if (type == null) {
			throw new IllegalArgumentException("Type of car could not be null");
		}
		switch (type) {
		case LARGE_CAR:
			return allCars.stream().filter(car -> car instanceof LargeCar && car.getRenter() == null).count();
		case SMALL_CAR:
			return allCars.stream().filter(car -> car instanceof SmallCar && car.getRenter() == null).count();
		}
		return 0;
	}

	/**
	 * This method returns a collection of all the cars currently rented out (if
	 * any).
	 * 
	 * @return returns a collection of all the cars currently rented out (if any).
	 */
	public List<Car> getRentedCars() {
		return allCars.stream().filter(car -> car.getRenter() != null).collect(Collectors.toList());
	}

	/**
	 * Given a person, this method returns the car they are currently renting (if
	 * any).
	 * 
	 * @param person
	 * @return returns the car they are currently renting (if any).
	 */
	public Car getCar(Person person) {
		if (person == null)
			throw new IllegalArgumentException("person should not be null.");
		return allCars.stream().filter(car -> car.getRenter() != null && car.getRenter().equals(person)).findFirst()
				.orElse(null);
	}

	/**
	 * This Given a Person (the renter), the person's DrivingLicence and a
	 * specification of the type of car required (small or large), this method
	 * determines whether the person is eligible to rent a car of the specified type
	 * and, if there is a car available, issues a car of the specified type. The car
	 * has a full tank of petrol at the start of the rental. The method associates
	 * the car with the person renting it (so that the company has a record of cars
	 * out for rent and the people renting them). If a car cannot be issued, the
	 * method returns an appropriate indication of the failure to issue a car. Note,
	 * this does not have to indicate why a car cannot be issued; it simply
	 * indicates that a car cannot be issued.
	 * 
	 * @param person
	 * @param drivingLicence
	 * @param type
	 * @return returns whether the car is available to rent or not.
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

		/*
		 * to rent a small car, they must be at least 20 years old and must have held
		 * their licence for at least 1 year.to rent a large car, they must be at least
		 * 25 years old and must have held their licence for at least 5 years
		 */
		if ((type.equals(TypeOfCar.SMALL_CAR) && compareDateMoreThanNYearsTillNow(person.getDateOfBirth(), 20)
				&& compareDateMoreThanNYearsTillNow(drivingLicence.getIssueDate(), 1))
				|| (type.equals(TypeOfCar.LARGE_CAR) && compareDateMoreThanNYearsTillNow(person.getDateOfBirth(), 25)
						&& compareDateMoreThanNYearsTillNow(drivingLicence.getIssueDate(), 5))) {
			Car oneCar = getOneCar(type);
			if (oneCar != null) {
				oneCar.setRenter(person);
				return true;
			} else
				return false;
		} else
			return false;

	}

	/**
	 * This method terminates the given person's rental contract. In effect, the
	 * person is returning the car. The car is then available for rent by someone
	 * else. The method removes the record of the rental from the company's records
	 * (disassociating the car from the person) and returns the amount of fuel in
	 * Litres required to fill the car's tank. The person returning the car must
	 * either have returned the car with a full tank or will be liable for the
	 * number of Litres required to fill the tank. This terminateRental method is
	 * not responsible for managing charges for the required fuel. It just reports
	 * the amount of fuel required to fill the tank. If a person attempts to
	 * terminate a non-existent contract, this method does nothing.
	 * 
	 * @param person
	 * @return returns the amount of fuel required to fill the tank.
	 */
	public int terminateRental(Person person) {
		Car currentCar = this.getCar(person);
		if (currentCar == null)
			return 0;
		currentCar.setRenter(null);
		int fuelToAdd = currentCar.getFuelTankCapacity() - currentCar.getCurrentFuelAmount();
		currentCar.addFuelToTank(fuelToAdd);
		return fuelToAdd;
	}

	private Car getOneCar(TypeOfCar type) {
		switch (type) {
		case LARGE_CAR:
			return allCars.stream().filter(car -> car instanceof LargeCar && car.getRenter() == null).findFirst()
					.orElse(null);
		case SMALL_CAR:
			return allCars.stream().filter(car -> car instanceof SmallCar && car.getRenter() == null).findFirst()
					.orElse(null);
		}
		return null;
	}

	public void initializeCarFleet() {
		addCars(TypeOfCar.SMALL_CAR, 20);
		addCars(TypeOfCar.LARGE_CAR, 10);
	}

	private void addCars(TypeOfCar type, int amount) {
		// Use third-party library XEGER to generate random String according to regular
		// expression expressions.
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

	private boolean compareDateMoreThanNYearsTillNow(final Date date, int years) {
		Calendar today = Calendar.getInstance();
		Calendar targetDate = Calendar.getInstance();
		targetDate.setTime(date);
		targetDate.add(Calendar.YEAR, years);
		if (targetDate.getTime().getTime() > today.getTime().getTime())
			return false;
		else
			return true;

	}
}

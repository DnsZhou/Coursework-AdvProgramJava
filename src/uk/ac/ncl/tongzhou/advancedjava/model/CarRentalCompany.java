
/**
 * @Description: 
 * @author: Tong Zhou b8027512@ncl.ac.uk
 * @created: 01:28 23-10-2018
 */
package uk.ac.ncl.tongzhou.advancedjava.model;

import java.util.List;

import uk.ac.ncl.tongzhou.advancedjava.model.car.Car;

/**
 * @ClassName: CarRentalCompany
 * @Description: The main class for a Car Rental Company 
 * 
 */
public class CarRentalCompany {
	private List<Car> allCars;
	
	
	
	public int availableCars(TypeOfCar type){
		return 0;
		
	}
	
	public List<Car> getRentedCars(){
		return null;
		
	}
	
	public Car getCar(Person person) {
		return null;
		
	}
	
	public boolean issueCar(Person person, DrivingLicence drivingLicence, TypeOfCar type) {
		return false;
		
	}
	
	public int terminateRental(Person person) {
		return 0;
		
	}
}

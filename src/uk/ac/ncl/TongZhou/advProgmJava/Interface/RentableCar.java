
/**
 * @Description: 
 * @author: Tong Zhou b8027512@ncl.ac.uk
 * @created: 22:59 19-10-2018
 */
package uk.ac.ncl.TongZhou.advProgmJava.Interface;

import java.util.List;

import uk.ac.ncl.TongZhou.advProgmJava.Entity.DrivingLicence;
import uk.ac.ncl.TongZhou.advProgmJava.Entity.Person;

/**
 * @ClassName: RentableCar 
 * @Description: Define the interface that define the main requirement for a rentable car.
 * 
 */
public interface RentableCar {
	int availableCars(String typeOfCar);
	List<RentableCar> getRentedCars();
	RentableCar getCar(Person person);
	String issueCar(Person person,DrivingLicence drivingLicence,String typeOfCar);
	int terminateRental(Person person);
	
	
}

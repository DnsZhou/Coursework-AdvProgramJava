package uk.ac.ncl.TongZhou.advProgmJava.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uk.ac.ncl.TongZhou.advProgmJava.Entity.Person;

/**
 * @author Tong Zhou b8027512@ncl.ac.uk
 * @created: 14 Oct 2018 17:15:56
 */
class TestPerson {

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void test() {
		Person p = new Person();
		p.setFirstName("Tong");
		p.setLastName("Zhou");
		assertTrue(p.getFirstName().concat(p.getLastName()).equals("TongZhou"));
	}

}

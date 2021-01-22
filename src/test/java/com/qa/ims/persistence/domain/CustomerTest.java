package com.qa.ims.persistence.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

public class CustomerTest {
	
	private Customer customer;
	private Customer other;
	
	@Before
	public void setUp() {
		customer = new Customer(1L, "Chris", "Perrins");
		other = new Customer(1L, "Chris", "Perrins");
	}
	
	@Test
	public void settersTest() {
		assertNotNull(customer.getId());
		assertNotNull(customer.getFirstName());
		assertNotNull(customer.getSurname());
		
		customer.setId(null);
		assertNull(customer.getId());
		customer.setFirstName(null);
		assertNull(customer.getFirstName());
		customer.setSurname(null);
		assertNull(customer.getSurname());
		
	}
	
	@Test
	public void equalsWithNull() {
		assertNotEquals(customer, null);
	}
	
	@Test
	public void equalsWithDifferentObject() {
		assertNotEquals(customer, new Object());
	}
	
	@Test
	public void createCustomerWithId() {
		assertEquals(1L, customer.getId(), 0);
		assertEquals("Chris", customer.getFirstName());
		assertEquals("Perrins", customer.getSurname());
	}
	
	@Test
	public void checkEquality() {
		assertEquals(customer, customer);
	}
	
	@Test
	public void checkEqualityBetweenDifferentObjects() {
		assertEquals(customer, other);
	}
	
	@Test
	public void customerNameNullButOtherNameNotNull() {
		customer.setFirstName(null);
		assertNotEquals(customer, other);
	}
	
	@Test
	public void customerNamesNotEqual() {
		other.setFirstName("rhys");
		assertNotEquals(customer, other);
	}
	
	@Test
	public void checkEqualityBetweenDifferentObjectsNullName() {
		customer.setFirstName(null);
		other.setFirstName(null);
		assertEquals(customer, other);
	}
	
	@Test
	public void nullId() {
		customer.setId(null);
		assertNotEquals(customer, other);
	}
	
	@Test
	public void nullIdOnBoth() {
		customer.setId(null);
		other.setId(null);
		assertEquals(customer, other);
	}
	
	@Test
	public void otherIdDifferent() {
		other.setId(2L);
		assertNotEquals(customer, other);
	}
	
	@Test
	public void nullSurname() {
		customer.setSurname(null);
		assertNotEquals(customer, other);
	}
	
	@Test
	public void nullSurnameOnBoth() {
		customer.setSurname(null);
		other.setSurname(null);
		assertEquals(customer, other);
	}
	
	@Test
	public void otherSurnameDifferent() {
		other.setSurname("thompson");
		assertNotEquals(customer, other);
	}
	
	@Test
	public void constructorWithoutId() {
		Customer customer = new Customer("Chris", "Perrins");
		assertNull(customer.getId());
		assertNotNull(customer.getFirstName());
		assertNotNull(customer.getSurname());
	}
	
	@Test
	public void hashCodeTest() {
		assertEquals(customer.hashCode(), other.hashCode());
	}
	@Test
	public void hashCodeTestWithNull() {
		Customer customer = new Customer(null, null);
		Customer other = new Customer(null, null);
		assertEquals(customer.hashCode(), other.hashCode());
	}
	
	@Test
	public void toStringTest() {
		String toString = "Customer [id=1, firstName=Chris, surname=Perrins]";
		assertEquals(toString, customer.toString());
	}
}

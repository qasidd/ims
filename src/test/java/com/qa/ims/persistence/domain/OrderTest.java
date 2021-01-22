package com.qa.ims.persistence.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class OrderTest {
	
	private Order order;
	private Order other;
	
	@Before
	public void setUp() {
		order = new Order(1L, 1L);
		other = new Order(1L, 1L);
	}
	
	@Test
	public void settersTest() {
		assertNotNull(order.getId());
		assertNotNull(order.getCustomerId());
		
		order.setId(null);
		assertNull(order.getId());
		order.setCustomerId(null);
		assertNull(order.getCustomerId());
		
	}
	
	@Test
	public void equalsWithNull() {
		assertNotEquals(order, (null));
	}
	
	@Test
	public void equalsWithDifferentObject() {
		assertNotEquals(order, (new Object()));
	}
	
	@Test
	public void createOrderWithId() {
		assertEquals(1L, order.getId(), 0);
		assertEquals(1L, order.getCustomerId(), 0);
	}
	
	@Test
	public void checkEquality() {
		assertEquals(order, (order));
	}
	
	@Test
	public void checkEqualityBetweenDifferentObjects() {
		assertEquals(order, other);
	}
	
	@Test
	public void orderCustmerIdNullButOtherNameNotNull() {
		order.setCustomerId(null);
		assertNotEquals(order, other);
	}
	
	@Test
	public void orderCustomerIdsNotEqual() {
		other.setCustomerId(3L);
		assertNotEquals(order, other);
	}
	
	@Test
	public void checkEqualityBetweenDifferentObjectsNullCustomerId() {
		order.setCustomerId(null);
		other.setCustomerId(null);
		assertEquals(order, other);
	}
	
	@Test
	public void nullId() {
		order.setId(null);
		assertNotEquals(order, other);
	}
	
	@Test
	public void nullIdOnBoth() {
		order.setId(null);
		other.setId(null);
		assertEquals(order, other);
	}
	
	@Test
	public void otherIdDifferent() {
		other.setId(2L);
		assertNotEquals(order, other);
	}
	
	@Test
	public void constructorWithoutId() {
		Order order = new Order(1L);
		assertNull(order.getId());
		assertNotNull(order.getCustomerId());
	}
	
	@Test
	public void hashCodeTest() {
		assertEquals(order.hashCode(), other.hashCode());
	}
	
	@Test
	public void hashCodeTestWithNull() {
		Order order = new Order(null, null);
		Order other = new Order(null, null);
		assertEquals(order.hashCode(), other.hashCode());
	}
	
	@Test
	public void toStringTest() {
		String toString = "Order [id=1, customerId=1, orderItemList=[]]";
		assertEquals(toString, order.toString());
	}

}

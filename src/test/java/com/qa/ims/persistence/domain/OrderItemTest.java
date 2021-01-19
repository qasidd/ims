package com.qa.ims.persistence.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class OrderItemTest {
	
	private OrderItem orderItem;
	private OrderItem other;
	
	@Before
	public void setUp() {
		orderItem = new OrderItem(1L, 1L, 2L);
		other = new OrderItem(1L, 1L, 2L);
	}
	
	@Test
	public void settersTest() {
		assertNotNull(orderItem.getId());
		assertNotNull(orderItem.getOrderId());
		assertNotNull(orderItem.getItemId());
		
		orderItem.setId(null);
		assertNull(orderItem.getId());
		orderItem.setOrderId(null);
		assertNull(orderItem.getOrderId());
		orderItem.setItemId(null);
		assertNull(orderItem.getItemId());
	}
	
	@Test
	public void equalsWithNull() {
		assertFalse(orderItem.equals(null));
	}
	
	@Test
	public void equalsWithDifferentObject() {
		assertFalse(orderItem.equals(new Object()));
	}
	
	@Test
	public void createOrderItemWithId() {
		assertEquals(1L, orderItem.getId(), 0);
		assertEquals(1L, orderItem.getOrderId(), 0);
		assertEquals(2L, orderItem.getItemId(), 0);
	}
	
	@Test
	public void checkEquality() {
		assertTrue(orderItem.equals(orderItem));
	}
	
	@Test
	public void checkEqualityBetweenDifferentObjects() {
		assertTrue(orderItem.equals(other));
	}
	
	@Test
	public void orderItemOrderIdNullButOtherOrderIdNotNull() {
		orderItem.setOrderId(null);
		assertFalse(orderItem.equals(other));
	}
	
	@Test
	public void orderItemOrderIdNotEqual() {
		other.setOrderId(5L);
		assertFalse(orderItem.equals(other));
	}
	
	@Test
	public void checkEqualityBetweenDifferentObjectsNullOrderId() {
		orderItem.setOrderId(null);
		other.setOrderId(null);
		assertTrue(orderItem.equals(other));
	}
	
	@Test
	public void nullId() {
		orderItem.setId(null);
		assertFalse(orderItem.equals(other));
	}
	
	@Test
	public void nullIdOnBoth() {
		orderItem.setId(null);
		other.setId(null);
		assertTrue(orderItem.equals(other));
	}
	
	@Test
	public void otherIdDifferent() {
		other.setId(2L);
		assertFalse(orderItem.equals(other));
	}
	
	@Test
	public void nullItemId() {
		orderItem.setItemId(null);
		assertFalse(orderItem.equals(other));
	}
	
	@Test
	public void nullItemIdOnBoth() {
		orderItem.setItemId(null);
		other.setItemId(null);
		assertTrue(orderItem.equals(other));
	}
	
	@Test
	public void otherItemIdDifferent() {
		other.setItemId(5L);
		assertFalse(orderItem.equals(other));
	}
	
	@Test
	public void constructorWithoutId() {
		OrderItem orderItem = new OrderItem(1L, 2L);
		assertNull(orderItem.getId());
		assertNotNull(orderItem.getOrderId());
		assertNotNull(orderItem.getItemId());
	}
	
	@Test
	public void hashCodeTest() {
		assertEquals(orderItem.hashCode(), other.hashCode());
	}
	@Test
	public void hashCodeTestWithNull() {
		OrderItem orderItem = new OrderItem(null, null, null);
		OrderItem other = new OrderItem(null, null, null);
		assertEquals(orderItem.hashCode(), other.hashCode());
	}
	
	@Test
	public void toStringTest() {
		String toString = "OrderItem [id=1, orderId=1, itemId=2]";
		assertEquals(toString, orderItem.toString());
	}
}

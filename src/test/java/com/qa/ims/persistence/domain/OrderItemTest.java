package com.qa.ims.persistence.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
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
		assertNotEquals(orderItem, (null));
	}
	
	@Test
	public void equalsWithDifferentObject() {
		assertNotEquals(orderItem, (new Object()));
	}
	
	@Test
	public void createOrderItemWithId() {
		assertEquals(1L, orderItem.getId(), 0);
		assertEquals(1L, orderItem.getOrderId(), 0);
		assertEquals(2L, orderItem.getItemId(), 0);
	}
	
	@Test
	public void checkEquality() {
		assertEquals(orderItem, (orderItem));
	}
	
	@Test
	public void checkEqualityBetweenDifferentObjects() {
		assertEquals(orderItem, other);
	}
	
	@Test
	public void orderItemOrderIdNullButOtherOrderIdNotNull() {
		orderItem.setOrderId(null);
		assertNotEquals(orderItem, other);
	}
	
	@Test
	public void orderItemOrderIdNotEqual() {
		other.setOrderId(5L);
		assertNotEquals(orderItem, other);
	}
	
	@Test
	public void checkEqualityBetweenDifferentObjectsNullOrderId() {
		orderItem.setOrderId(null);
		other.setOrderId(null);
		assertEquals(orderItem, other);
	}
	
	@Test
	public void nullId() {
		orderItem.setId(null);
		assertNotEquals(orderItem, other);
	}
	
	@Test
	public void nullIdOnBoth() {
		orderItem.setId(null);
		other.setId(null);
		assertEquals(orderItem, other);
	}
	
	@Test
	public void otherIdDifferent() {
		other.setId(2L);
		assertNotEquals(orderItem, other);
	}
	
	@Test
	public void nullItemId() {
		orderItem.setItemId(null);
		assertNotEquals(orderItem, other);
	}
	
	@Test
	public void nullItemIdOnBoth() {
		orderItem.setItemId(null);
		other.setItemId(null);
		assertEquals(orderItem, other);
	}
	
	@Test
	public void otherItemIdDifferent() {
		other.setItemId(5L);
		assertNotEquals(orderItem, other);
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

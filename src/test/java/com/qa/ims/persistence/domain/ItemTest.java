package com.qa.ims.persistence.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class ItemTest {
	
	private Item item;
	private Item other;
	
	@Before
	public void setUp() {
		item = new Item(1L, "iPhone 12", 899.0);
		other = new Item(1L, "iPhone 12", 899.0);
	}
	
	@Test
	public void settersTest() {
		assertNotNull(item.getId());
		assertNotNull(item.getTitle());
		assertNotNull(item.getPrice());
		
		item.setId(null);
		assertNull(item.getId());
		item.setTitle(null);
		assertNull(item.getTitle());
		item.setPrice(null);
		assertNull(item.getPrice());
		
	}
	
	@Test
	public void equalsWithNull() {
		assertNotEquals(item, null);
	}
	
	@Test
	public void equalsWithDifferentObject() {
		assertNotEquals(item, (new Object()));
	}
	
	@Test
	public void createItemWithId() {
		assertEquals(1L, item.getId(), 0);
		assertEquals("iPhone 12", item.getTitle());
		assertEquals(899.0, item.getPrice(), 0);
	}
	
	@Test
	public void checkEquality() {
		assertEquals(item, (item));
	}
	
	@Test
	public void checkEqualityBetweenDifferentObjects() {
		assertEquals(item, other);
	}
	
	@Test
	public void itemTitleNullButOtherTileNotNull() {
		item.setTitle(null);
		assertNotEquals(item, other);
	}
	
	@Test
	public void itemTitlesNotEqual() {
		other.setTitle("rhys");
		assertNotEquals(item, other);
	}
	
	@Test
	public void checkEqualityBetweenDifferentObjectsNullTitle() {
		item.setTitle(null);
		other.setTitle(null);
		assertEquals(item, other);
	}
	
	@Test
	public void nullId() {
		item.setId(null);
		assertNotEquals(item, other);
	}
	
	@Test
	public void nullIdOnBoth() {
		item.setId(null);
		other.setId(null);
		assertEquals(item, other);
	}
	
	@Test
	public void otherIdDifferent() {
		other.setId(2L);
		assertNotEquals(item, other);
	}
	
	@Test
	public void nullPrice() {
		item.setPrice(null);
		assertNotEquals(item, other);
	}
	
	@Test
	public void nullPriceOnBoth() {
		item.setPrice(null);
		other.setPrice(null);
		assertEquals(item, other);
	}
	
	@Test
	public void otherPriceDifferent() {
		other.setPrice(1099.0);
		assertNotEquals(item, other);
	}
	
	@Test
	public void constructorWithoutId() {
		Item item = new Item("iPhone 12", 899.0);
		assertNull(item.getId());
		assertNotNull(item.getTitle());
		assertNotNull(item.getPrice());
	}
	
	@Test
	public void hashCodeTest() {
		assertEquals(item.hashCode(), other.hashCode());
	}
	
	@Test
	public void hashCodeTestWithNull() {
		Item item = new Item(null, null);
		Item other = new Item(null, null);
		assertEquals(item.hashCode(), other.hashCode());
	}
	
	@Test
	public void toStringTest() {
		String toString = "Item [id=1, title=iPhone 12, price=899.0]";
		assertEquals(toString, item.toString());
	}

}

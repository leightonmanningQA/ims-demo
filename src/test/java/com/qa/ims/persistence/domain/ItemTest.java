package com.qa.ims.persistence.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

public class ItemTest {

	private Item item;
	private Item other;

	@Before
	public void setUp() {
		item = new Item(1L, "xbox", 499.99);
		other = new Item(1L, "xbox", 499.99);
		BigDecimal bd1 = BigDecimal.valueOf(item.getItemPrice());
	}

	@Test
	public void settersTest() {

		assertNotNull(item.getId());
		assertNotNull(item.getItemName());
		// assertNotNull(item.getItemPrice());

		item.setId(null);
		assertNull(item.getId());
		item.setItemName(null);
		assertNull(item.getItemName());
		item.setItemPrice(0.0);
		assertEquals(item.getItemPrice(), 0.0, 0.001);

	}

	@Test
	public void equalsWithNull() {
		assertFalse(item.equals(null));
	}

	@Test
	public void equalsWithDifferentObject() {
		assertFalse(item.equals(new Object()));
	}

	@Test
	public void createItemWithId() {
		assertEquals(1L, item.getId(), 0);
		assertEquals("xbox", item.getItemName());
		assertEquals(499.99, item.getItemPrice(), 0.1);
	}

	@Test
	public void checkEquality() {
		assertTrue(item.equals(item));
	}

	@Test
	public void checkEqualityBetweenDifferentObjects() {
		assertTrue(item.equals(other));
	}

	@Test
	public void itemNameNullButOtherNameNotNull() {
		item.setItemName(null);
		assertFalse(item.equals(other));
	}

	@Test
	public void itemNamesNotEqual() {
		other.setItemName("ps4");
		assertFalse(item.equals(other));
	}

	@Test
	public void checkEqualityBetweenDifferentObjectsNullName() {
		item.setItemName(null);
		other.setItemName(null);
		assertTrue(item.equals(other));
	}

	@Test
	public void nullId() {
		item.setId(null);
		assertFalse(item.equals(other));
	}

	@Test
	public void nullIdOnBoth() {
		item.setId(null);
		other.setId(null);
		assertTrue(item.equals(other));
	}

	@Test
	public void otherIdDifferent() {
		other.setId(2L);
		assertFalse(item.equals(other));
	}

	@Test
	public void nullItemPrice() {
		item.setItemPrice(0.0);
		assertFalse(item.equals(other));
	}

	@Test
	public void nullItemPriceOnBoth() {
		item.setItemPrice(0.0);
		other.setItemPrice(0.0);
		assertTrue(item.equals(other));
	}

	@Test
	public void otherItemPriceDifferent() {
		other.setItemPrice(10.99);
		assertTrue(item.equals(other));
	}

	@Test
	public void constructorWithoutId() {
		Item item = new Item("nintendo", 299.99);
		assertNull(item.getId());
		assertNotNull(item.getItemName());
		assertNotNull(item.getItemPrice());
	}

	@Test
	public void hashCodeTest() {
		assertEquals(item.hashCode(), other.hashCode());
	}

	@Test
	public void hashCodeTestWithNull() {
		Item item = new Item(null, 0.0);
		Item other = new Item(null, 0.0);
		assertEquals(item.hashCode(), other.hashCode());
	}

	@Test
	public void toStringTest() {
		String toString = "id:1 item name:xbox value:499.99";
		assertEquals(toString, item.toString());
	}
}

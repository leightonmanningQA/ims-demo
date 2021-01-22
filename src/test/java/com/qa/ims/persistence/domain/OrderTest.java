package com.qa.ims.persistence.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class OrderTest {

	private Order order;
	private Order other;
	private Order order2;
	private Order order3;
	private Order full;
	private Order full2;

	private Item item;

	@Before
	public void setUp() {
		order = new Order(1L, 2L, "bb9 j99");
		other = new Order(1L, 2L, "bb9 j99");
		order2 = new Order(6L, 2);
		order3 = new Order(1L, 2L, 20);

		full = new Order(1L, 2L, "bb9 j99", 3L, 2);
		full2 = new Order(1L, 2L, "bb9 j99", 3L, 2);

		item = new Item(1L, "asdsa", 20.0);

	}

	public void itemTest() {
		order.setItem(item);
		assertNotNull(order.getItem());
	}

	@Test
	public void settersTest() {
		assertNotNull(order.getOrderId());
		assertNotNull(order.getCustomerId());
		assertNotNull(order.getPostcode());
		assertNotNull(full.getItemId());
		assertNotNull(full.getQuantity());

		order.setOrderId(null);
		assertNull(order.getOrderId());
		order.setCustomerId(null);
		assertNull(order.getCustomerId());
		order.setPostcode(null);
		assertNull(order.getPostcode());
		full.setItemId(null);
		assertNull(full.getItemId());
		full.setQuantity(0);
		assertSame(0, full.getQuantity());
	}

	@Test
	public void equalsWithNull() {
		assertFalse(order.equals(null));
	}

	@Test
	public void equalsWithDifferentObject() {
		assertFalse(order.equals(new Object()));
	}

	@Test
	public void createOrderWithId() {
		assertEquals(1L, order.getOrderId(), 0);
		assertEquals("bb9 j99", order.getPostcode());
		assertEquals(2L, order.getCustomerId(), 0);
	}

	@Test
	public void checkEquality() {
		assertTrue(order.equals(order));
	}

	@Test
	public void checkEqualityBetweenDifferentObjects() {
		assertTrue(order.equals(other));
	}

	@Test
	public void orderPostNullButOtherNameNotNull() {
		order.setPostcode(null);
		assertFalse(order.equals(other));
	}

	@Test
	public void orderPostNotEqual() {
		other.setPostcode("bb5 6jh");
		assertFalse(order.equals(other));
	}

	@Test
	public void checkEqualityBetweenDifferentObjectsNullName() {
		order.setPostcode(null);
		other.setPostcode(null);
		assertTrue(order.equals(other));
	}

	@Test
	public void nullId() {
		order.setOrderId(null);
		assertFalse(order.equals(other));
	}

	@Test
	public void nullIdOnBoth() {
		order.setOrderId(null);
		other.setOrderId(null);
		assertTrue(order.equals(other));
	}

	@Test
	public void otherIdDifferent() {
		other.setOrderId(2L);
		assertFalse(order.equals(other));
	}

	@Test
	public void nullCustomerId() {
		order.setCustomerId(null);
		assertFalse(order.equals(other));
	}

	@Test
	public void nullcustomeridonboth() {
		order.setCustomerId(null);
		other.setCustomerId(null);
		assertTrue(order.equals(other));
	}

	@Test
	public void othercustomeridDifferent() {
		other.setCustomerId(5L);
		assertFalse(order.equals(other));
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
		String toString = "orderId=1,customerId=2,postcode=bb9 j99";
		assertEquals(toString, order.toString());
	}
}

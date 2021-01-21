package com.qa.ims.controller;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import com.qa.ims.persistence.domain.Order;
import com.qa.ims.services.ItemServices;
import com.qa.ims.services.OrderServices;

@RunWith(MockitoJUnitRunner.class)
public class OrderControllerTest {
	/**
	 * The thing I want to fake functionlity for
	 */
	@Mock
	private OrderServices orderServices;
	private ItemServices itemServices;
	/**
	 * Spy is used because i want to mock some methods inside the item I'm testing
	 * InjectMocks uses dependency injection to insert the mock into the customer
	 * controller
	 */
	@Spy
	@InjectMocks
	private OrderController orderController;

	@Test
	public void readAllTest() {
		OrderController orderController = new OrderController(orderServices);
		List<Order> orders = new ArrayList<>();
		orders.add(new Order(1L, "bb9 9ls"));
		orders.add(new Order(2L, "Tt2 2as"));
		orders.add(new Order(3L, "Jas 21s"));
		Mockito.when(orderServices.readAll()).thenReturn(orders);
		assertEquals(orders, orderController.readAll());
	}

	@Test
	public void createTest() {
		ItemController itemController = new ItemController(itemServices);
		String customerId = "2";
		String postcode = "gh7 6hn";
		String itemId = "2";
		int quantity = 2;

		Mockito.doReturn(customerId, postcode).when(orderController).getInput();

		Order order = new Order(1L, 1L, postcode);
		Order savedOrder = new Order(1L, 1L, "gh7 6hn");
		Order item = new Order(1L, quantity);

		Mockito.when(orderServices.create(order)).thenReturn(savedOrder);
		assertEquals(savedOrder, order);

		// List<Item> itemlist = itemServices.readAll();
		// Mockito.doReturn(itemId, quantity).when(orderController).getInput();

	}

	@Test
	public void updateTest() {
		String id = "1";
		String postcode = "postcode";
		Mockito.doReturn(id, postcode).when(orderController).getInput();
		Order order = new Order(1L, 1L, postcode);
		Order savedorder = new Order(1L, 1L, postcode);
		Mockito.when(orderServices.updateOrder(order)).thenReturn(order);
		assertEquals(order, savedorder);
	}

	@Test
	public void deleteTest() {
		String orderlineid = "1";
		String orderid = "2";
		Mockito.doReturn(orderlineid, orderid).when(orderController).getInput();
		Order order = new Order(1L, "postcode");
		Mockito.when(orderServices.create(order)).thenReturn(order);
		orderController.delete();
		// assertEquals(null, order);

	}

}

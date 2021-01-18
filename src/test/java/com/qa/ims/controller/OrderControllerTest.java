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
import com.qa.ims.services.OrderServices;

@RunWith(MockitoJUnitRunner.class)
public class OrderControllerTest {
	/**
	 * The thing I want to fake functionlity for
	 */
	@Mock
	private OrderServices orderService;

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
		OrderController orderController = new OrderController(orderService);
		List<Order> orders = new ArrayList<>();
		orders.add(new Order(1L, "bb1 2al"));
		orders.add(new Order(2L, "Tt2 2as"));
		orders.add(new Order(3L, "Jas 21s"));
		Mockito.when(orderService.readAll()).thenReturn(orders);
		assertEquals(orders, orderController.readAll());
	}
//	@Test
//	public void createTest() {
//		String firstName = "Leighton";
//		String surname = "Manning";
//		Mockito.doReturn(firstName, surname).when(customerController).getInput();
//
//		Customer customer = new Customer(firstName, surname);
//		Customer savedCustomer = new Customer(1L, "Chris", "Perrins");
//		Mockito.when(customerServices.create(customer)).thenReturn(savedCustomer);
//		assertEquals(savedCustomer, customerController.create());
//	}
//	@Test
//	public void updateTest() {
//		String id = "1";
//		String firstName = "Leighton";
//		String surname = "Manning";
//		Mockito.doReturn(id, firstName, surname).when(customerController).getInput();
//		Customer customer = new Customer(1L, firstName, surname);
//		Mockito.when(customerServices.update(customer)).thenReturn(customer);
//		assertEquals(customer, customerController.update());
//	}
//	@Test
//	public void deleteTest() {
//		String id = "1";
//		Mockito.doReturn(id).when(customerController).getInput();
//		customerController.delete();
//		Mockito.verify(customerServices, Mockito.times(1)).delete(1L);
//	}

}

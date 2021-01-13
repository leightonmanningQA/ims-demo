package com.qa.ims.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import com.qa.ims.services.ItemServices;

@RunWith(MockitoJUnitRunner.class)
public class ItemControllerTest {

	/**
	 * The thing I want to fake functionlity for
	 */
	@Mock
	private ItemServices itemServices;

	/**
	 * Spy is used because i want to mock some methods inside the item I'm testing
	 * InjectMocks uses dependency injection to insert the mock into the item
	 * controller
	 */
	@Spy
	@InjectMocks
	private ItemController itemController;

//	@Test
//	public void readAllTest() {
//		CustomerController customerController = new CustomerController(customerServices);
//		List<Customer> customers = new ArrayList<>();
//		customers.add(new Customer("Chris", "P"));
//		customers.add(new Customer("Rhys", "T"));
//		customers.add(new Customer("Nic", "J"));
//		Mockito.when(customerServices.readAll()).thenReturn(customers);
//		assertEquals(customers, customerController.readAll());
//	}

//	@Test
//	public void createTest() {
//		String firstName = "Chris";
//		String surname = "Perrins";
//		Mockito.doReturn(firstName, surname).when(customerController).getInput();
//		Customer customer = new Customer(firstName, surname);
//		Customer savedCustomer = new Customer(1L, "Chris", "Perrins");
//		Mockito.when(customerServices.create(customer)).thenReturn(savedCustomer);
//		assertEquals(savedCustomer, customerController.create());
//	}

	/**
	 * 
	 */
//	@Test
//	public void updateTest() {
//		String id = "1";
//		String itemName = "xbox";
//		String itemValue = "599.99";
//		Mockito.doReturn(id, itemName, itemValue).when(itemController).getInput();
//		Item item = new Item(1L, itemName, itemValue);
//		Mockito.when(itemServices.update(item)).thenReturn(item);
//		assertEquals(item, itemController.update());
//	}

	/**
	 * Delete doesn't return anything, so we can just verify that it calls the
	 * delete method
	 */
	@Test
	public void deleteTest() {
		String id = "1";
		Mockito.doReturn(id).when(itemController).getInput();
		itemController.delete();
		Mockito.verify(itemServices, Mockito.times(1)).delete(1L);
	}

}

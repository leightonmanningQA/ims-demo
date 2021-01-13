package com.qa.ims.services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.qa.ims.persistence.dao.Dao;
import com.qa.ims.persistence.domain.Item;

@RunWith(MockitoJUnitRunner.class)
public class ItemServicesTest {

	@Mock
	private Dao<Item> itemDao;

	@InjectMocks
	private ItemServices itemServices;

	@Test
	public void itemrServicesCreate() {
		Item item = new Item("xbox", 499.99);
		itemServices.create(item);
		Mockito.verify(itemDao, Mockito.times(1)).create(item);
	}

//	@Test
//	public void customerServicesRead() {
//		customerServices.readAll();
//		Mockito.verify(customerDao, Mockito.times(1)).readAll();
//	}

//	@Test
//	public void customerServicesUpdate() {
//		Customer customer = new Customer("chris", "perrins");
//		customerServices.update(customer);
//		Mockito.verify(customerDao, Mockito.times(1)).update(customer);
//	}

//	@Test
//	public void customerServicesDelete() {
//		customerServices.delete(1L);;
//		Mockito.verify(customerDao, Mockito.times(1)).delete(1L);
//	}
}

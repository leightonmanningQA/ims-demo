package com.qa.ims.services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.qa.ims.persistence.dao.Dao;
import com.qa.ims.persistence.domain.Customer;

@RunWith(MockitoJUnitRunner.class)
public class CustomerServicesTest {

	@Mock // used to create the mock object to be injected
	private Dao<Customer> customerDao;

	@InjectMocks // used to create and inject the mock object
	private CustomerServices customerServices;

	@Test
	public void customerServicesCreate() {
		Customer customer = new Customer("Leighton", "Manning");
		customerServices.create(customer);
		Mockito.verify(customerDao, Mockito.times(1)).create(customer);
	}

	@Test
	public void customerServicesRead() {
		customerServices.readAll();
		Mockito.verify(customerDao, Mockito.times(1)).readAll();
	}

	@Test
	public void customerServicesUpdate() {
		Customer customer = new Customer("leighton", "manning");
		customerServices.update(customer);
		Mockito.verify(customerDao, Mockito.times(1)).update(customer);
	}

	@Test
	public void customerServicesDelete() {
		customerServices.delete(1L);
		Mockito.verify(customerDao, Mockito.times(1)).delete(1L);
	}
}

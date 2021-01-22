package com.qa.ims.services;

import java.util.List;

import com.qa.ims.persistence.dao.Dao;
import com.qa.ims.persistence.domain.Customer;
import com.qa.ims.persistence.domain.Order;

public class CustomerServices implements CrudServices<Customer> {

	private Dao<Customer> customerDao;

	public CustomerServices(Dao<Customer> customerDao) {
		this.customerDao = customerDao;
	}

	public List<Customer> readAll() {
		return customerDao.readAll();
	}

	public Customer create(Customer customer) {
		return customerDao.create(customer);
	}

	public Customer update(Customer customer) {
		return customerDao.update(customer);
	}

	public void delete(Long id) {
		customerDao.delete(id);
	}

	@Override
	public void deleteorder(Long orderlineId, Long orderId) {
		// TODO Auto-generated method stub

	}

	@Override
	public Order updateOrder(Order order) {
		// TODO Auto-generated method stub
		return null;
	}

}

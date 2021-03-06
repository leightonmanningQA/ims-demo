package com.qa.ims.services;

import java.util.List;

import com.qa.ims.persistence.dao.Dao;
import com.qa.ims.persistence.domain.Order;

public class OrderServices implements CrudServices<Order> {
	private Dao<Order> orderDao;

	public OrderServices(Dao<Order> orderDao) {
		this.orderDao = orderDao;
	}

	public List<Order> readAll() {
		return orderDao.readAll();
	}

	public Order create(Order order) {
		return orderDao.create(order);
	}

	public Order update(Order order) {
		return orderDao.update(order);
	}

	public void delete(Long orderId) {
		orderDao.delete(orderId);
	}

	@Override
	public void deleteorder(Long orderlineId, Long orderId) {
		orderDao.deleteorder(orderlineId, orderId);

	}

	@Override
	public Order updateOrder(Order order) {
		return orderDao.updateOrder(order);

	}

}

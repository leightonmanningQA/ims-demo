package com.qa.ims.persistence.dao;

import java.util.List;

import com.qa.ims.persistence.domain.Order;

public interface Dao<T> {

	List<T> readAll();

	T create(T t);

	T update(T t);

	void delete(long id);

	void deleteorder(long orderlineId, long orderId);

	Order updateOrder(Order order);

}

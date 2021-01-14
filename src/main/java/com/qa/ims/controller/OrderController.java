package com.qa.ims.controller;

import java.util.List;

import org.apache.log4j.Logger;

import com.qa.ims.persistence.domain.Order;
import com.qa.ims.services.CrudServices;
import com.qa.ims.utils.Utils;

public class OrderController implements CrudController<Order> {

	public static final Logger LOGGER = Logger.getLogger(OrderController.class);

	private CrudServices<Order> orderService;

	public OrderController(CrudServices<Order> orderService) {
		this.orderService = orderService;
	}

	String getInput() {
		return Utils.getInput();
	}

	/**
	 * Reads all orders to the logger
	 */
	@Override
	public List<Order> readAll() {
		List<Order> orders = orderService.readAll();
		for (Order order : orders) {
			LOGGER.info(order.toString());
		}
		return orders;
	}

	/**
	 * Creates a order by taking in user input
	 */
	@Override
	public Order create() {
		LOGGER.info("Please enter your customer ID.");
		Long customerId = Long.valueOf(getInput());
		LOGGER.info("Please enter a postcode");
		String postcode = getInput();
		Order order = orderService.create(new Order(customerId, postcode));
		LOGGER.info("Order created, your order ID is: " + order.getOrderId() + " Please make note of it");
		return order;
	}

	/**
	 * Updates an existing order by taking in user input
	 */
	@Override
	public Order update() {
		LOGGER.info("Please enter the id of the order you would like to update");
		Long orderId = Long.valueOf(getInput());
		LOGGER.info("Please enter a customer id you would like to change to");
		Long customerId = Long.valueOf(getInput());
		LOGGER.info("Please enter a new postcode");
		String postcode = getInput();
		Order order = orderService.update(new Order(orderId, customerId, postcode));
		LOGGER.info("Customer Updated");
		return order;
	}

	/**
	 * Deletes an existing order by the id of the order
	 */
	@Override
	public void delete() {
		LOGGER.info("Please enter the id of the order you would like to delete");
		Long orderId = Long.valueOf(getInput());
		orderService.delete(orderId);
	}

}

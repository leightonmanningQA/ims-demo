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

	int getIntinput() {
		return Utils.getIntinput();
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
		LOGGER.info("Order created, your order ID is: " + order.getOrderId());
		LOGGER.info("Enter the items ID you wish to add to the order, or type no to quit.");
		String answer = getInput();

		while (answer != "no") {
			Long itemId = Long.valueOf(getInput());
			LOGGER.info("Enter the quantity you would like");
			int quantity = getIntinput();

			Order orderline = orderService.addItem(new Order(order.getOrderId(), itemId, quantity));
		}

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
		LOGGER.info("Do you want to delete an item from and order or full order(item/order)");
		String answer = getInput();
		if (answer == "order") {
			LOGGER.info("Enter the ID of the order you wish to delete");
			Long orderId = Long.valueOf(getInput());
			orderService.delete(orderId);
		} else if (answer == "item") {
			LOGGER.info("Enter the ID of the order you wish to delete");
		}
	}

//	public void additem() {
//		LOGGER.info("Please enter the id of your order");
//		Long orderId = Long.valueOf(getInput());
//		LOGGER.info("Please enter the id of the item you want");
//		Long itemId = Long.valueOf(getInput());
//		LOGGER.info("How many of the item do you want");
//		int amount = getIntinput();
//		orderService.additem();
//	}

}

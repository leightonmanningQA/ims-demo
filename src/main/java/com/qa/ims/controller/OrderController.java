package com.qa.ims.controller;

import java.util.List;

import org.apache.log4j.Logger;

import com.qa.ims.persistence.domain.Item;
import com.qa.ims.persistence.domain.Order;
import com.qa.ims.services.CrudServices;
import com.qa.ims.utils.Utils;

public class OrderController implements CrudController<Order> {

	public static final Logger LOGGER = Logger.getLogger(OrderController.class);

	private CrudServices<Order> orderService;
	private CrudServices<Item> ItemServices;
	public static boolean option;

	public OrderController(CrudServices<Order> orderService, CrudServices<Item> itemService) {
		super();
		this.orderService = orderService;
		this.ItemServices = itemService;
	}

	public OrderController(CrudServices<Order> orderService) {
		super();
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
	 * Creates a order by taking in user input. Also adds an item to an order.
	 */

	@Override
	public Order create() {
		String temp = "";
		Double calcPrice = 0.0;
		LOGGER.info("Please enter your customer ID.");
		Long customerId = Long.valueOf(getInput());
		LOGGER.info("Please enter a postcode");
		String postcode = getInput();

		Order order = orderService.create(new Order(1L, customerId, postcode));
		LOGGER.info("Order created, your order ID is: " + order.getOrderId());
		List<Item> itemlist = ItemServices.readAll();
		Item item = null;
		boolean run = false;

		while (!run) {
			LOGGER.info("Enter the items ID you wish to add to the order");
			Long itemId = Long.valueOf(getInput());
			LOGGER.info("Enter the quantity you would like");
			int quantity = getIntinput();
			for (Item i : itemlist) {
				if (i.getId() == itemId) {
					item = i;
					break;
				}
			}
			order.setItem(item);
			order.setQuantity(quantity);
			Double price = quantity * item.getItemPrice();

			Double finalPrice;
			order = orderService.update(order);// in order dao
			LOGGER.info("Are you done with your order, y for yes n for no");
			temp = getInput().toLowerCase();
			if (temp.equals("y")) {
				run = true;
				finalPrice = calcPrice + price;
				LOGGER.info("The final price for those items is: " + finalPrice);
			}
			LOGGER.info("The price for those items is: " + price);
			calcPrice = price + calcPrice;
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
		LOGGER.info("Please enter a new postcode");
		String postcode = getInput();
		Order order = orderService.updateOrder(new Order(orderId, postcode));
		LOGGER.info("Order Updated");
		return order;
	}

	/**
	 * Deletes an existing order by the id of the order
	 */
	@Override
	public void delete() {
		LOGGER.info("Do you want to delete an item from an order or a full order(item/order)");
		String answer = getInput();
		if (answer.equals("item")) {
			LOGGER.info("Enter the ID of the orderline you wish to delete");
			Long orderlineId = Long.valueOf(getInput());
			orderService.delete(orderlineId);
		} else if (answer.equals("order")) {
			LOGGER.info("Enter the ID of the orderline you wish to delete");
			Long orderlineId = Long.valueOf(getInput());
			LOGGER.info("Enter the ID of the order you wish to delete");
			Long orderId = Long.valueOf(getInput());
			orderService.deleteorder(orderlineId, orderId);
		}
		LOGGER.info("Orderline Deleted");
	}

}

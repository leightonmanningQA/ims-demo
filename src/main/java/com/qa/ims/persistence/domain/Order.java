package com.qa.ims.persistence.domain;

import java.util.ArrayList;

public class Order {

	private Long orderId;
	private Long customerId;
	private String postcode;
	private Item item = null;
	private Long itemId;
	private int quantity;
	private ArrayList<Item> iteminorder = new ArrayList();

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public Order(Long customerId, String postcode) {
		this.customerId = customerId;
		this.postcode = postcode;
	}

	public Order(Long itemId, int quantity) {
		this.customerId = itemId;
		this.quantity = quantity;
	}

	public Order(Long orderId, Long customerId, String postcode) {
		this.orderId = orderId;
		this.customerId = customerId;
		this.postcode = postcode;
	}

	public Order(Long orderId, Long itemId, int quantity) {
		super();
		this.orderId = orderId;
		this.itemId = itemId;
		this.quantity = quantity;
	}

	public Order(Long orderId, Long customerId, String postcode, Long itemId, int quantity) {
		super();
		this.orderId = orderId;
		this.customerId = customerId;
		this.postcode = postcode;
		this.itemId = itemId;
		this.quantity = quantity;
	}

	public ArrayList<Item> getIteminorder() {
		return iteminorder;
	}

	public void setIteminorder(ArrayList<Item> iteminorder) {
		this.iteminorder = iteminorder;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "orderId=" + orderId + ", customerId=" + customerId + ", postcode=" + postcode;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((orderId == null) ? 0 : orderId.hashCode());
		result = prime * result + ((customerId == null) ? 0 : customerId.hashCode());
		result = prime * result + ((postcode == null) ? 0 : postcode.hashCode());
		return result;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		if (orderId == null) {
			if (other.orderId != null)
				return false;
		} else if (!orderId.equals(other.orderId))
			return false;
		if (customerId == null) {
			if (other.customerId != null)
				return false;
		} else if (!customerId.equals(other.customerId))
			return false;
		if (postcode == null) {
			if (other.postcode != null)
				return false;
		} else if (!postcode.equals(other.postcode))
			return false;
		return true;
	}
}

package com.qa.ims.persistence.domain;

public class Item {

	private Long id;
	private String itemName;
	private double itemPrice;

	public Item(String itemName, double itemPrice) {
		this.itemName = itemName;
		this.itemPrice = itemPrice;
	}

	public Item(Long id, String itemName, double itemPrice) {
		this.id = id;
		this.itemName = itemName;
		this.itemPrice = itemPrice;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public double getItemPrice() {
		return itemPrice;
	}

	public void setItemPrice(double itemPrice) {
		this.itemPrice = itemPrice;
	}

	public String toString() {
		return "id:" + id + " item name:" + itemName + " value:" + itemPrice;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((itemName == null) ? 0 : itemName.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		// result = prime * result + ((itemPrice == 0.0) ? 0 : itemPrice.hashCode());
		// //ISSUE WITH DOUBLE INSTEAD OF STRING.
		return result;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Item other = (Item) obj;
		if (itemName == null) {
			if (other.itemName != null)
				return false;
		} else if (!itemName.equals(other.itemName))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		// if (surname == null) {
		// if (other.surname != null)
		// return false; again with the variable being a double
		// } else if (!surname.equals(other.surname))
		// return false;
		return true;
	}

}

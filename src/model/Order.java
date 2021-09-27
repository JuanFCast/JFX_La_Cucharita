package model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Order {
	
	private String UUID;
	private List<DishOrder> orderedDishes;
	private ORDER_STATUS status;
	private String orderDate;
	
	public Order(String UUID, ArrayList<DishOrder> order) {
		this.UUID = UUID;
		orderedDishes = order;
		status = ORDER_STATUS.PENDING;
		orderDate = "" + LocalDateTime.now();
		String[]s = orderDate.split("T");
		orderDate = s[0];
	}

	public String getUUID() {
		return UUID;
	}

	public void setUUID(String uUID) {
		UUID = uUID;
	}

	public ORDER_STATUS getStatus() {
		return status;
	}

	public void setStatus(ORDER_STATUS status) {
		this.status = status;
	}

	public List<DishOrder> getOrderedDishes() {
		return orderedDishes;
	}

	public String getOrderDate() {
		return orderDate;
	}
	
	
	
}

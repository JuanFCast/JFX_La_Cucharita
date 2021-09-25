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
	}
	
	
	
}

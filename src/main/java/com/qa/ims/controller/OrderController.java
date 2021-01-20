package com.qa.ims.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.qa.action.OrderReadAction;
import com.qa.action.OrderUpdateAction;
import com.qa.ims.persistence.domain.Order;
import com.qa.ims.services.CrudServicesExtended;
import com.qa.ims.utils.Utils;

/**
 * Takes in order details for CRUD functionality
 *
 */
public class OrderController implements CrudController<Order> {

	public static final Logger LOGGER = Logger.getLogger(OrderController.class);
	
	private CrudServicesExtended<Order> orderService;
	
	public OrderController(CrudServicesExtended<Order> orderService) {
		this.orderService = orderService;
	}
	

	public String getInput() {
		return Utils.getInput();
	}
	
	public Long getLong() {
		return Utils.getLong();
	}
	
	// easier for testing
	OrderUpdateAction getUpdateAction() {
		return OrderUpdateAction.getAction();
	}
	
	OrderReadAction getReadAction() {
		return OrderReadAction.getAction();
	}
	
	/**
	 * Reads either all orders or one specific order to the logger
	 */
	@Override
	public List<Order> read() {
		LOGGER.info("Would you like to view all orders or the basket of one order?");
		OrderReadAction.printActions();
		
		List<Order> orderList = new ArrayList<>();
		switch (getReadAction()) {
		case ALL:
			orderList = readAll();
			break;
		case ONE:
			orderList = readOne();
			break;
		}

		return orderList;
	}
	
	public List<Order> readAll() {
		List<Order> orderList = orderService.readAll();
		for(Order order: orderList) {
			LOGGER.info(order.toString());
		}
		
		return orderList;
	}
	
	public List<Order> readOne() {
		LOGGER.info("Please enter the id of the order you would like to view the basket of");
		Long orderId = getLong();
		Order order = orderService.readById(orderId);
		double cost = orderService.calculateCost(order);
		List<Order> orderList = new ArrayList<>();
		orderList.add(order);
		
		LOGGER.info(orderList.get(0));
		String total = String.format("%.2f", cost);
		LOGGER.info("Total: £" + total);
		
		return orderList;
	}

	/**
	 * Creates a order by taking in user input
	 */
	@Override
	public Order create() {
		LOGGER.info("Please enter a customer ID");
		Long customerId = getLong();
		Order order = orderService.create(new Order(customerId));
		LOGGER.info("Order created");
		return order;
	}

	/**
	 * Updates an existing order by taking in user input
	 */
	@Override
	public Order update() {
		LOGGER.info("Please enter the id of the order you would like to update");
		Long id = getLong();
		LOGGER.info("Would you like to add or remove an item from order " + id + "?");
		OrderUpdateAction.printActions();
		
		Order order = null;
		switch(getUpdateAction()) {
		case ADD:
			order = addTo(id);
			break;
		case DELETE:
			order = deleteFrom(id);
			break;
		}
		
		LOGGER.info("Order Updated");
		LOGGER.info(order);
		return order;
	}
	
	public Order addTo(long id) {
		LOGGER.info("Please enter the id of the item you would like to add to order " + id);
		Long itemId = getLong();
		return orderService.addTo(id, itemId);
	}
	
	public Order deleteFrom(long id) {
		LOGGER.info("Please enter the id of the item you would like to delete from order " + id);
		Long itemId = getLong();
		return orderService.deleteFrom(id, itemId);
	}

	/**
	 * Deletes an existing order by the id of the order
	 */
	@Override
	public void delete() {
		LOGGER.info("Please enter the id of the order you would like to delete");
		Long id = getLong();
		orderService.delete(id);
		LOGGER.info("Order Deleted");
	}
	
}

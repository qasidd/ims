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
	

	String getInput() {
		return Utils.getInput();
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
		Long orderId;
		switch (getReadAction()) {
		case ALL:
			orderList = orderService.readAll();
			for(Order order: orderList) {
				LOGGER.info(order.toString());
			}
			break;
		case ONE:
			LOGGER.info("Please enter the id of the order you would like to view the basket of");
			orderId = Long.valueOf(getInput());
			Order order = orderService.readById(orderId);
			double cost = orderService.calculateCost(order);
			orderList.add(order);
			LOGGER.info(orderList.get(0));
			LOGGER.info(String.format("Total: £%.2f", cost));
			break;
		default:
			break;
		}

		return orderList;
	}

	/**
	 * Creates a order by taking in user input
	 */
	@Override
	public Order create() {
		LOGGER.info("Please enter a customer ID");
		Long customerId = Long.valueOf(getInput());
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
		Long id = Long.valueOf(getInput());
		LOGGER.info("Would you like to add or remove an item from order " + id + "?");
		OrderUpdateAction.printActions();
		
		Order order = null;
		Long itemId;
		switch(getUpdateAction()) {
		case ADD:
			LOGGER.info("Please enter the id of the item you would like to add to order " + id);
			itemId = Long.valueOf(getInput());
			order = orderService.addTo(orderService.readById(id), itemId);
			break;
		case DELETE:
			LOGGER.info("Please enter the id of the item you would like to delete from order " + id + ":");
			itemId = Long.valueOf(getInput());
			order = orderService.deleteFrom(orderService.readById(id), itemId);
			break;
		}
		
		LOGGER.info("Order Updated");
		return order;
	}

	/**
	 * Deletes an existing order by the id of the order
	 */
	@Override
	public void delete() {
		LOGGER.info("Please enter the id of the order you would like to delete");
		Long id = Long.valueOf(getInput());
		orderService.delete(id);
		LOGGER.info("Order Deleted");
	}
	
}

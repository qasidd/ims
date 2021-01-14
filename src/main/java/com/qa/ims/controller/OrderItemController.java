package com.qa.ims.controller;

import java.util.List;

import org.apache.log4j.Logger;

import com.qa.ims.persistence.domain.OrderItem;
import com.qa.ims.services.CrudServices;
import com.qa.ims.utils.Utils;

/**
 * Takes in orderItem details for CRUD functionality
 *
 */
public class OrderItemController implements CrudController<OrderItem>{

	public static final Logger LOGGER = Logger.getLogger(OrderItemController.class);
	
	private CrudServices<OrderItem> orderItemService;
	
	public OrderItemController(CrudServices<OrderItem> orderItemService) {
		this.orderItemService = orderItemService;
	}
	

	String getInput() {
		return Utils.getInput();
	}
	
	/**
	 * Reads all orderItems to the logger
	 */
	@Override
	public List<OrderItem> readAll() {
		List<OrderItem> orderItems = orderItemService.readAll();
		for(OrderItem orderItem: orderItems) {
			LOGGER.info(orderItem.toString());
		}
		return orderItems;
	}

	/**
	 * Creates a orderItem by taking in user input
	 */
	@Override
	public OrderItem create() {
		LOGGER.info("Please enter an order id");
		Long orderId = Long.valueOf(getInput());
		LOGGER.info("Please enter an item id");
		Long itemId = Long.valueOf(getInput());
		OrderItem orderItem = orderItemService.create(new OrderItem(orderId, itemId));
		LOGGER.info("OrderItem created");
		return orderItem;
	}

	/**
	 * Updates an existing orderItem by taking in user input
	 */
	@Override
	public OrderItem update() {
		LOGGER.info("Please enter the id of the orderItem you would like to update");
		Long id = Long.valueOf(getInput());
		LOGGER.info("Please enter an order id");
		Long orderId = Long.valueOf(getInput());
		LOGGER.info("Please enter an item id");
		Long itemId = Long.valueOf(getInput());
		OrderItem orderItem = orderItemService.update(new OrderItem(id, orderId, itemId));
		LOGGER.info("OrderItem Updated");
		return orderItem;
	}

	/**
	 * Deletes an existing orderItem by the id of the orderItem
	 */
	@Override
	public void delete() {
		LOGGER.info("Please enter the id of the orderItem you would like to delete");
		Long id = Long.valueOf(getInput());
		orderItemService.delete(id);
	}
	
}


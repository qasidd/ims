package com.qa.action;

import org.apache.log4j.Logger;

import com.qa.ims.utils.Utils;

/**
 * OrderAction is a collection of commands which are used to determine the type of
 * function to apply to an order.
 *
 */
public enum OrderUpdateAction implements Action {
	ADD("To add an item to an order"), DELETE("To delete an item from an order");
	
	public static final Logger LOGGER = Logger.getLogger(OrderUpdateAction.class);

	private String description;
	
	private OrderUpdateAction() { }
	
	OrderUpdateAction(String description) {
		this.description = description;
	}
	
	/**
	 * Describes the action
	 */
	public String getDescription() {
		return this.name() + ": " + this.description;
	}

	/**
	 * Prints out all possible actions
	 */
	public static void printActions() {
		for (OrderUpdateAction action : OrderUpdateAction.values()) {
			LOGGER.info(action.getDescription());
		}
	}

	/**
	 * Gets an action based on a users input. If user enters a non-specified
	 * enumeration, it will ask for another input.
	 * 
	 * @return Action type
	 */
	public static OrderUpdateAction getAction() {
		OrderUpdateAction action;
		while (true) {
			try {
				action = OrderUpdateAction.valueOf(Utils.getInput().toUpperCase());
				break;
			} catch (IllegalArgumentException e) {
				LOGGER.error("Invalid selection please try again");
			}
		}
		return action;
	}
	
}

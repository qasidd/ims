package com.qa.action;

import org.apache.log4j.Logger;

import com.qa.ims.utils.Utils;

/**
 * OrderAction is a collection of commands which are used to determine the type of
 * function to apply to an order.
 *
 */
public enum OrderAction implements Action {
	ADD("To add an item to an order"), DELETE("To delete an item from an order");
	
	public static final Logger LOGGER = Logger.getLogger(OrderAction.class);

	private String description;
	
	private OrderAction() { }
	
	OrderAction(String description) {
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
		for (OrderAction action : OrderAction.values()) {
			LOGGER.info(action.getDescription());
		}
	}

	/**
	 * Gets an action based on a users input. If user enters a non-specified
	 * enumeration, it will ask for another input.
	 * 
	 * @return Action type
	 */
	public static OrderAction getAction() {
		OrderAction action;
		while (true) {
			try {
				action = OrderAction.valueOf(Utils.getInput().toUpperCase());
				break;
			} catch (IllegalArgumentException e) {
				LOGGER.error("Invalid selection please try again");
			}
		}
		return action;
	}
	
}

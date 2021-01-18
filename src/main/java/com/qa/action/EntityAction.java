package com.qa.action;

import org.apache.log4j.Logger;

import com.qa.ims.utils.Utils;

/**
 * EntityAction is a collection of commands which are used to determine the type of
 * function to apply to an entity.
 *
 */
public enum EntityAction implements Action {
	CREATE("To save a new entry into the database"), READ("To read an entry from the database"),
	UPDATE("To change an entry already in the database"), DELETE("To remove an entry from the database"),
	RETURN("To return to domain selection");

	public static final Logger LOGGER = Logger.getLogger(EntityAction.class);

	private String description;

	private EntityAction() {
	}

	EntityAction(String description) {
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
		for (EntityAction action : EntityAction.values()) {
			LOGGER.info(action.getDescription());
		}
	}

	/**
	 * Gets an action based on a users input. If user enters a non-specified
	 * enumeration, it will ask for another input.
	 * 
	 * @return Action type
	 */
	public static EntityAction getAction() {
		EntityAction action;
		while (true) {
			try {
				action = EntityAction.valueOf(Utils.getInput().toUpperCase());
				break;
			} catch (IllegalArgumentException e) {
				LOGGER.error("Invalid selection please try again");
			}
		}
		return action;
	}

}

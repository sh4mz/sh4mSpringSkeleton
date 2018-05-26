package net.sh4m.genericjdbc.exception;

import java.io.Serializable;

public class StaleObjectStateException extends RuntimeException {

	private static final long serialVersionUID = 4370611367112354542L;

	private final String entityName;
	private final Serializable identifier;

	/**
	 * 
	 */
	public StaleObjectStateException(String persistentClass,
			Serializable identifier) {
		this(persistentClass, identifier,
				"Row was updated or deleted by another transaction.");
	}

	/**
	 * 
	 */
	public StaleObjectStateException(String persistentClass,
			Serializable identifier, String message) {
		super(message);
		this.entityName = persistentClass;
		this.identifier = identifier;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getMessage() {
		StringBuilder message = new StringBuilder(super.getMessage());
		message.append(" : ");
		message.append("[").append(entityName);
		message.append("#").append(identifier).append("]");
		return message.toString();
	}
}

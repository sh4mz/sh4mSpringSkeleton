package net.sh4m.genericjdbc.exception;

public class NotImplementedException extends UnsupportedOperationException {
	private static final long serialVersionUID = 3592636645833957042L;
	private final String code;

	/**
	 * Constructs a NotImplementedException.
	 * 
	 * @param message
	 *            description of the exception
	 */
	public NotImplementedException(final String message) {
		this(message, (String) null);
	}

	/**
	 * Constructs a NotImplementedException.
	 * 
	 * @param cause
	 *            cause of the exception
	 */
	public NotImplementedException(final Throwable cause) {
		this(cause, null);
	}

	/**
	 * Constructs a NotImplementedException.
	 * 
	 * @param message
	 *            description of the exception
	 * @param cause
	 *            cause of the exception
	 */
	public NotImplementedException(final String message, final Throwable cause) {
		this(message, cause, null);
	}

	/**
	 * Constructs a NotImplementedException.
	 * 
	 * @param message
	 *            description of the exception
	 * @param code
	 */
	public NotImplementedException(final String message, final String code) {
		super(message);
		this.code = code;
	}

	/**
	 * Constructs a NotImplementedException.
	 * 
	 * @param cause
	 *            cause of the exception
	 * @param code
	 *            code indicating a resource for more information regarding the
	 *            lack of implementation
	 */
	public NotImplementedException(final Throwable cause, final String code) {
		super(cause);
		this.code = code;
	}

	/**
	 * Constructs a NotImplementedException.
	 * 
	 * @param message
	 *            description of the exception
	 * @param cause
	 *            cause of the exception
	 * @param code
	 *            code indicating a resource for more information regarding the
	 *            lack of implementation
	 */
	public NotImplementedException(final String message, final Throwable cause,
			final String code) {
		super(message, cause);
		this.code = code;
	}

	/**
	 * Obtain the not implemented code. This is an unformatted piece of text
	 * intended to point to further information regarding the lack of
	 * implementation. It might, for example, be an issue tracker ID or a URL.
	 * 
	 * @return a code indicating a resource for more information regarding the
	 *         lack of implementation
	 */
	public String getCode() {
		return this.code;
	}
}
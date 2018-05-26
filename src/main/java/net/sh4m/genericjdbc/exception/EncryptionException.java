package net.sh4m.genericjdbc.exception;

import org.springframework.core.NestedRuntimeException;

/**
 * Exception utility class for throwing any Encryption/Decryption related
 * exceptions.
 * 
 * @see NestedRuntimeException
 */
public class EncryptionException extends NestedRuntimeException {
	private static final long serialVersionUID = 4081968455408863021L;

	/**
	 * Construct a <code>EncryptionException</code> with the specified detail
	 * message and nested exception.
	 * 
	 * @param message
	 *            the detail message
	 * @param cause
	 *            the nested exception
	 */
	public EncryptionException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Construct a <code>EncryptionException</code> with the specified detail
	 * message.
	 * 
	 * @param message
	 *            the detail message
	 */
	public EncryptionException(String message) {
		super(message);
	}
}

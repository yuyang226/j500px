/**
 * 
 */
package com.github.yuyang226.j500px;

/**
 * @author yayu
 *
 */
public class J500pxException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8873724447070949051L;

	/**
	 * 
	 */
	public J500pxException() {
		super();
	}

	/**
	 * @param message
	 */
	public J500pxException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public J500pxException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public J500pxException(String message, Throwable cause) {
		super(message, cause);
	}

}

package com.target.myretail.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Gautham Reddy Created On : 03/17/2018
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Product not found.")
public class ProductNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 504753681963962824L;

	/** The err code. */
	private String errCode;
	
	/**
	 * Gets the err code.
	 *
	 * @return the err code
	 */
	public String getErrCode() {
		return errCode;
	}

	/**
	 * Sets the err code.
	 *
	 * @param errCode the new err code
	 */
	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}

	/**
	 * Gets the err msg.
	 *
	 * @return the err msg
	 */
	public String getErrMsg() {
		return errMsg;
	}

	/**
	 * Sets the err msg.
	 *
	 * @param errMsg the new err msg
	 */
	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}

	/** The err msg. */
	private String errMsg;

	/**
	 * Default Constructor
	 */
	public ProductNotFoundException(){
		
	}
	
	/**
	 * Instantiates a new content mapper exception.
	 *
	 * @param errCode the err code
	 * @param errMsg the err msg
	 */
	public ProductNotFoundException(String errCode, String errMsg) {
		this.errCode = errCode;
		this.errMsg = errMsg;
	}	
	
	/**
	 * Instantiates a new content mapper exception.
	 *
	 * @param errMsg the err msg
	 */
	public ProductNotFoundException(String errMsg) {
		this.errMsg = errMsg;
	}
	
	/**
	 * Instantiates a new content mapper exception.
	 *
	 * @param message the message
	 * @param t the t
	 */
	public ProductNotFoundException(final String message, final Throwable t) {
		super(message, t);
	}
}

package com.target.myretail.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Gautham Reddy Created On : 03/17/2018
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Product id in body does not match with id in path.")
public class ProductIdMisMatchException extends RuntimeException {

	private static final long serialVersionUID = -4498861160085111973L;
	
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
	public ProductIdMisMatchException(){
		
	}
	
	/**
	 * Instantiates a new content mapper exception.
	 *
	 * @param errCode the err code
	 * @param errMsg the err msg
	 */
	public ProductIdMisMatchException(String errCode, String errMsg) {
		this.errCode = errCode;
		this.errMsg = errMsg;
	}	
	
	/**
	 * Instantiates a new content mapper exception.
	 *
	 * @param errMsg the err msg
	 */
	public ProductIdMisMatchException(String errMsg) {
		this.errMsg = errMsg;
	}
	
	/**
	 * Instantiates a new content mapper exception.
	 *
	 * @param message the message
	 * @param t the t
	 */
	public ProductIdMisMatchException(final String message, final Throwable t) {
		super(message, t);
	}

}

package com.target.myretail.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Gautham Reddy Created On : 03/17/2018
 */
@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Failed to fetch product name from external API.")
public class ExternalAPIException extends RuntimeException {

	private static final long serialVersionUID = 6569499520626898508L;
	
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
	public ExternalAPIException(){
		
	}
	
	/**
	 * Instantiates a new content mapper exception.
	 *
	 * @param errCode the err code
	 * @param errMsg the err msg
	 */
	public ExternalAPIException(String errCode, String errMsg) {
		this.errCode = errCode;
		this.errMsg = errMsg;
	}	
	
	/**
	 * Instantiates a new content mapper exception.
	 *
	 * @param errMsg the err msg
	 */
	public ExternalAPIException(String errMsg) {
		this.errMsg = errMsg;
	}
	
	/**
	 * Instantiates a new content mapper exception.
	 *
	 * @param message the message
	 * @param t the t
	 */
	public ExternalAPIException(final String message, final Throwable t) {
		super(message, t);
	}

}

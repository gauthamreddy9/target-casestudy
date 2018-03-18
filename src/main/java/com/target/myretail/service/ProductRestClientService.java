package com.target.myretail.service;


/**
 * @author Gautham Reddy Created On : 03/17/2018
 */
public interface ProductRestClientService {
	
	/**
	 * @param id
	 * @return ProductName
	 * @throws Exception
	 */
	public String getProductNameFromExtAPI(String id) throws Exception;

}

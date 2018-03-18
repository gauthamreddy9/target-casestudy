package com.target.myretail.repo;

import com.target.myretail.dto.Product;

/**
 * @author Gautham Reddy Created On : 03/17/2018
 */
public interface ProductRepo {
	
	/**
	 * @param id
	 * @return Product
	 */
	public Product getProductById(String id);
	
	/**
	 * @param id
	 * @param price
	 * @param currencyCode
	 * @return Product
	 */
	public Product updatePriceById(String id, String price, String currencyCode);

}

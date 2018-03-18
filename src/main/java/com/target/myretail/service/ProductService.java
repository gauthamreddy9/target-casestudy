package com.target.myretail.service;

import com.target.myretail.dto.Product;

/**
 * @author Gautham Reddy Created On : 03/17/2018
 */
public interface ProductService {

	/**
	 * @param id
	 * @return Product
	 * @throws Exception
	 */
	public Product getProductDetails(String id) throws Exception;
	
	/**
	 * @param id
	 * @param Product
	 * @return Product
	 */
	public Product updateProduct(String id, Product product);
	
}

package com.target.myretail.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.target.myretail.dto.Price;
import com.target.myretail.dto.Product;
import com.target.myretail.exception.EmptyProductPriceException;
import com.target.myretail.repo.ProductRepo;
import com.target.myretail.service.ProductRestClientService;
import com.target.myretail.service.ProductService;

/**
 * @author Gautham Reddy Created On : 03/17/2018
 */
@Service
public class ProductServiceImpl implements ProductService {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ProductRestClientService productRestClient;

	@Autowired
	private ProductRepo productRepo;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.target.myretail.service.ProductService#getProductDetails(java.lang.
	 * String)
	 */
	@Override
	public Product getProductDetails(String id) throws Exception {

		// From application database
		Product product = getProductWithPrice(id);
		if (product != null) {
			// From External API
			String title = fetchProductName(id);
			product.setName(title);
		}
		return product;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.target.myretail.service.ProductService#updateProduct(java.lang.
	 * String, com.target.myretail.dto.Product)
	 */
	@Override
	public Product updateProduct(String id, Product product) {

		if (product.getCurrent_price() != null && product.getCurrent_price().getValue() != null) {
			Product updatedProduct = updatePrice(id, product.getCurrent_price().getValue(),
					product.getCurrent_price().getCurrency_code());
			return updatedProduct;
		}
		logger.info("Product price is empty or null");
		throw new EmptyProductPriceException("Product price should not be empty. Please specify product price.");
	}

	/**
	 * @param id
	 * @return String
	 * @throws Exception
	 */
	private String fetchProductName(String id) throws Exception {
		return productRestClient.getProductNameFromExtAPI(id);
	}

	/**
	 * @param id
	 * @return Product
	 */
	private Product getProductWithPrice(String id) {
		return productRepo.getProductById(id);
	}

	/**
	 * @param id
	 * @param price
	 * @param currencyCode
	 * @return ProductPrice
	 */
	private Product updatePrice(String id, String price, String currencyCode) {
		return productRepo.updatePriceById(id, price, currencyCode);
	}

}

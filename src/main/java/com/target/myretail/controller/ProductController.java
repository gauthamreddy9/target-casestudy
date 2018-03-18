package com.target.myretail.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.target.myretail.dto.Product;
import com.target.myretail.exception.ProductIdMisMatchException;
import com.target.myretail.exception.ProductNotFoundException;
import com.target.myretail.service.ProductService;

/**
 * @author Gautham Reddy Created On : 03/17/2018
 */
@RestController
@RequestMapping("/products")
public class ProductController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ProductService productService;

	/**
	 * @param productId
	 * @return Product
	 * @throws Exception 
	 * This API can be used to fetch Product by productId.
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Product> getProductDetails(
			@PathVariable(value = "id", required = true) String productId) throws Exception{
		logger.info("In getProductDetails method.");
		Product product;
		try {
			product = productService.getProductDetails(productId);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (product == null)
			throw new ProductNotFoundException("Requested product does not exists.");
		return new ResponseEntity<>(product, HttpStatus.OK);

	}

	/**
	 * @param id
	 * @return productInfo
	 * This API returns productInfo as a string. This API is a 
	 * substitute for http://redsky.target.com/v2/* an external API.
	 */
	@RequestMapping(value = "/v2/pdp/tcin/{id}", method = RequestMethod.GET)
	public ResponseEntity<String> getProductName(@PathVariable(value = "id", required = true) String id) {
		logger.info("In getProductName method.");
		String productInfo = "{\"product\": {\"item\": {\"product_description\": {\"title\": \"The Big Lebowski (Blu-ray)\"}}}}";
		return new ResponseEntity<>(productInfo, HttpStatus.OK);
	}

	/**
	 * @param id
	 * @param product
	 * @return Product
	 * This API updates product's price for a given productId and
	 * product object in requestBody.
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Product> updateProductPrice(
			@PathVariable(value = "id", required = true) String id, @RequestBody Product product) throws Exception{
		logger.info("In updateProductPrice method.");
		if (!id.equalsIgnoreCase(product.getId())) {
			throw new ProductIdMisMatchException("Product Id is not matching with request body product.");
		}
		Product newProduct;
		try {
			newProduct = productService.updateProduct(id, product);
		} catch (Exception e) {
			logger.error("Exception occurred while updating product price.", e);
			throw e;
		}
		if (newProduct == null)
			throw new ProductNotFoundException("Requested product does not exists.");
		return new ResponseEntity<>(newProduct, HttpStatus.OK);
	}

}

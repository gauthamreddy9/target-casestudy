package com.target.myretail.repo.impl;

import static org.springframework.data.mongodb.core.query.Criteria.where;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.target.myretail.dto.Product;
import com.target.myretail.repo.ProductRepo;

/**
 * @author Gautham Reddy Created On : 03/17/2018
 */
@Repository
public class ProductRepoImpl implements ProductRepo {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private MongoTemplate mongoTemplate;

	@Value("${product.collection.name}")
	private String collectionName;

	/* (non-Javadoc)
	 * @see com.target.myretail.repo.PricesRepo#getPriceById(java.lang.String)
	 */
	@Override
	public Product getProductById(String id) {
		Criteria criteria = new Criteria();
		criteria = where("product_id").is(id);
		Query query = new Query(criteria);
		Product product = mongoTemplate.findOne(query, Product.class, collectionName);
		return product;
	}

	/* (non-Javadoc)
	 * @see com.target.myretail.repo.PricesRepo#updatePriceById(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public Product updatePriceById(String id, String price, String currencyCode) {
		Criteria criteria = where("product_id").is(id);
		Query query = new Query(criteria);
		Update update = new Update();
		update.set("price.value", price);
		if (currencyCode != null && !currencyCode.isEmpty()) {
			update.set("price.currency_code", currencyCode);
		}
		logger.info("Updating price for product id = "+id);
		Product updatedProduct = mongoTemplate.findAndModify(query, update, new FindAndModifyOptions().returnNew(true),
				Product.class, collectionName);
		return updatedProduct;
	}

}

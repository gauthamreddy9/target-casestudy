package com.target.myretail.service.impl;

import java.io.IOException;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.target.myretail.exception.ExternalAPIException;
import com.target.myretail.service.ProductRestClientService;

/**
 * @author Gautham Reddy Created On : 03/17/2018
 */
@Component
public class ProductRestClientServiceImpl implements ProductRestClientService {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private RestTemplate restTemplate;

	@Value("${redsky.target.service}")
	private String baseurl;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.target.myretail.service.ProductRestClientService#
	 * getProductNameFromExtAPI(java.lang.String)
	 */
	@Override
	public String getProductNameFromExtAPI(String id) throws Exception {
		String url = buildExtUrl(id);
		ResponseEntity<String> jsonObj = null;
		try {
			logger.info("Fetching product name from external API.");
			jsonObj = restTemplate.getForEntity(url, String.class);
		} catch (RestClientException rce) {
			logger.error("Failed to fetch data from external API.", rce);
			throw new ExternalAPIException("Failed to fetch data from external API.", rce);
		}
		String name = null;
		if (jsonObj != null)
			name = extractName(jsonObj.getBody());
		return name;
	}

	/**
	 * @param id
	 * @return external API url
	 */
	private String buildExtUrl(String id) {
		return baseurl + "/" + id;
	}

	/**
	 * @param jsonObj
	 * @return productName
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 * This method extracts product title from the productInfo json object.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private String extractName(String jsonObj) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Map> objMap = mapper.readValue(jsonObj, Map.class);
		Map<String, Map> productMap = objMap.get("product");
		Map<String, Map> itemMap = productMap.get("item");
		Map<String, String> prodDescrMap = itemMap.get(("product_description"));
		return prodDescrMap.get("title");
	}
}

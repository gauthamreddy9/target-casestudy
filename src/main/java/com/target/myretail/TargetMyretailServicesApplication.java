package com.target.myretail;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.core.convert.DbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultDbRefResolver;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.web.client.RestTemplate;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

/**
 * @author Gautham Reddy Created On : 03/17/2018
 */
@SpringBootApplication
@ComponentScan("com.target")
public class TargetMyretailServicesApplication {

	@Value("${spring.data.mongodb.database}")
	private String myretailDb;
	
	@Value("${spring.data.mongodb.dbConnectionUrl}")
	private String mongoUrl;
	
	public static void main(String[] args) {
		SpringApplication.run(TargetMyretailServicesApplication.class, args);
	}
	
	public void authenticationManager(AuthenticationManagerBuilder builder){
	}
	
	@Bean(name = "restTemplate")
	public RestTemplate getRestTemplate(){
		HttpComponentsClientHttpRequestFactory httpRequestFactory = new HttpComponentsClientHttpRequestFactory();
        httpRequestFactory.setConnectionRequestTimeout(60 * 8000); // in milliseconds
        httpRequestFactory.setConnectTimeout(60 * 8000); // in milliseconds
        httpRequestFactory.setReadTimeout(60 * 8000); // in milliseconds
		return new RestTemplate(httpRequestFactory);
	}
	
	@Bean(name = "mongoTemplate")
	public MongoTemplate pntMongoTemplate() throws Exception {
	    MongoClientURI uri = new MongoClientURI(mongoUrl);
		MongoClient client = new MongoClient(uri);
		MongoDbFactory mongodbFactory = new SimpleMongoDbFactory(client, myretailDb);
		MongoMappingContext context = new MongoMappingContext();
		DbRefResolver dbRefResolver = new DefaultDbRefResolver(mongodbFactory);
		MappingMongoConverter converter = new MappingMongoConverter(dbRefResolver, context);
		return new MongoTemplate(mongodbFactory, converter);
	}
	
}

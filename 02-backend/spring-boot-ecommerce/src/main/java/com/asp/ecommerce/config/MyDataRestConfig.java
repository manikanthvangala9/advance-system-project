package com.asp.ecommerce.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.metamodel.EntityType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import com.asp.ecommerce.entity.Country;
import com.asp.ecommerce.entity.Order;
import com.asp.ecommerce.entity.Product;
import com.asp.ecommerce.entity.ProductCategory;
import com.asp.ecommerce.entity.State;





@Configuration
public class MyDataRestConfig implements RepositoryRestConfigurer {
	
	@Value("${allowed.origins}")
	private String[] theAllowedOrigins;
	
	
	private EntityManager entityManager;
	
	@Autowired
	public MyDataRestConfig(EntityManager theEntityManager) {
		entityManager=theEntityManager;
	}
	

	@Override
	public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
		// TODO Auto-generated method stub
		RepositoryRestConfigurer.super.configureRepositoryRestConfiguration(config, cors);
		
		HttpMethod[] unSupportedActions= {HttpMethod.PUT, HttpMethod.POST, HttpMethod.DELETE };
		
		//Disable the above mentioned actions for Entities
		
		disableHttpMethod(config, unSupportedActions, ProductCategory.class);
		disableHttpMethod(config, unSupportedActions, Product.class);
		disableHttpMethod(config, unSupportedActions, State.class);
		disableHttpMethod(config, unSupportedActions, Country.class);
		disableHttpMethod(config,unSupportedActions,Order.class);
		
		
		//Internal method tp expose the Ids
		exposeIds(config);
		
		//Configure cors Mapping
		
		cors.addMapping(config.getBasePath()+"/**").allowedOrigins(theAllowedOrigins);
		
	}


	private void disableHttpMethod(RepositoryRestConfiguration config, HttpMethod[] unSupportedActions, Class theClass) {
		config.getExposureConfiguration()
		.forDomainType(theClass)
		.withItemExposure((metadeta,httpMethods)->httpMethods.disable(unSupportedActions))
		.withCollectionExposure((metadata,httpMethods)->httpMethods.disable(unSupportedActions));
	}


	private void exposeIds(RepositoryRestConfiguration config) {
		//expose entity Ids
		//get all the entities form all the entity managers
		
		Set<EntityType<?>> entities = entityManager.getMetamodel().getEntities();
		
		//Array of all entity types
		List<Class> entityClasses=new ArrayList<>();
		
		//get entity types of entities
		for(EntityType tempEntityType: entities) {
			entityClasses.add(tempEntityType.getJavaType());
		}
		
		//expose ids for array of entity types
		
		Class [] domainTypes=entityClasses.toArray(new Class[0]);
		config.exposeIdsFor(domainTypes);
		
	}
	
	
	

	
}

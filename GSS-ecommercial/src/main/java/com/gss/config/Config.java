package com.gss.config;

import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.core.io.Resource;
//import com.google.common.collect.Maps;
import com.mongodb.client.MongoDatabase;

import utils.MongoUtils;

@Configuration
@EnableScheduling
public class Config {
//	@Bean
//	CustomAttributeProperties loadCustomAttributeProperties() throws IOException {
//		Resource schemaExcelBindingPropertiesResource = new ClassPathResource(
//				"config/mapping_attribute.properties");
//		Properties excelBindingProperties = PropertiesLoaderUtils
//				.loadProperties(schemaExcelBindingPropertiesResource);
//		HashMap<String, Object> excelBindingPropertiesMap = Maps
//				.newHashMap(Maps.fromProperties(excelBindingProperties));
//
//		CustomAttributeProperties customAttributeProperties = new CustomAttributeProperties();
//		customAttributeProperties.setExcelMappingAttributes(excelBindingPropertiesMap);
//		return customAttributeProperties;
//	}
	
	@Bean
    MongoDatabase mongoDatabase(MongoTemplate mongoTemplate) {
        return mongoTemplate.getDb();
    }
	
	@Bean
    public MongoUtils mongoUtils() {
        return new MongoUtils();
    }
}

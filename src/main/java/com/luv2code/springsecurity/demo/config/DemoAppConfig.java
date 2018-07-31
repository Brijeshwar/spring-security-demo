package com.luv2code.springsecurity.demo.config;

import java.util.logging.Logger;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
@EnableWebMvc
@ComponentScan("com.luv2code.springsecurity.demo")
@PropertySource("classpath:persistence-mysql.properties")
public class DemoAppConfig {
	
	private Logger logger = Logger.getLogger(getClass().getName());
	
	//will hold data read from properties files
	@Autowired
	private Environment env;
	
	//Define datasource bean object
	@Bean
	public DataSource securityDataSource() {
		//create connection pool
		ComboPooledDataSource securityDataSource = new ComboPooledDataSource();
		
		//set the jdbc driver
		try {
			securityDataSource.setDriverClass(env.getProperty("jdbc.driver"));
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
		
		//set database connection properties
		logger.info(">>>> jdbc.url=" + env.getProperty("jdbc.url"));
		logger.info(">>>> jdbc.user=" + env.getProperty("jdbc.user"));
		
		securityDataSource.setJdbcUrl(env.getProperty("jdbc.url"));
		securityDataSource.setUser(env.getProperty("jdbc.user"));
		securityDataSource.setPassword(env.getProperty("jdbc.password"));
		
		//set the connection pool properties
		securityDataSource.setInitialPoolSize(getIntProperty("connection.pool.initialPoolSize"));
		securityDataSource.setMinPoolSize(getIntProperty("connection.pool.minPoolSize"));
		securityDataSource.setMaxPoolSize(getIntProperty("connection.pool.maxPoolSize"));
		securityDataSource.setMaxIdleTime(getIntProperty("connection.pool.maxIdleTime"));
		
		return securityDataSource;
	}
	
	//Helper method: read environment property and convert it to int
	private int getIntProperty(String property) {
		String propVal = env.getProperty(property);
		
		//now convert it to int
		int intPropValue = Integer.parseInt(propVal);
		
		return intPropValue;
	}
	

	//Define a bean for ViewResolver
	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/view/");
		viewResolver.setSuffix(".jsp");
		
		return viewResolver;
	}
}

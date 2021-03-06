package com.feiyu4fun.cameras;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.TimeZone;

@SpringBootApplication
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
@EnableScheduling
@EnableAspectJAutoProxy
@Transactional
public class CamerasApplication implements ServletContextListener {

	@Autowired
	private SessionFactory managementSessionFactory;
	
	/**
	 * Keep timezone consistency between java app and database
	 */
	@PostConstruct
    void started() {
      TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
    }
    
	public static void main(String[] args) {
		SpringApplication.run(CamerasApplication.class, args);
	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		// no need to do any thing
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		managementSessionFactory.getCurrentSession().close();
		managementSessionFactory.close();
	}

	// only work for not using spring security
//	@Bean
//	public WebMvcConfigurer corsConfigurer() {
//        return new WebMvcConfigurer() {
//            @Override
//            public void addCorsMappings(CorsRegistry registry) {
//                registry.addMapping("/api/**")
//                		.allowedOrigins(
//	                		"http://localhost",
//	                		"http://localhost:8080",
//	                		"http://localhost:8081",
//	                		"http://feiyu4fun.com");
//            }
//        };
//    }
}

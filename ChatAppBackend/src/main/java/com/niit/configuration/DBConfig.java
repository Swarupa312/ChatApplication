package com.niit.configuration;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
/*import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;*/
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.niit.Model.BlogComment;
import com.niit.Model.BlogPost;
import com.niit.Model.Job;
import com.niit.Model.ProfilePicture;
import com.niit.Model.User;

@Configuration
@EnableTransactionManagement
public class DBConfig
{
	@Bean
	public SessionFactory sessionFactory()
	{
		LocalSessionFactoryBuilder localsessionfactory=new LocalSessionFactoryBuilder(getdataSource());
		Properties properties=new Properties();
		properties.setProperty("hibernate.dialect","org.hibernate.dialect.Oracle10gDialect");
		properties.setProperty("hibernate.hbm2ddl.auto","update");
		properties.setProperty("hibernate.showsql","true");	
		localsessionfactory.addProperties(properties);
		Class classes[]=new Class[]{
				User.class,Job.class,BlogPost.class,BlogComment.class,ProfilePicture.class
		};
		return localsessionfactory.addAnnotatedClasses(classes).buildSessionFactory();
	}

	@Bean
	public DataSource getdataSource()
	{
		BasicDataSource datasource=new BasicDataSource();
		datasource.setDriverClassName("oracle.jdbc.OracleDriver");
		datasource.setUrl("jdbc:oracle:thin:@localhost:1521:XE");
		datasource.setUsername("SWARUPA");
		datasource.setPassword("swarupa");
		System.out.println("Database connection");
		return datasource;	
	}
	
	@Bean
	public HibernateTransactionManager hibernatemanagement()
	{ 
		return new HibernateTransactionManager(sessionFactory());
	}

}

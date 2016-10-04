package com.pradeep.menu.util.hibernate;

import java.util.Properties;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Component;

import com.pradeep.menu.bean.entity.User;
import com.pradeep.menu.bean.entity.UserType;

@Component
public class HibernateUtil {
	private static final SessionFactory concreteSessionFactory;

	static {
		try {
			Properties prop = new Properties();
			// <property
			// name="">org.hibernate.dialect.MySQLInnoDBDialect</property>

			prop.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/emenu");
			prop.setProperty("hibernate.connection.username", "root");
			prop.setProperty("hibernate.connection.password", "root");
			prop.setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
			prop.setProperty("hibernate.show_sql", "true");
			prop.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLInnoDBDialect");
			prop.setProperty("hibernate.id.new_generator_mappings", "false");
			prop.setProperty("hibernate.connection.zeroDateTimeBehavior", "convertToNull");
			
			concreteSessionFactory = new Configuration().addPackage("com.pradeep.menu.bean.orm").addProperties(prop)
					.addAnnotatedClass(UserType.class).addAnnotatedClass(User.class).buildSessionFactory();
		
		} catch (Throwable ex) {
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static Session getSession() throws HibernateException {
		return concreteSessionFactory.openSession();
	}

}

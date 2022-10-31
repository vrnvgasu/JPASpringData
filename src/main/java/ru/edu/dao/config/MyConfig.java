package ru.edu.dao.config;

import java.util.Properties;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

@Configuration
@EnableJpaRepositories("ru.edu.dao.repo") // поддержка JPA репозиториев
public class MyConfig {

	//Настройки для подключения к БД
	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setUrl("jdbc:postgresql://localhost:5492/jpa_sber");
		dataSource.setDriverClassName("org.postgresql.Driver");
		dataSource.setUsername("user");
		dataSource.setPassword("user");

		return dataSource;
	}

	//бин для EntityManagerFactory
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean managerFactory = new LocalContainerEntityManagerFactoryBean();

		//1. указываем зависимость на DataSource
		managerFactory.setDataSource(dataSource());

		//2. выбираем поставщика БД (Hibernate)
		JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		managerFactory.setJpaVendorAdapter(vendorAdapter);

		// добавили параметры для поставщика (Hibernate)
		Properties properties = new Properties();
		properties.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
		properties.setProperty("hibernate.show_sql", "true");
		managerFactory.setJpaProperties(properties);

		//3.указываем, в каком пакете лежат сущности Entity
		managerFactory.setPackagesToScan("ru.edu.dao.entity");

		return managerFactory;
	}

	//Менеджер для управления транзакциями
	@Bean
	public JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
		return new JpaTransactionManager(entityManagerFactory);
	}

}

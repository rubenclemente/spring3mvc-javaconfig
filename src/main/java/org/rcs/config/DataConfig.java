package org.rcs.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories("org.rcs.repository")
public class DataConfig extends WebMvcConfigurerAdapter {

	@Autowired
	private Environment env;

	@Bean
	public DataSource dataSource() {
		try {
			/*
			ComboPooledDataSource ds = new ComboPooledDataSource();
			ds.setDriverClass(this.env.getRequiredProperty("app.jdbc.driverClassName"));
			ds.setJdbcUrl(this.env.getRequiredProperty("app.jdbc.url"));
			ds.setUser(this.env.getRequiredProperty("app.jdbc.username"));
			ds.setPassword(this.env.getRequiredProperty("app.jdbc.password"));
			ds.setAcquireIncrement(5);
			ds.setIdleConnectionTestPeriod(60);
			ds.setMaxPoolSize(100);
			ds.setMaxStatements(50);
			ds.setMinPoolSize(10);
			 */

			DriverManagerDataSource ds = new DriverManagerDataSource();
			ds.setDriverClassName(this.env.getRequiredProperty("app.jdbc.driverClassName"));
			ds.setUrl(this.env.getRequiredProperty("app.jdbc.url"));
			ds.setUsername(this.env.getRequiredProperty("app.jdbc.username"));
			ds.setPassword(this.env.getRequiredProperty("app.jdbc.password"));

			return ds;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		HibernateJpaVendorAdapter vendor = new HibernateJpaVendorAdapter();
		vendor.setShowSql(false);

		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setPersistenceXmlLocation("classpath*:META-INF/persistence.xml");
		em.setPersistenceUnitName("hibernatePersistenceUnit");
		em.setDataSource(this.dataSource());
		em.setJpaVendorAdapter(vendor);

		return em;
	}

	@Bean
	public JpaTransactionManager transactionManager() {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(this.entityManagerFactory().getObject());

		return transactionManager;
	}
}

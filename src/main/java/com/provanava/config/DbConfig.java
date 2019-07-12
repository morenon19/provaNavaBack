package com.provanava.config;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

@Configuration
public class DbConfig {

	/** Valores responsáveis pela configuração do banco de dados */
	public static String username;
	private static String password;
	private static String url;
	
	
	/** Funções para atribuir o valor das chaves de application.properties às variáveis estáticas */
	@Value("${db.username}")
	public void setUsername(String user) {
		username = user;
	}
	@Value("${db.password}")
	public void setPassword(String pass) {
		password = pass;
	}
	@Value("${db.url}")
	public void setUrl(String ur) {
		url = ur;
	}
	
	/** Vinculação do JDBCTemplate ao dataSource configurado  */
	@Bean
	public JdbcTemplate jdbcTemplate(SimpleDriverDataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}
	
	
	/** Configuração do dataSource com as chaves de application.properties */
	@Bean
	public static SimpleDriverDataSource getDatabaseConnection() {
		
		SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
		try {
			dataSource.setDriver(new com.mysql.cj.jdbc.Driver());
			dataSource.setUrl(url);
			dataSource.setUsername(username);
			dataSource.setPassword(password);
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return dataSource;
	}

}

package com.web.heritage;

import java.sql.Connection;
import java.sql.DriverManager;

import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HeritageApplication {

	public static void main(String[] args) {
		SpringApplication.run(HeritageApplication.class, args);
	}
	
	@Test
	public void testConnection() throws Exception{
		Class.forName(DRIVER);
		try(Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
			System.out.println(connection);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	

}

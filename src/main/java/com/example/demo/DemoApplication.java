package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}



//	String url = "jdbc:sqlserver://DESKTOP-ECJR0OG\\SQLEXPRESS01;databaseName=students;trustServerCertificate=true;integratedSecurity=true;encrypt=true";
//	String user = "sa" ;
//	String password = "Azerty";
//		try {
//				Connection connection = DriverManager.getConnection(url,user,password);
//				System.out.println("Connect to Microsft SQL  Server :)");
//				} catch (SQLException e) {
//				System.out.println("ops, there's an error");
//				e.printStackTrace();
//				}
//				}
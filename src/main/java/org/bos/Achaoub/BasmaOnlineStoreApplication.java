package org.bos.Achaoub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
// le cas de pas utiliser une base de donnes il est recommandé de ignore la dataSource du springBoot
//@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
public class BasmaOnlineStoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(BasmaOnlineStoreApplication.class, args);
	}
	
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public SpringApplicationContext springApplicationContext() {
		return new SpringApplicationContext();
	}

}

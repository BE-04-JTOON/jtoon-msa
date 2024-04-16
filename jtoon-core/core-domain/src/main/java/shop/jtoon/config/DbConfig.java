package shop.jtoon.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
public class DbConfig {

	@Bean
	@ConfigurationProperties(prefix = "jtoon-db.datasource.main")
	public HikariConfig mainHikariConfig() {
		return new HikariConfig();
	}

	@Bean
	public HikariDataSource hikariDataSource(@Qualifier("mainHikariConfig") HikariConfig mainHikariConfig) {
		return new HikariDataSource(mainHikariConfig);
	}
}

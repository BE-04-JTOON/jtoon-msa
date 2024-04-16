package shop.jtoon.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;

@Getter
@Configuration
public class MailProperties {

	@Value("${mail.host}")
	private String host;

	@Value("${mail.port}")
	private Integer port;

	@Value("${mail.username}")
	private String username;

	@Value("${mail.password}")
	private String password;

	@Value("${mail.properties.mail.smtp.auth}")
	private String auth;

	@Value("${mail.properties.mail.smtp.starttls.enable}")
	private String starttls;

	@Value("${mail.properties.mail.smtp.ssl.trust}")
	private String sslTrust;
}
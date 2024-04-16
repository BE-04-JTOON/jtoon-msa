package shop.jtoon.login.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CrytoService {

	private final PasswordEncoder passwordEncoder;

	public String encoded(String originPassword) {
		return passwordEncoder.encode(originPassword);
	}

	public boolean passwordMatch(String requestPassword, String encodedPassword) {
		return passwordEncoder.matches(requestPassword, encodedPassword);
	}
}

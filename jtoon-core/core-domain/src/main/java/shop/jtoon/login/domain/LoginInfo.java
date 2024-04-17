package shop.jtoon.login.domain;

import lombok.Builder;
import shop.jtoon.member.entity.LoginType;

@Builder
public record LoginInfo(
	String email,
	String password,
	LoginType loginType
) {

	public LoginInfo encode(String encodedPassword) {
		return LoginInfo.builder()
			.email(email)
			.password(encodedPassword)
			.loginType(loginType)
			.build();
	}
}

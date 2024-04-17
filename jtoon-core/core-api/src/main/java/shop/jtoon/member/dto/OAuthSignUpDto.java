package shop.jtoon.member.dto;

import lombok.Builder;

import shop.jtoon.login.domain.LoginInfo;
import shop.jtoon.login.domain.UserInfo;
import shop.jtoon.member.entity.Gender;
import shop.jtoon.member.entity.LoginType;

@Builder
public record OAuthSignUpDto(
	String email,
	String password,
	String name,
	String nickname,
	String gender,
	String phone,
	String loginType
) {
	public LoginInfo toLoginInfo() {
		return LoginInfo.builder()
			.email(email)
			.password(password)
			.loginType(LoginType.from(loginType))
			.build();
	}

	public UserInfo toUserInfo() {
		return UserInfo.builder()
			.name(name)
			.nickname(nickname)
			.gender(Gender.from(gender))
			.phone(phone)
			.build();
	}
}

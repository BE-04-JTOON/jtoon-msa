package shop.jtoon.member.dto;

import lombok.Builder;

import shop.jtoon.member.entity.Gender;
import shop.jtoon.member.entity.LoginType;
import shop.jtoon.member.entity.Member;
import shop.jtoon.member.entity.Role;

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
	public Member toEntity(String encryptedPassword) {
		return Member.builder()
			.email(email)
			.password(encryptedPassword)
			.name(name)
			.nickname(nickname)
			.gender(Gender.from(gender))
			.phone(phone)
			.role(Role.USER)
			.loginType(LoginType.from(loginType))
			.build();
	}
}

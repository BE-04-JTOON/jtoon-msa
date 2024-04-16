package shop.jtoon.member.request;

import static shop.jtoon.util.RegExp.*;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import shop.jtoon.login.domain.LoginInfo;
import shop.jtoon.login.domain.UserInfo;
import shop.jtoon.member.entity.Gender;
import shop.jtoon.member.entity.LoginType;

public record LocalSignUpReq(
	@Pattern(regexp = EMAIL_PATTERN) String email,
	@Pattern(regexp = PASSWORD_PATTERN) String password,
	@NotBlank @Size(max = 10) String name,
	@NotBlank @Size(max = 30) String nickname,
	@NotNull String gender,
	@Pattern(regexp = PHONE_PATTERN) String phone,
	@NotNull String loginType
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

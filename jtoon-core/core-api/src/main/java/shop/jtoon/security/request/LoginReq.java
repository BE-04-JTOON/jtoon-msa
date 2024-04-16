package shop.jtoon.security.request;

import static shop.jtoon.util.RegExp.*;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import shop.jtoon.login.domain.LoginInfo;
import shop.jtoon.member.entity.LoginType;

public record LoginReq(
	@Pattern(regexp = EMAIL_PATTERN) String email,
	@Pattern(regexp = PASSWORD_PATTERN) @NotBlank String password
) {

	public LoginInfo toLoginInfo() {
		return LoginInfo.builder()
			.email(email)
			.password(password)
			.loginType(LoginType.KAKAO)
			.build();
	}
}

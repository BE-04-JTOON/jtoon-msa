package shop.jtoon.login.service;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import shop.jtoon.login.domain.LoginInfo;
import shop.jtoon.login.domain.UserInfo;
import shop.jtoon.member.entity.Member;
import shop.jtoon.member.entity.Role;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LoginMapper {

	public static Member toMember(LoginInfo loginInfo, UserInfo userInfo) {
		return Member.builder()
			.email(loginInfo.email())
			.password(loginInfo.password())
			.loginType(loginInfo.loginType())
			.name(userInfo.name())
			.nickname(userInfo.nickname())
			.gender(userInfo.gender())
			.phone(userInfo.phone())
			.role(Role.USER)
			.build();
	}
}

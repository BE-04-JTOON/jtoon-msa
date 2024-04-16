package shop.jtoon.login.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import shop.jtoon.exception.DuplicatedException;
import shop.jtoon.login.domain.LoginInfo;
import shop.jtoon.login.domain.UserInfo;
import shop.jtoon.member.entity.Member;
import shop.jtoon.member.repository.MemberReader;
import shop.jtoon.type.ErrorStatus;

@Service
@RequiredArgsConstructor
public class LoginManager {

	private final CrytoService crytoService;
	private final MemberReader memberReader;

	public Member signUp(LoginInfo loginInfo, UserInfo userInfo) {
		LoginInfo encodedLoginInfo = loginInfo.encode(crytoService.encoded(loginInfo.password()));
		validateDuplicateEmail(encodedLoginInfo.email());

		return LoginMapper.toMember(loginInfo, userInfo);
	}

	private void validateDuplicateEmail(String email) {
		if (memberReader.readMemberExist(email)) {
			throw new DuplicatedException(ErrorStatus.MEMBER_EMAIL_CONFLICT);
		}
	}
}

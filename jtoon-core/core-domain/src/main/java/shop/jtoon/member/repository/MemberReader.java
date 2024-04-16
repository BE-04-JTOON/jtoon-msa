package shop.jtoon.member.repository;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import shop.jtoon.exception.InvalidRequestException;
import shop.jtoon.exception.NotFoundException;
import shop.jtoon.login.service.CrytoService;
import shop.jtoon.login.domain.LoginInfo;
import shop.jtoon.member.entity.LoginType;
import shop.jtoon.member.entity.Member;
import shop.jtoon.type.ErrorStatus;

@Repository
@RequiredArgsConstructor
public class MemberReader {

	private final CrytoService crytoService;
	private final MemberRepository memberRepository;

	public Member read(Long id) {
		return memberRepository.findById(id)
			.orElseThrow(() -> new NotFoundException(ErrorStatus.MEMBER_NOT_FOUND));
	}

	public Optional<Member> readOptionalByEmail(String email) {
		return memberRepository.findByEmail(email);
	}

	public Member readByEmail(String email) {
		return memberRepository.findByEmail(email)
			.orElseThrow(() -> new NotFoundException(ErrorStatus.MEMBER_EMAIL_NOT_FOUND));
	}

	public boolean readMemberExist(String email) {
		return memberRepository.findByEmail(email).isPresent();
	}

	public Member readLoginRequestMember(LoginInfo loginInfo) {
		Member member = readByEmail(loginInfo.email());
		validateLoginRequest(loginInfo, member);

		return member;
	}

	private void validateLoginRequest(LoginInfo loginInfo, Member member) {
		if (!crytoService.passwordMatch(loginInfo.password(), member.getPassword())) {
			throw new InvalidRequestException(ErrorStatus.MEMBER_WRONG_LOGIN_INFO);
		}

		if (!member.getLoginType().equals(LoginType.LOCAL)) {
			throw new InvalidRequestException(ErrorStatus.MEMBER_DUPLICATE_SOCIAL_LOGIN);
		}
	}
}

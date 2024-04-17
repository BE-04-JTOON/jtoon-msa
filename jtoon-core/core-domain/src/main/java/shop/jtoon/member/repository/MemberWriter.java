package shop.jtoon.member.repository;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import shop.jtoon.login.service.LoginMapper;
import shop.jtoon.login.domain.LoginInfo;
import shop.jtoon.login.domain.UserInfo;
import shop.jtoon.member.entity.Member;

@Repository
@RequiredArgsConstructor
public class MemberWriter {

	private final MemberRepository memberRepository;

	public void write(Member member) {
		memberRepository.save(member);
	}

	public Member signUp(LoginInfo loginInfo, UserInfo userInfo) {
		Member member = LoginMapper.toMember(loginInfo, userInfo);

		return memberRepository.save(member);
	}
}

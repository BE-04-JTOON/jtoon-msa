package shop.jtoon.payment.repository;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import shop.jtoon.exception.NotFoundException;
import shop.jtoon.member.entity.Member;
import shop.jtoon.payment.entity.MemberCookie;
import shop.jtoon.type.ErrorStatus;

@Repository
@RequiredArgsConstructor
public class CookieReader {

	private final MemberCookieRepository memberCookieRepository;

	public MemberCookie read(Member member) {
		return memberCookieRepository.findByMember(member)
			.orElseThrow(() -> new NotFoundException(ErrorStatus.MEMBER_COOKIE_NOT_FOUND));
	}
}

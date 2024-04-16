package shop.jtoon.payment.repository;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import shop.jtoon.member.entity.Member;
import shop.jtoon.payment.entity.MemberCookie;

@Repository
@RequiredArgsConstructor
public class CookieWriter {

	private final MemberCookieRepository memberCookieRepository;

	public void write(MemberCookie memberCookie) {
		memberCookieRepository.save(memberCookie);
	}
}

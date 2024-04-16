package shop.jtoon.payment.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import shop.jtoon.member.entity.Member;
import shop.jtoon.payment.domain.Item;
import shop.jtoon.payment.entity.CookieItem;
import shop.jtoon.payment.entity.MemberCookie;
import shop.jtoon.payment.repository.CookieReader;
import shop.jtoon.payment.repository.CookieWriter;
import shop.jtoon.webtoon.entity.PurchasedEpisode;

@Service
@RequiredArgsConstructor
public class CookieManager {

	private final CookieWriter cookieWriter;
	private final CookieReader cookieReader;

	public void bakeCookie(Item item, Member member) {
		CookieItem cookie = CookieItem.from(item.itemName());
		MemberCookie memberCookie = MemberCookie.create(cookie.getCount(), member);
		cookieWriter.write(memberCookie);
	}

	public int useCookie(int cookieCount, Member member) {
		MemberCookie memberCookie = cookieReader.read(member);
		memberCookie.decreaseCookieCount(cookieCount);

		return memberCookie.getCookieCount();
	}

}

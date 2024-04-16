package shop.jtoon.payment.application;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import shop.jtoon.exception.NotFoundException;
import shop.jtoon.member.entity.Member;
import shop.jtoon.payment.entity.CookieItem;
import shop.jtoon.payment.entity.MemberCookie;
import shop.jtoon.payment.repository.MemberCookieRepository;
import shop.jtoon.type.ErrorStatus;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberCookieService {

    private final MemberCookieRepository memberCookieRepository;

    @Transactional
    public void createMemberCookie(String cookieItem, Member member) {
        CookieItem item = CookieItem.from(cookieItem);
        MemberCookie memberCookie = MemberCookie.create(item.getCount(), member);
        memberCookieRepository.save(memberCookie);
    }

    @Transactional
    public int useCookie(int cookieCount, Member member) {
        MemberCookie memberCookie = getByMember(member);
        memberCookie.decreaseCookieCount(cookieCount);

        return memberCookie.getCookieCount();
    }

    public int getMemberCookieCount(Member member) {
        MemberCookie memberCookie = getByMember(member);

        return memberCookie.getCookieCount();
    }

    private MemberCookie getByMember(Member member) {
        return memberCookieRepository.findByMember(member)
                .orElseThrow(() -> new NotFoundException(ErrorStatus.MEMBER_COOKIE_NOT_FOUND));
    }
}

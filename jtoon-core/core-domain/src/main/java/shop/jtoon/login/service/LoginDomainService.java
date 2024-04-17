package shop.jtoon.login.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

import shop.jtoon.login.domain.LoginInfo;
import shop.jtoon.member.domain.MyInfo;
import shop.jtoon.login.domain.UserInfo;
import shop.jtoon.member.entity.Member;
import shop.jtoon.member.repository.MemberReader;
import shop.jtoon.member.repository.MemberWriter;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class LoginDomainService {

    private final LoginManager loginManager;
    private final MemberReader memberReader;
    private final MemberWriter memberWriter;

    @Transactional
    public void signUp(LoginInfo loginInfo, UserInfo userInfo) {
        Member member = loginManager.signUp(loginInfo, userInfo);
        memberWriter.write(member);
    }

    @Transactional
    public void login(LoginInfo loginInfo) {
        Member member = memberReader.readLoginRequestMember(loginInfo);
        member.updateLastLogin();
    }

    @Transactional
    public Member generateOrGetSocialMember(LoginInfo loginInfo, UserInfo userInfo) {
        Optional<Member> member = memberReader.readOptionalByEmail(loginInfo.email());

        return member.orElseGet(() -> memberWriter.signUp(loginInfo, userInfo));
    }

    public MyInfo findMemberDtoByEmail(String email) {
        Member member = memberReader.readByEmail(email);

        return MyInfo.of(member);
    }
}

package shop.jtoon.member.application;

import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import shop.jtoon.login.service.LoginDomainService;
import shop.jtoon.member.domain.MyInfo;
import shop.jtoon.member.dto.OAuthSignUpDto;
import shop.jtoon.member.entity.Member;
import shop.jtoon.member.request.LocalSignUpReq;
import shop.jtoon.security.request.LoginReq;
import shop.jtoon.security.service.JwtService;
import shop.jtoon.security.service.RefreshTokenService;
import shop.jtoon.security.util.TokenCookie;
import shop.jtoon.util.SecurityConstant;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final JwtService jwtProvider;
    private final RefreshTokenService refreshTokenServiceImpl;

    private final LoginDomainService loginService;

    public void signUp(LocalSignUpReq localSignUpReq) {
        loginService.signUp(localSignUpReq.toLoginInfo(), localSignUpReq.toUserInfo());
    }

    public void loginMember(LoginReq loginReq, HttpServletResponse response) {
        loginService.login(loginReq.toLoginInfo());

        String accessToken = jwtProvider.generateAccessToken(loginReq.email());
        String refreshToken = jwtProvider.generateRefreshToken();
        refreshTokenServiceImpl.saveRefreshToken(refreshToken, loginReq.email());

        response.addCookie(TokenCookie.of(SecurityConstant.ACCESS_TOKEN_HEADER, accessToken));
        response.addCookie(TokenCookie.of(SecurityConstant.REFRESH_TOKEN_HEADER, refreshToken));
    }

    public Member generateOrGetSocialMember(OAuthSignUpDto oAuthSignUpDto) {
        return loginService.generateOrGetSocialMember(oAuthSignUpDto.toLoginInfo(), oAuthSignUpDto.toUserInfo());
    }

    public MyInfo readMyInfo(String email) {
        return loginService.findMemberDtoByEmail(email);
    }
}

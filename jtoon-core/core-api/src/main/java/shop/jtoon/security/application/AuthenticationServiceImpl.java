package shop.jtoon.security.application;

import static shop.jtoon.util.SecurityConstant.*;

import java.util.List;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import shop.jtoon.member.application.LoginService;
import shop.jtoon.member.domain.MyInfo;
import shop.jtoon.member.dto.MemberDto;
import shop.jtoon.security.service.AuthenticationService;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

	private final LoginService memberService;

	@Override
	public Authentication getAuthentication(String claimsEmail) {
		MyInfo myInfo = memberService.readMyInfo(claimsEmail);
		MemberDto memberDto = MemberDto.toDto(myInfo);

		return new UsernamePasswordAuthenticationToken(memberDto, BLANK,
			List.of(new SimpleGrantedAuthority(memberDto.role().toString())));
	}
}

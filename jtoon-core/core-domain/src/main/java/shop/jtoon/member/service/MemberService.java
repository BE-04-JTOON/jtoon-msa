package shop.jtoon.member.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import shop.jtoon.member.domain.MyInfo;
import shop.jtoon.member.entity.Member;
import shop.jtoon.member.repository.MemberReader;

@Service
@RequiredArgsConstructor
public class MemberService {

	private final MemberReader memberReader;

	public Member read(String email) {
		return memberReader.readByEmail(email);
	}
}

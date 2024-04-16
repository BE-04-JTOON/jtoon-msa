package shop.jtoon.member.domain;

import lombok.Builder;
import shop.jtoon.member.entity.Gender;
import shop.jtoon.member.entity.Member;
import shop.jtoon.member.entity.Role;

@Builder
public record MyInfo(
	Long id,
	String email,
	String name,
	String nickname,
	Gender gender,
	Role role,
	String phone
) {

	public static MyInfo of(Member member) {
		return MyInfo.builder()
			.id(member.getId())
			.email(member.getEmail())
			.name(member.getName())
			.nickname(member.getNickname())
			.gender(member.getGender())
			.role(member.getRole())
			.phone(member.getPhone())
			.build();
	}
}

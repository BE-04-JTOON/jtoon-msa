package shop.jtoon.member.dto;

import lombok.Builder;
import shop.jtoon.member.domain.MyInfo;
import shop.jtoon.member.entity.Gender;
import shop.jtoon.member.entity.Role;

@Builder
public record MemberDto(
	Long id,
	String email,
	String name,
	String nickname,
	Gender gender,
	Role role,
	String phone
) {
	public static MemberDto toDto(MyInfo myInfo) {
		return MemberDto.builder()
			.id(myInfo.id())
			.email(myInfo.email())
			.name(myInfo.name())
			.nickname(myInfo.nickname())
			.gender(myInfo.gender())
			.role(myInfo.role())
			.phone(myInfo.phone())
			.build();
	}
}

package shop.jtoon.login.domain;

import lombok.Builder;
import shop.jtoon.member.entity.Gender;

@Builder
public record UserInfo(
	String name,
	String nickname,
	Gender gender,
	String phone
) {

}

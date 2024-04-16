package shop.jtoon.member.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum MemberStatus {

	NORMAL,
	REST,
	BLACK
}

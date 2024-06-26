package shop.jtoon.payment.entity;

import static java.util.Objects.*;
import static shop.jtoon.type.ErrorStatus.*;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import shop.jtoon.exception.InvalidRequestException;
import shop.jtoon.common.BaseTimeEntity;
import shop.jtoon.member.entity.Member;

@Entity
@Getter
@Table(name = "member_cookies")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberCookie extends BaseTimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "member_cookie_id")
	private Long id;

	@Column(name = "cookie_count", nullable = false)
	private int cookieCount;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id", nullable = false)
	private Member member;

	@Builder
	private MemberCookie(int cookieCount, Member member) {
		this.cookieCount = validateCookieCount(cookieCount);
		this.member = requireNonNull(member, MEMBER_IS_NULL.getMessage());
	}

	public static MemberCookie create(int cookieCount, Member member) {
		return MemberCookie.builder()
			.cookieCount(cookieCount)
			.member(member)
			.build();
	}

	public void decreaseCookieCount(int cookieCount) {
		if (this.cookieCount < cookieCount) {
			throw new InvalidRequestException(EPISODE_NOT_ENOUGH_COOKIES);
		}

		this.cookieCount -= cookieCount;
	}

	private int validateCookieCount(int cookieCount) {
		if (cookieCount < 0) {
			throw new InvalidRequestException(COOKIE_COUNT_NOT_NEGATIVE);
		}

		return cookieCount;
	}
}

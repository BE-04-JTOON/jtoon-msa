package shop.jtoon.webtoon.entity;

import static java.util.Objects.*;
import static shop.jtoon.type.ErrorStatus.*;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
import shop.jtoon.event.entity.EventStatus;
import shop.jtoon.exception.InvalidRequestException;
import shop.jtoon.common.BaseTimeEntity;
import shop.jtoon.member.entity.Member;
import shop.jtoon.webtoon.entity.enums.AgeLimit;

@Entity
@Getter
@Table(name = "webtoons")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Webtoon extends BaseTimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "webtoon_id")
	private Long id;

	@Column(name = "title", nullable = false, unique = true, length = 30)
	private String title;

	@Column(name = "description", nullable = false, length = 400)
	private String description;

	@Column(name = "age_limit", nullable = false)
	private AgeLimit ageLimit;

	@Column(name = "thumbnail_url", nullable = false, length = 500)
	private String thumbnailUrl;

	@Column(name = "cookie_count", nullable = false)
	private int cookieCount;

	@Column(name = "favorite_count", nullable = false)
	private int favoriteCount;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "author_id", nullable = false)
	private Member author;

	@Enumerated(EnumType.STRING)
	@Column(name = "status")
	private EventStatus eventStatus;

	@Builder
	private Webtoon(
		String title,
		String description,
		AgeLimit ageLimit,
		String thumbnailUrl,
		int cookieCount,
		Member author
	) {
		this.title = requireNonNull(title, WEBTOON_TITLE_IS_NULL.getMessage());
		this.description = requireNonNull(description, WEBTOON_DESCRIPTION_IS_NULL.getMessage());
		this.ageLimit = requireNonNull(ageLimit, WEBTOON_AGE_LIMIT_IS_NULL.getMessage());
		this.thumbnailUrl = requireNonNull(thumbnailUrl, WEBTOON_THUMBNAIL_URL_IS_NULL.getMessage());
		this.cookieCount = validateCookieCount(cookieCount);
		this.author = requireNonNull(author, WEBTOON_AUTHOR_IS_NULL.getMessage());
		this.eventStatus = EventStatus.READY;
	}

	public void validateAuthor(Long memberId) {
		if (!author.isEqual(memberId)) {
			throw new InvalidRequestException(WEBTOON_NOT_AUTHOR);
		}
	}

	private int validateCookieCount(int cookieCount) {
		if (cookieCount < 0) {
			throw new InvalidRequestException(COOKIE_COUNT_NOT_NEGATIVE);
		}

		return cookieCount;
	}
}

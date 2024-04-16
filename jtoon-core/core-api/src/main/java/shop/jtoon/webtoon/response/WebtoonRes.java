package shop.jtoon.webtoon.response;

import java.time.LocalDateTime;

import lombok.Builder;
import shop.jtoon.member.entity.Member;
import shop.jtoon.webtoon.entity.Webtoon;
import shop.jtoon.webtoon.entity.enums.AgeLimit;

@Builder
public record WebtoonRes(
	Long id,
	String title,
	String description,
	AgeLimit ageLimit,
	String thumbnailUrl,
	int cookieCount,
	int favoriteCount,
	Member author,
	LocalDateTime createdAt,
	LocalDateTime updatedAt
) {

	public static WebtoonRes from(Webtoon webtoon) {
		return WebtoonRes.builder()
			.id(webtoon.getId())
			.title(webtoon.getTitle())
			.description(webtoon.getDescription())
			.ageLimit(webtoon.getAgeLimit())
			.thumbnailUrl(webtoon.getThumbnailUrl())
			.cookieCount(webtoon.getCookieCount())
			.favoriteCount(webtoon.getFavoriteCount())
			.author(webtoon.getAuthor())
			.createdAt(webtoon.getCreatedAt())
			.updatedAt(webtoon.getUpdatedAt())
			.build();
	}
}

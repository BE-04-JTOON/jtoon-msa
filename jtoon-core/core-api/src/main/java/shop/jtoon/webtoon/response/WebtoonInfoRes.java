package shop.jtoon.webtoon.response;

import java.util.List;

import lombok.Builder;
import shop.jtoon.webtoon.domain.WebtoonDetail;
import shop.jtoon.webtoon.entity.Webtoon;
import shop.jtoon.webtoon.entity.enums.AgeLimit;

@Builder
public record WebtoonInfoRes(
	String title,
	String description,
	List<String> dayOfWeeks,
	List<GenreRes> genres,
	AgeLimit ageLimit,
	String thumbnailUrl,
	int favoriteCount,
	AuthorRes author
) {

	public static WebtoonInfoRes of(WebtoonDetail webtoonDetail) {
		return WebtoonInfoRes.builder()
			.title(webtoonDetail.title())
			.description(webtoonDetail.description())
			.dayOfWeeks(webtoonDetail.dayOfWeeks())
			.genres(GenreRes.from(webtoonDetail.genres()))
			.ageLimit(webtoonDetail.ageLimit())
			.thumbnailUrl(webtoonDetail.thumbnailUrl())
			.favoriteCount(webtoonDetail.favoriteCount())
			.author(AuthorRes.from(webtoonDetail.author()))
			.build();
	}
}

package shop.jtoon.webtoon.domain;

import java.util.List;

import lombok.Builder;
import shop.jtoon.webtoon.entity.Webtoon;
import shop.jtoon.webtoon.entity.enums.AgeLimit;
import shop.jtoon.webtoon.entity.enums.Genre;

@Builder
public record WebtoonDetail(
	String title,
	String description,
	List<String> dayOfWeeks,
	List<Genre> genres,
	AgeLimit ageLimit,
	String thumbnailUrl,
	int favoriteCount,
	Author author
) {

	public static WebtoonDetail of(Webtoon webtoon, List<String> dayOfWeeks, List<Genre> genres) {
		return WebtoonDetail.builder()
			.title(webtoon.getTitle())
			.description(webtoon.getDescription())
			.dayOfWeeks(dayOfWeeks)
			.genres(genres)
			.ageLimit(webtoon.getAgeLimit())
			.thumbnailUrl(webtoon.getThumbnailUrl())
			.favoriteCount(webtoon.getFavoriteCount())
			.author(Author.from(webtoon.getAuthor()))
			.build();
	}

}

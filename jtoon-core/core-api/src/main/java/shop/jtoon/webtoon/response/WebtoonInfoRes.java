package shop.jtoon.webtoon.response;

import java.util.List;

import lombok.Builder;
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

	public static WebtoonInfoRes of(Webtoon webtoon, List<String> dayOfWeeks, List<GenreRes> genres) {
		return WebtoonInfoRes.builder()
			.title(webtoon.getTitle())
			.description(webtoon.getDescription())
			.dayOfWeeks(dayOfWeeks)
			.genres(genres)
			.ageLimit(webtoon.getAgeLimit())
			.thumbnailUrl(webtoon.getThumbnailUrl())
			.favoriteCount(webtoon.getFavoriteCount())
			.author(AuthorRes.from(webtoon.getAuthor()))
			.build();
	}
}

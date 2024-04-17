package shop.jtoon.webtoon.domain;

import java.util.List;
import java.util.Set;

import lombok.Builder;
import shop.jtoon.webtoon.entity.DayOfWeekWebtoon;
import shop.jtoon.webtoon.entity.GenreWebtoon;
import shop.jtoon.webtoon.entity.Webtoon;
import shop.jtoon.webtoon.entity.enums.Genre;

@Builder
public record WebtoonGenres(
	Set<Genre> genres
) {
	public List<GenreWebtoon> toGenreWebtoonEntity(Webtoon webtoon) {
		return genres.stream()
			.map(genre -> GenreWebtoon.create(genre, webtoon))
			.toList();
	}
}

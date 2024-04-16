package shop.jtoon.webtoon.response;

import lombok.Builder;
import shop.jtoon.webtoon.entity.GenreWebtoon;
import shop.jtoon.webtoon.entity.enums.Genre;

@Builder
public record GenreRes(
	Genre type,
	String name
) {

	public static GenreRes from(GenreWebtoon genreWebtoon) {
		return GenreRes.builder()
			.type(genreWebtoon.getGenre())
			.name(genreWebtoon.getGenre().getText())
			.build();
	}
}

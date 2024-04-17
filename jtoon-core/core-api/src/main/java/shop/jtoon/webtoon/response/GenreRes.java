package shop.jtoon.webtoon.response;

import java.util.List;

import lombok.Builder;
import shop.jtoon.webtoon.entity.GenreWebtoon;
import shop.jtoon.webtoon.entity.enums.Genre;

@Builder
public record GenreRes(
	Genre type,
	String name
) {

	public static GenreRes from(Genre genre) {
		return GenreRes.builder()
			.type(genre)
			.name(genre.getText())
			.build();
	}

	public static List<GenreRes> from(List<Genre> genres) {
		return genres.stream()
				.map(GenreRes::from)
				.toList();
	}
}

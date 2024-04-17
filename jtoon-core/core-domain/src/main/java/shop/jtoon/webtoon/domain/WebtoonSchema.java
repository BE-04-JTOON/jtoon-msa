package shop.jtoon.webtoon.domain;

import lombok.Builder;
import shop.jtoon.webtoon.entity.Webtoon;

@Builder
public record WebtoonSchema(
	Long webtoonId,
	String title,
	String thumbnailUrl,
	int ageLimit,
	Author author
) {
	public static WebtoonSchema from(Webtoon webtoon) {
		return WebtoonSchema.builder()
			.webtoonId(webtoon.getId())
			.title(webtoon.getTitle())
			.thumbnailUrl(webtoon.getThumbnailUrl())
			.ageLimit(webtoon.getAgeLimit().getValue())
			.author(Author.from(webtoon.getAuthor()))
			.build();
	}
}

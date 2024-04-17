package shop.jtoon.webtoon.domain;

import java.time.LocalDateTime;

import org.antlr.v4.runtime.misc.NotNull;

import lombok.Builder;
import shop.jtoon.webtoon.entity.Episode;
import shop.jtoon.webtoon.entity.Webtoon;

@Builder
public record EpisodeSchema(
	int no,
	String title,
	boolean hasComment,
	LocalDateTime openedAt
) {
	public Episode toEpisode(Webtoon webtoon, String mainUrl, String thumbnailUrl) {
		return Episode.builder()
			.no(no)
			.title(title)
			.hasComment(hasComment)
			.openedAt(openedAt)
			.mainUrl(mainUrl)
			.thumbnailUrl(thumbnailUrl)
			.webtoon(webtoon)
			.build();
	}
}

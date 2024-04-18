package shop.jtoon.webtoon.domain;

import static shop.jtoon.util.WebtoonUitls.*;

import java.time.LocalDateTime;
import java.util.List;

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
	public Episode toEpisode(Webtoon webtoon, List<String> mainUrls, String thumbnailUrl) {
		return Episode.builder()
			.no(no)
			.title(title)
			.hasComment(hasComment)
			.openedAt(openedAt)
			.mainUrl(String.join(URL_SPLITER, mainUrls))
			.thumbnailUrl(thumbnailUrl)
			.webtoon(webtoon)
			.build();
	}
}

package shop.jtoon.webtoon.domain;

import lombok.Builder;
import shop.jtoon.webtoon.entity.Episode;

@Builder
public record EpisodeMainInfo(
	String mainUrl
) {

	public static EpisodeMainInfo of(Episode episode) {
		return EpisodeMainInfo.builder()
			.mainUrl(episode.getMainUrl())
			.build();
	}
}

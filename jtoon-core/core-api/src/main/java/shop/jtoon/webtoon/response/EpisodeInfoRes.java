package shop.jtoon.webtoon.response;

import lombok.Builder;
import shop.jtoon.webtoon.entity.Episode;

@Builder
public record EpisodeInfoRes(
	String mainUrl
) {

	public static EpisodeInfoRes from(Episode episode) {
		return EpisodeInfoRes.builder()
			.mainUrl(episode.getMainUrl())
			.build();
	}
}

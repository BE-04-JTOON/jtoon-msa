package shop.jtoon.webtoon.response;

import lombok.Builder;
import shop.jtoon.webtoon.domain.EpisodeMainInfo;
import shop.jtoon.webtoon.entity.Episode;

@Builder
public record EpisodeInfoRes(
	String mainUrl
) {

	public static EpisodeInfoRes from(EpisodeMainInfo episode) {
		return EpisodeInfoRes.builder()
			.mainUrl(episode.mainUrl())
			.build();
	}
}

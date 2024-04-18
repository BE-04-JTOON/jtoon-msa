package shop.jtoon.webtoon.response;

import java.util.List;

import lombok.Builder;
import shop.jtoon.webtoon.domain.EpisodeMainInfo;
import shop.jtoon.webtoon.entity.Episode;

@Builder
public record EpisodeInfoRes(
	List<String> mainUrls
) {

	public static EpisodeInfoRes from(EpisodeMainInfo episode) {
		return EpisodeInfoRes.builder()
			.mainUrls(episode.mainUrls())
			.build();
	}
}

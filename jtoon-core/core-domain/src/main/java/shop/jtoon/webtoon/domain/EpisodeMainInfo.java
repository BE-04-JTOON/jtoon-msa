package shop.jtoon.webtoon.domain;

import static shop.jtoon.util.WebtoonUitls.*;

import java.util.List;

import lombok.Builder;
import shop.jtoon.webtoon.entity.Episode;

@Builder
public record EpisodeMainInfo(
	List<String> mainUrls
) {

	public static EpisodeMainInfo of(Episode episode) {
		return EpisodeMainInfo.builder()
			.mainUrls(List.of(episode.getMainUrl().split(URL_SPLITER)))
			.build();
	}
}

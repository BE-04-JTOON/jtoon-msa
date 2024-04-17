package shop.jtoon.webtoon.domain;

import java.time.format.DateTimeFormatter;

import lombok.Builder;
import shop.jtoon.webtoon.entity.Episode;

@Builder
public record SearchEpisode(
	Long episodeId,
	int no,
	String title,
	String thumbnailUrl,
	String openedAt
) {
	public static SearchEpisode from(Episode episode) {
		return SearchEpisode.builder()
			.episodeId(episode.getId())
			.no(episode.getNo())
			.title(episode.getTitle())
			.thumbnailUrl(episode.getThumbnailUrl())
			.openedAt(episode.getOpenedAt().format(DateTimeFormatter.ofPattern("yy.MM.dd")))
			.build();
	}
}

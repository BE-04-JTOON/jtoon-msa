package shop.jtoon.webtoon.response;

import java.time.format.DateTimeFormatter;
import java.util.List;

import lombok.Builder;
import shop.jtoon.webtoon.domain.SearchEpisode;
import shop.jtoon.webtoon.entity.Episode;

@Builder
public record EpisodeItemRes(
	Long episodeId,
	int no,
	String title,
	String thumbnailUrl,
	String openedAt
) {

	public static EpisodeItemRes from(SearchEpisode episode) {
		return EpisodeItemRes.builder()
			.episodeId(episode.episodeId())
			.no(episode.no())
			.title(episode.title())
			.thumbnailUrl(episode.thumbnailUrl())
			.openedAt(episode.openedAt())
			.build();
	}

	public static List<EpisodeItemRes> from(List<SearchEpisode> episodes) {
		return episodes.stream()
			.map(EpisodeItemRes::from)
			.toList();
	}
}

package shop.jtoon.webtoon.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import shop.jtoon.webtoon.domain.SearchEpisode;
import shop.jtoon.webtoon.entity.Episode;
import shop.jtoon.webtoon.entity.Webtoon;

@Repository
@RequiredArgsConstructor
public class EpisodeWriter {

	private final EpisodeRepository episodeRepository;
	private final EpisodeSearchRepository episodeSearchRepository;

	public void write(Episode episode) {
		episodeRepository.save(episode);
	}

	public List<SearchEpisode> readEpisodes(Long webtoonId, int size, Long offset) {
		return episodeSearchRepository.getEpisodes(webtoonId, size, offset)
			.stream()
			.map(SearchEpisode::from)
			.toList();
	}
}

package shop.jtoon.webtoon.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;
import shop.jtoon.util.DynamicQuery;
import shop.jtoon.webtoon.entity.Episode;
import shop.jtoon.webtoon.entity.QEpisode;

@Repository
@RequiredArgsConstructor
public class EpisodeSearchRepository {

	private final JPAQueryFactory jpaQueryFactory;

	public List<Episode> getEpisodes(Long webtoonId, int size, Long offset) {
		return jpaQueryFactory.selectFrom(QEpisode.episode)
			.where(DynamicQuery.generateEq(webtoonId, QEpisode.episode.webtoon.id::eq))
			.limit(size)
			.offset(offset)
			.orderBy(QEpisode.episode.no.desc())
			.fetch();
	}
}

package shop.jtoon.webtoon.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import shop.jtoon.webtoon.entity.Episode;
import shop.jtoon.webtoon.entity.Webtoon;

public interface EpisodeRepository extends JpaRepository<Episode, Long> {

	boolean existsByWebtoonAndNo(Webtoon webtoon, int no);
}

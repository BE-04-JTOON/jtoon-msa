package shop.jtoon.webtoon.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import shop.jtoon.webtoon.entity.GenreWebtoon;
import shop.jtoon.webtoon.entity.Webtoon;

public interface GenreWebtoonRepository extends JpaRepository<GenreWebtoon, Long> {

	List<GenreWebtoon> findByWebtoon(Webtoon webtoon);
}

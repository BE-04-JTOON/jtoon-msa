package shop.jtoon.webtoon.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import shop.jtoon.webtoon.entity.DayOfWeekWebtoon;
import shop.jtoon.webtoon.entity.Webtoon;

public interface DayOfWeekWebtoonRepository extends JpaRepository<DayOfWeekWebtoon, Long> {

	List<DayOfWeekWebtoon> findByWebtoon(Webtoon webtoon);
}

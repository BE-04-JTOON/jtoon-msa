package shop.jtoon.webtoon.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import shop.jtoon.webtoon.entity.Webtoon;

public interface WebtoonRepository extends JpaRepository<Webtoon, Long> {

	boolean existsByTitle(String title);
}

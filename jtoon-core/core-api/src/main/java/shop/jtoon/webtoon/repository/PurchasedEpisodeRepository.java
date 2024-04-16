package shop.jtoon.webtoon.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import shop.jtoon.webtoon.entity.PurchasedEpisode;

public interface PurchasedEpisodeRepository extends JpaRepository<PurchasedEpisode, Long> {
}

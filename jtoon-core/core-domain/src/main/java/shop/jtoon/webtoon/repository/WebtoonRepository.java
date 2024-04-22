package shop.jtoon.webtoon.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import shop.jtoon.event.entity.EventStatus;
import shop.jtoon.webtoon.entity.Webtoon;

public interface WebtoonRepository extends JpaRepository<Webtoon, Long> {

	boolean existsByTitle(String title);

	Optional<Webtoon> findByIdAndEventStatus(Long webtoonId, EventStatus eventStatus);

	@Modifying
	@Query("update Webtoon set eventStatus = 'OK' where id = :webtoonId")
	void updateStatus(Long webtoonId);

	@Modifying
	@Query("update Webtoon set eventStatus = 'OK' where id in (:webtoonIds)")
	void updateStatus(List<Long> webtoonIds);
}

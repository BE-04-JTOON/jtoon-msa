package shop.jtoon.event.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;
import shop.jtoon.event.entity.Event;
import shop.jtoon.event.entity.EventStatus;
import shop.jtoon.event.entity.QEvent;

@Repository
@RequiredArgsConstructor
public class EventSearchRepository {

	private final JPAQueryFactory jpaQueryFactory;

	public List<Event> findByCreateAtBefore(LocalDateTime now) {
		return jpaQueryFactory.selectFrom(QEvent.event)
			.where(
				QEvent.event.createdAt.before(now),
				QEvent.event.status.eq(EventStatus.READY)
			)
			.fetch();
	}
}

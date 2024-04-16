package shop.jtoon.webtoon.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;
import shop.jtoon.member.entity.QMember;
import shop.jtoon.util.DynamicQuery;
import shop.jtoon.webtoon.entity.DayOfWeekWebtoon;
import shop.jtoon.webtoon.entity.QDayOfWeekWebtoon;
import shop.jtoon.webtoon.entity.QWebtoon;
import shop.jtoon.webtoon.entity.enums.DayOfWeek;

@Repository
@RequiredArgsConstructor
public class WebtoonSearchRepository {

	private final JPAQueryFactory jpaQueryFactory;

	public List<DayOfWeekWebtoon> findWebtoons(DayOfWeek dayOfWeek, String keyword) {
		return jpaQueryFactory.selectFrom(QDayOfWeekWebtoon.dayOfWeekWebtoon)
			.join(QDayOfWeekWebtoon.dayOfWeekWebtoon.webtoon, QWebtoon.webtoon)
			.join(QWebtoon.webtoon.author, QMember.member)
			.where(
				DynamicQuery.generateEq(dayOfWeek, QDayOfWeekWebtoon.dayOfWeekWebtoon.dayOfWeek::eq),
				DynamicQuery.generateEq(keyword, this::containsKeyword)
			)
			.fetch();
	}

	private BooleanExpression containsKeyword(String keyword) {
		return QWebtoon.webtoon.title.contains(keyword)
			.or(QWebtoon.webtoon.author.nickname.contains(keyword));
	}
}

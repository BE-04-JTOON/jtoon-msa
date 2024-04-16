package shop.jtoon.webtoon.repository;

import static shop.jtoon.member.entity.QMember.*;
import static shop.jtoon.webtoon.entity.QDayOfWeekWebtoon.*;
import static shop.jtoon.webtoon.entity.QWebtoon.*;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;
import shop.jtoon.global.util.DynamicQuery;
import shop.jtoon.webtoon.entity.DayOfWeekWebtoon;
import shop.jtoon.webtoon.entity.enums.DayOfWeek;

@Repository
@RequiredArgsConstructor
public class WebtoonSearchRepository {

	private final JPAQueryFactory jpaQueryFactory;
	private final DayOfWeekWebtoonRepository dayOfWeekWebtoonRepository;

	public List<DayOfWeekWebtoon> findWebtoons(DayOfWeek dayOfWeek, String keyword) {
		return jpaQueryFactory.selectFrom(dayOfWeekWebtoon)
			.join(dayOfWeekWebtoon.webtoon, webtoon)
			.join(webtoon.author, member)
			.where(
				DynamicQuery.generateEq(dayOfWeek, dayOfWeekWebtoon.dayOfWeek::eq),
				DynamicQuery.generateEq(keyword, this::containsKeyword)
			)
			.fetch();
	}

	private BooleanExpression containsKeyword(String keyword) {
		return webtoon.title.contains(keyword)
			.or(webtoon.author.nickname.contains(keyword));
	}
}

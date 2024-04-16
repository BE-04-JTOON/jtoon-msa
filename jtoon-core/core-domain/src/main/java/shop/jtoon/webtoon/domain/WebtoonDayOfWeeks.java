package shop.jtoon.webtoon.domain;

import java.util.List;
import java.util.Set;

import lombok.Builder;
import shop.jtoon.webtoon.entity.DayOfWeekWebtoon;
import shop.jtoon.webtoon.entity.Webtoon;
import shop.jtoon.webtoon.entity.enums.DayOfWeek;

@Builder
public record WebtoonDayOfWeeks(
	Set<DayOfWeek> dayOfWeeks
) {
	public List<DayOfWeekWebtoon> toDayOfWeekWebtoonEntity(Webtoon webtoon) {
		return dayOfWeeks.stream()
			.map(dayOfWeek -> DayOfWeekWebtoon.create(dayOfWeek, webtoon))
			.toList();
	}
}

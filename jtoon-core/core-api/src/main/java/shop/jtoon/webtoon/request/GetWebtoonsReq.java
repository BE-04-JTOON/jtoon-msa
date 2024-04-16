package shop.jtoon.webtoon.request;

import lombok.Builder;
import shop.jtoon.webtoon.domain.SearchWebtoon;
import shop.jtoon.webtoon.entity.enums.DayOfWeek;

@Builder
public record GetWebtoonsReq(
	DayOfWeek day,
	String keyword
) {

	public SearchWebtoon toSearchWebtoon() {
		return SearchWebtoon.builder()
			.day(day)
			.keyword(keyword)
			.build();
	}
}

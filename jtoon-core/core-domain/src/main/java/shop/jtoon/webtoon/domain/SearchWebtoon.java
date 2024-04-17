package shop.jtoon.webtoon.domain;

import lombok.Builder;
import shop.jtoon.webtoon.entity.enums.DayOfWeek;

@Builder
public record SearchWebtoon(
	DayOfWeek day,
	String keyword
) {

}

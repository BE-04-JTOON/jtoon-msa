package shop.jtoon.webtoon.response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.Builder;
import shop.jtoon.webtoon.domain.WebtoonSchema;
import shop.jtoon.webtoon.entity.enums.DayOfWeek;

@Builder
public record WebtoonItemRes(
	Long webtoonId,
	String title,
	String thumbnailUrl,
	int ageLimit,
	AuthorRes author
) {

	public static WebtoonItemRes from(WebtoonSchema webtoon) {
		return WebtoonItemRes.builder()
			.webtoonId(webtoon.webtoonId())
			.title(webtoon.title())
			.thumbnailUrl(webtoon.thumbnailUrl())
			.ageLimit(webtoon.ageLimit())
			.author(AuthorRes.from(webtoon.author()))
			.build();
	}

	public static Map<DayOfWeek, List<WebtoonItemRes>> from(Map<DayOfWeek, List<WebtoonSchema>> webtoons) {
		Map<DayOfWeek, List<WebtoonItemRes>> webtoonRes = new HashMap<>();
		for (DayOfWeek day : webtoons.keySet()) {
			webtoonRes.put(day, webtoons.get(day).stream().map(WebtoonItemRes::from).toList());
		}

		return  webtoonRes;
	}
}

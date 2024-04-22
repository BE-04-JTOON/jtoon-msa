package shop.jtoon.webtoon.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import shop.jtoon.webtoon.entity.DayOfWeekWebtoon;
import shop.jtoon.webtoon.entity.GenreWebtoon;
import shop.jtoon.webtoon.entity.Webtoon;

@Repository
@RequiredArgsConstructor
public class WebtoonWriter {

	private final WebtoonRepository webtoonRepository;
	private final DayOfWeekWebtoonRepository dayOfWeekWebtoonRepository;
	private final GenreWebtoonRepository genreWebtoonRepository;

	public void createWebtoon(Webtoon webtoon, List<DayOfWeekWebtoon> dayOfWeekWebtoons,
		List<GenreWebtoon> genreWebtoons) {
		webtoonRepository.save(webtoon);
		dayOfWeekWebtoonRepository.saveAll(dayOfWeekWebtoons);
		genreWebtoonRepository.saveAll(genreWebtoons);
	}

	public void update(List<Long> webtoonIds) {
		webtoonRepository.updateStatus(webtoonIds);
	}
}

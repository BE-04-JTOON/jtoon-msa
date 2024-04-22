package shop.jtoon.webtoon.repository;

import static shop.jtoon.type.ErrorStatus.*;

import java.util.List;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import shop.jtoon.event.entity.EventStatus;
import shop.jtoon.exception.NotFoundException;
import shop.jtoon.webtoon.domain.SearchWebtoon;
import shop.jtoon.webtoon.entity.DayOfWeekWebtoon;
import shop.jtoon.webtoon.entity.GenreWebtoon;
import shop.jtoon.webtoon.entity.Webtoon;
import shop.jtoon.webtoon.entity.enums.Genre;

@Repository
@RequiredArgsConstructor
public class WebtoonReader {

	private final WebtoonRepository webtoonRepository;
	private final WebtoonSearchRepository webtoonSearchRepository;
	private final DayOfWeekWebtoonRepository dayOfWeekWebtoonRepository;
	private final GenreWebtoonRepository genreWebtoonRepository;

	public List<DayOfWeekWebtoon> search(SearchWebtoon search) {
		return webtoonSearchRepository.findWebtoons(search.day(), search.keyword());
	}

	public Webtoon read(Long webtoonId) {
		return webtoonRepository.findByIdAndEventStatus(webtoonId, EventStatus.OK).orElseThrow(() -> new NotFoundException(WEBTOON_NOT_FOUND));
	}

	public List<String> readDayOfWebtoon(Webtoon webtoon) {
		return dayOfWeekWebtoonRepository.findByWebtoon(webtoon)
			.stream()
			.map(DayOfWeekWebtoon::getDayOfWeekName)
			.toList();
	}

	public List<Genre> readGenreOfWebtoon(Webtoon webtoon) {
		return genreWebtoonRepository.findByWebtoon(webtoon).stream()
			.map(GenreWebtoon::getGenre).toList();
	}

	public boolean exsistsTitie(String title) {
		return webtoonRepository.existsByTitle(title);
	}
}

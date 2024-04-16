package shop.jtoon.webtoon.repository;

import static shop.jtoon.type.ErrorStatus.*;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import shop.jtoon.exception.NotFoundException;
import shop.jtoon.payment.repository.CookieReader;
import shop.jtoon.payment.repository.PaymentReader;
import shop.jtoon.webtoon.entity.Episode;
import shop.jtoon.webtoon.entity.PurchasedEpisode;
import shop.jtoon.webtoon.entity.Webtoon;

@Repository
@RequiredArgsConstructor
public class EpisodeReader {

	private final EpisodeRepository episodeRepository;
	private final PurchasedEpisodeRepository purchasedEpisodeRepository;

	public boolean validateDuplicateNo(Webtoon webtoon, int no) {
		return episodeRepository.existsByWebtoonAndNo(webtoon, no);
	}

	public Episode read(Long episodeId) {
		return episodeRepository.findById(episodeId).orElseThrow(() -> new NotFoundException(EPISODE_NOT_FOUND));
	}

	public void save(PurchasedEpisode purchasedEpisode) {
		purchasedEpisodeRepository.save(purchasedEpisode);
	}
}

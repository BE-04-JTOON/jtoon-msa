package shop.jtoon.webtoon.application;

import static shop.jtoon.common.ImageType.*;
import static shop.jtoon.type.ErrorStatus.*;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;

import shop.jtoon.exception.InvalidRequestException;

import shop.jtoon.webtoon.domain.EpisodeMainInfo;
import shop.jtoon.webtoon.domain.EpisodeSchema;
import shop.jtoon.webtoon.entity.Webtoon;
import shop.jtoon.webtoon.request.CreateEpisodeReq;
import shop.jtoon.webtoon.request.GetEpisodesReq;
import shop.jtoon.webtoon.response.EpisodeInfoRes;
import shop.jtoon.webtoon.response.EpisodeItemRes;
import shop.jtoon.webtoon.service.EpisodeDomainService;

@Service
@RequiredArgsConstructor
public class EpisodeService {

	private final WebtoonClientService webtoonClientService;
	private final EpisodeDomainService episodeDomainService;

	public void createEpisode(
		Long memberId,
		Long webtoonId,
		MultipartFile mainImage,
		MultipartFile thumbnailImage,
		CreateEpisodeReq request
	) {
		Webtoon webtoon = episodeDomainService.readWebtoon(webtoonId, memberId, request.no());
		String mainUrl = webtoonClientService.upload(request.toUploadImageDto(EPISODE_MAIN, webtoon.getTitle(), mainImage));
		String thumbnailUrl = webtoonClientService.upload(request.toUploadImageDto(
			EPISODE_THUMBNAIL,
			webtoon.getTitle(),
			thumbnailImage
		));

		try {
			EpisodeSchema episode = request.toEpisodeSchema();
			episodeDomainService.createEpisode(episode, webtoon, mainUrl, thumbnailUrl);
		} catch (RuntimeException e) {
			webtoonClientService.deleteImage(mainUrl);
			webtoonClientService.deleteImage(thumbnailUrl);
			throw new InvalidRequestException(EPISODE_CREATE_FAIL);
		}
	}

	public List<EpisodeItemRes> getEpisodes(Long webtoonId, GetEpisodesReq request) {
		return EpisodeItemRes.from(episodeDomainService.readEpisodes(webtoonId, request.getSize(), request.getOffset()));
	}

	public EpisodeInfoRes getEpisode(Long episodeId) {
		EpisodeMainInfo episode = episodeDomainService.readEpisode(episodeId);

		return EpisodeInfoRes.from(episode);
	}

	public void purchaseEpisode(Long memberId, Long episodeId) {
		episodeDomainService.purchaseEpisode(memberId, episodeId);
	}
}

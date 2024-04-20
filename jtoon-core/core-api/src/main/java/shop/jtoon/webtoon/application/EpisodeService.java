package shop.jtoon.webtoon.application;

import static shop.jtoon.common.ImageType.*;

import java.util.List;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;
import shop.jtoon.dto.ImageUploadEvent;
import shop.jtoon.dto.MultiImageEvent;
import shop.jtoon.webtoon.domain.EpisodeMainInfo;
import shop.jtoon.webtoon.domain.EpisodeSchema;
import shop.jtoon.webtoon.entity.Webtoon;
import shop.jtoon.webtoon.request.CreateEpisodeReq;
import shop.jtoon.webtoon.request.GetEpisodesReq;
import shop.jtoon.webtoon.request.MultiImagesReq;
import shop.jtoon.webtoon.response.EpisodeInfoRes;
import shop.jtoon.webtoon.response.EpisodeItemRes;
import shop.jtoon.webtoon.service.EpisodeDomainService;

@Service
@RequiredArgsConstructor
public class EpisodeService {

	private final WebtoonClientService webtoonClientService;
	private final EpisodeDomainService episodeDomainService;
	private final ApplicationEventPublisher publisher;

	public void createEpisode(
		Long memberId,
		Long webtoonId,
		MultiImagesReq mainImages,
		MultipartFile thumbnailImage,
		CreateEpisodeReq request
	) {
		Webtoon webtoon = episodeDomainService.readWebtoon(webtoonId, memberId, request.no());

		MultiImageEvent mainUploadEvents = MultiImageEvent.builder()
			.imageUploadEvents(mainImages.toMultiImageEvent(request, webtoon.getTitle()))
			.build();
		List<String> mainUrls = mainUploadEvents.imageUploadEvents().stream()
			.map(webtoonClientService::parseUrl)
			.toList();

		ImageUploadEvent thumbnailUploadEvent = request.toUploadImageDto(
			EPISODE_THUMBNAIL,
			webtoon.getTitle(),
			thumbnailImage
		).toImageUploadEvent();
		String thumbnailUrl = webtoonClientService.upload(thumbnailUploadEvent);

		EpisodeSchema episode = request.toEpisodeSchema();
		episodeDomainService.createEpisode(episode, webtoon, mainUrls, thumbnailUrl);

		publisher.publishEvent(mainUploadEvents);
		publisher.publishEvent(thumbnailUploadEvent);
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

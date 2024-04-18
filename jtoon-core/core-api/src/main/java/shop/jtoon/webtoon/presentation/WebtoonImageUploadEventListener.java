package shop.jtoon.webtoon.presentation;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import shop.jtoon.dto.ImageUploadEvent;
import shop.jtoon.dto.MultiImageEvent;
import shop.jtoon.global.util.AsyncEventListener;
import shop.jtoon.webtoon.application.WebtoonClientService;

@Component
@RequiredArgsConstructor
public class WebtoonImageUploadEventListener {

	private final WebtoonClientService webtoonClientService;

	@AsyncEventListener
	public void uploadImage(ImageUploadEvent imageUploadEvent) {
		webtoonClientService.upload(imageUploadEvent);
	}

	@AsyncEventListener
	public void uploadMultiImages(MultiImageEvent multiImageEvent) {
		multiImageEvent.imageUploadEvents().stream()
			.parallel()
			.forEach(webtoonClientService::upload);
	}

}

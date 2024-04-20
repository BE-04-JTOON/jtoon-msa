package shop.jtoon.webtoon.presentation;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import shop.jtoon.dto.ImageUpload;
import shop.jtoon.global.util.AsyncEventListener;
import shop.jtoon.webtoon.application.WebtoonClientService;
import shop.jtoon.webtoon.request.MultiImageEvent;

@Component
@RequiredArgsConstructor
public class WebtoonImageUploadEventListener {

	private final WebtoonClientService webtoonClientService;

	@AsyncEventListener
	public void uploadImage(ImageUpload imageUpload) {
		webtoonClientService.upload(imageUpload);
	}

	@AsyncEventListener
	public void uploadMultiImages(MultiImageEvent multiImageEvent) {
		multiImageEvent.imageEvents().stream()
			.parallel()
			.forEach(imageEvent -> webtoonClientService.upload(imageEvent.toImageUpload()));
	}
}

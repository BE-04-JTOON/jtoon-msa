package shop.jtoon.webtoon.application;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import shop.jtoon.dto.ImageUploadEvent;
import shop.jtoon.service.S3Service;

@Service
@RequiredArgsConstructor
public class WebtoonClientService {

	private final S3Service s3Service;

	public String parseUrl(ImageUploadEvent imageUpload) {
		return s3Service.uploadUrl(imageUpload.key());
	}

	public String upload(ImageUploadEvent imageUpload) {
		return s3Service.uploadImage(imageUpload);
	}

	public void deleteImage(String thumbnailUrl) {
		s3Service.deleteImage(thumbnailUrl);
	}
}

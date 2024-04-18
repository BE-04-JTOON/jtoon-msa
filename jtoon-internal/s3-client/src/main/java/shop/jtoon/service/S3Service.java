package shop.jtoon.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import shop.jtoon.dto.ImageUploadEvent;
import shop.jtoon.dto.UploadImageDto;
import shop.jtoon.util.S3Manager;

@Service
@RequiredArgsConstructor
public class S3Service {

	private final S3Manager s3Manager;

	public String uploadUrl(String key) {
		return s3Manager.uploadUrl(key);
	}

	public String uploadImage(UploadImageDto dto) {
		return s3Manager.uploadImage(dto.toKey(), dto.image());
	}

	public String uploadImage(ImageUploadEvent imageUpload) {
		return s3Manager.uploadImage(imageUpload.key(), imageUpload.multipartFile());
	}

	public void deleteImage(String imageUrl) {
		s3Manager.delete(imageUrl);
	}
}

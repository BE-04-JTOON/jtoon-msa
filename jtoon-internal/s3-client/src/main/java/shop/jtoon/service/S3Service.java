package shop.jtoon.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import shop.jtoon.dto.ImageUpload;
import shop.jtoon.util.S3Manager;

@Service
@RequiredArgsConstructor
public class S3Service {

	private final S3Manager s3Manager;

	public String uploadUrl(String key) {
		return s3Manager.uploadUrl(key);
	}

	public ImageUpload uploadImage(ImageUpload imageUpload) {
		s3Manager.uploadImage(imageUpload.key(), imageUpload.data());
		return imageUpload;
	}

	public void deleteImage(String imageUrl) {
		s3Manager.delete(imageUrl);
	}
}

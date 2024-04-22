package shop.jtoon.dto;

import lombok.Builder;

@Builder
public record ImageUpload(
	String key,
	byte[] data
) {

}

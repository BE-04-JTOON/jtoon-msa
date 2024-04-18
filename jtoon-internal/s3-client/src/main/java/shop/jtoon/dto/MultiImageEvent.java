package shop.jtoon.dto;

import java.util.List;

import lombok.Builder;

@Builder
public record MultiImageEvent(
	List<ImageUploadEvent> imageUploadEvents
) {

}

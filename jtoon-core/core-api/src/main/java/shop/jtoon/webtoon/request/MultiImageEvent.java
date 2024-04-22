package shop.jtoon.webtoon.request;

import java.util.List;

import lombok.Builder;

@Builder
public record MultiImageEvent(
	List<ImageEvent> imageEvents
) {

}

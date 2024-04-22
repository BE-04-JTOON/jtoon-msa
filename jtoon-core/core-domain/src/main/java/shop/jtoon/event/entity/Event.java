package shop.jtoon.event.entity;

import org.hibernate.annotations.Type;

import io.hypersistence.utils.hibernate.type.json.JsonType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import shop.jtoon.common.BaseTimeEntity;
import shop.jtoon.event.domain.ImagePayload;

@Getter
@Entity
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class Event extends BaseTimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "member_id")
	private Long id;

	@Enumerated(EnumType.STRING)
	private EventStatus status;

	@Type(JsonType.class)
	@Column(columnDefinition = "json")
	private ImagePayload payload;

	@Builder
	public Event(Long id, EventStatus status, ImagePayload payload) {
		this.status = status;
		this.payload = payload;
	}
}

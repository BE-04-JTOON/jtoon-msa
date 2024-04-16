package shop.jtoon.payment.domain;

import java.math.BigDecimal;

import lombok.Builder;

@Builder
public record Item(
	String itemName,
	BigDecimal amount
) {

}

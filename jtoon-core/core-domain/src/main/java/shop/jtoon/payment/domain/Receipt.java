package shop.jtoon.payment.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.Builder;
import shop.jtoon.payment.entity.PaymentInfo;

@Builder
public record Receipt(
	String itemName,
	int itemCount,
	BigDecimal amount,
	LocalDateTime createdAt
) {
	public static Receipt toReceipt(PaymentInfo paymentInfo) {
		return Receipt.builder()
			.itemName(paymentInfo.getCookieItem().getItemName())
			.itemCount(paymentInfo.getCookieItem().getCount())
			.amount(paymentInfo.getAmount())
			.createdAt(paymentInfo.getCreatedAt())
			.build();
	}
}

package shop.jtoon.payment.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.Builder;
import shop.jtoon.payment.entity.PaymentInfo;

@Builder
public record PaymentRes(
        String itemName,
        int itemCount,
        BigDecimal amount,
        LocalDateTime createdAt
) {

    public static PaymentRes toDto(PaymentInfo paymentInfo) {
        return PaymentRes.builder()
                .itemName(paymentInfo.getCookieItem().getItemName())
                .itemCount(paymentInfo.getCookieItem().getCount())
                .amount(paymentInfo.getAmount())
                .createdAt(paymentInfo.getCreatedAt())
                .build();
    }
}

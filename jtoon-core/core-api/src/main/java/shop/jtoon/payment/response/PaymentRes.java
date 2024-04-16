package shop.jtoon.payment.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import lombok.Builder;
import shop.jtoon.payment.domain.Receipt;
import shop.jtoon.payment.entity.PaymentInfo;

@Builder
public record PaymentRes(
        String itemName,
        int itemCount,
        BigDecimal amount,
        LocalDateTime createdAt
) {

    public static PaymentRes toDto(Receipt receipt) {
        return PaymentRes.builder()
                .itemName(receipt.itemName())
                .itemCount(receipt.itemCount())
                .amount(receipt.amount())
                .createdAt(receipt.createdAt())
                .build();
    }

    public static List<PaymentRes> of(List<Receipt> paymentInfos) {
        return paymentInfos.stream()
            .map(PaymentRes::toDto)
            .toList();
    }
}

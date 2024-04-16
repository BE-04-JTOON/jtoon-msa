package shop.jtoon.payment.request;

import static shop.jtoon.util.RegExp.*;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import shop.jtoon.member.entity.Member;
import shop.jtoon.payment.domain.Buyer;
import shop.jtoon.payment.domain.Item;
import shop.jtoon.payment.domain.Pay;
import shop.jtoon.payment.entity.CookieItem;
import shop.jtoon.payment.entity.PaymentInfo;

@Builder
public record PaymentReq(
        @NotBlank String impUid,
        @NotBlank String merchantUid,
        @NotBlank String payMethod,
        @NotBlank String itemName,
        @NotNull @DecimalMin("1") BigDecimal amount,
        @Pattern(regexp = EMAIL_PATTERN) String buyerEmail,
        @NotBlank @Size(max = 10) String buyerName,
        @Pattern(regexp = PHONE_PATTERN) String buyerPhone
) {

    public PaymentInfo toEntity(Member member) {
        return PaymentInfo.builder()
                .impUid(this.impUid)
                .merchantUid(this.merchantUid)
                .payMethod(this.payMethod)
                .cookieItem(CookieItem.from(this.itemName))
                .amount(this.amount)
                .member(member)
                .build();
    }

    public Pay toPay() {
        return Pay.builder()
            .impUid(impUid)
            .merchantUid(merchantUid)
            .payMethod(payMethod)
            .build();
    }

    public Item toItem() {
        return Item.builder()
            .itemName(itemName)
            .amount(amount)
            .build();
    }

    public Buyer toBuyer() {
        return Buyer.builder()
            .buyerEmail(buyerEmail)
            .buyerName(buyerName)
            .buyerPhone(buyerPhone)
            .build();
    }
}

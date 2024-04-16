package shop.jtoon.payment.service;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import shop.jtoon.exception.InvalidRequestException;
import shop.jtoon.payment.entity.CookieItem;
import shop.jtoon.type.ErrorStatus;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PaymentInfoDomainService {

    public void validatePaymentInfo(String itemName, BigDecimal amount) {
        CookieItem cookieItem = CookieItem.from(itemName);
        validateAmount(amount, cookieItem.getAmount());
    }

    private void validateAmount(BigDecimal amount, BigDecimal cookieAmount) {
        if (!amount.equals(cookieAmount)) {
            throw new InvalidRequestException(ErrorStatus.PAYMENT_AMOUNT_INVALID);
        }
    }
}

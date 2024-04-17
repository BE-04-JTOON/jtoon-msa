package shop.jtoon.payment.presentation;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import shop.jtoon.annotation.CurrentUser;
import shop.jtoon.member.dto.MemberDto;
import shop.jtoon.payment.application.PaymentService;
import shop.jtoon.payment.request.CancelReq;
import shop.jtoon.payment.request.ConditionReq;
import shop.jtoon.payment.request.PaymentReq;
import shop.jtoon.payment.response.PaymentRes;

@RestController
@RequiredArgsConstructor
@RequestMapping("/payments")
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping("/validation")
    @ResponseStatus(HttpStatus.CREATED)
    public BigDecimal validatePayment(@RequestBody @Valid PaymentReq paymentReq, @CurrentUser MemberDto memberDto) {
        return paymentService.validateAndCreatePayment(paymentReq, memberDto);
    }

    @PostMapping("/cancel")
    @ResponseStatus(HttpStatus.OK)
    public void cancelPayment(@RequestBody @Valid CancelReq cancelReq) {
        paymentService.cancelPayment(cancelReq);
    }

    @PostMapping("/search")
    public List<PaymentRes> getPayments(@RequestBody ConditionReq conditionReq, @CurrentUser MemberDto memberDto) {
        return paymentService.getPayments(conditionReq, memberDto);
    }
}

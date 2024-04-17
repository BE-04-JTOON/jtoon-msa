package shop.jtoon.payment.application;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import shop.jtoon.member.dto.MemberDto;
import shop.jtoon.member.entity.Member;
import shop.jtoon.member.service.MemberService;
import shop.jtoon.payment.domain.CancelPayInfo;
import shop.jtoon.payment.domain.Item;
import shop.jtoon.payment.domain.Pay;
import shop.jtoon.payment.domain.Receipt;
import shop.jtoon.payment.request.CancelReq;
import shop.jtoon.payment.request.ConditionReq;
import shop.jtoon.payment.request.PaymentReq;
import shop.jtoon.payment.response.PaymentRes;
import shop.jtoon.payment.service.PayService;
import shop.jtoon.service.IamportService;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final IamportService iamportService;
    private final MemberService memberService;
    private final PayService payService;

    public BigDecimal validateAndCreatePayment(PaymentReq paymentReq, MemberDto memberDto) {
        Pay pay = paymentReq.toPay();
        Item item = paymentReq.toItem();

        Member member = memberService.read(memberDto.email());
        iamportService.validateIamport(pay.impUid(), item.amount());
        payService.createPaymentInfo(pay, item, member);

        return paymentReq.amount();
    }

    public void cancelPayment(CancelReq cancelReq) {
        CancelPayInfo cancelPayInfo = cancelReq.cancelPayInfo();

        iamportService.validateIamport(cancelPayInfo.impUid(), cancelPayInfo.checksum());
        iamportService.cancelIamport(
            cancelPayInfo.impUid(),
            cancelPayInfo.reason(),
            cancelPayInfo.checksum(),
            cancelPayInfo.refundHolder()
        );
    }

    public List<PaymentRes> getPayments(ConditionReq conditionReq, MemberDto memberDto) {
        List<Receipt> paymentInfos =  payService.readPayments(conditionReq.merchantsUid(), memberDto.email());

        return PaymentRes.of(paymentInfos);
    }
}

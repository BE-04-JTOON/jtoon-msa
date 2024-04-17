package shop.jtoon.payment.request;

import java.util.List;

import lombok.Builder;

@Builder
public record ConditionReq(
        List<String> merchantsUid
) {
}

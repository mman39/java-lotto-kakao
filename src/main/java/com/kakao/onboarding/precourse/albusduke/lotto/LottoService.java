package com.kakao.onboarding.precourse.albusduke.lotto;

public class LottoService {
    private static  final int LOTTO_COST = 1_000;
    public int savePurchaseAmount(PurchaseAmountDto dto) {
        if (dto.purchaseAmount() % LOTTO_COST != 0) {
            throw new IllegalArgumentException("로또 구매 단위는 " + LOTTO_COST + "원 단위여야 합니다.");
        }
        return dto.purchaseAmount() / LOTTO_COST;
    }
}

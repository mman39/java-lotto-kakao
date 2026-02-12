package com.kakao.onboarding.precourse.albusduke.lotto.domain;

import java.util.List;

public class ManualPurchaseGames {
    private final List<Integer> games;

    public ManualPurchaseGames(List<Integer> manualPurchaseGames) {
        this.games = manualPurchaseGames;
    }

    public List<Integer> getManualPurchaseGames() {
        return games;
    }
}

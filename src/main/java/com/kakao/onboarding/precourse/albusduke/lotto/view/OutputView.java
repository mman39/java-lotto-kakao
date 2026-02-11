package com.kakao.onboarding.precourse.albusduke.lotto.view;

import com.kakao.onboarding.precourse.albusduke.lotto.domain.PurchaseGameAmount;
import com.kakao.onboarding.precourse.albusduke.lotto.domain.Statistics;
import com.kakao.onboarding.precourse.albusduke.lotto.domain.LottoGames;
import com.kakao.onboarding.precourse.albusduke.lotto.util.Console;

public class OutputView {

    private final Console console;

    public OutputView(Console console) {
        this.console = console;
    }

    public void outputPurchaseGameAmount(PurchaseGameAmount purchasedGameAmount) {
        console.outputPurchaseGameAmount(purchasedGameAmount);
    }

    public void outputLottoNumbers(LottoGames lottoGames) {
        console.outputLottoNumbers(lottoGames);
    }

    public void outputStatistics(Statistics statistics) {
        console.outputStatistics(statistics);
    }

    public void outputError(IllegalArgumentException e) {
        console.outputError(e);
    }
}

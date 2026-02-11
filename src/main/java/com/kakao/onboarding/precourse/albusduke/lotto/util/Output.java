package com.kakao.onboarding.precourse.albusduke.lotto.util;

import com.kakao.onboarding.precourse.albusduke.lotto.domain.PurchaseGameAmount;
import com.kakao.onboarding.precourse.albusduke.lotto.domain.Statistics;
import com.kakao.onboarding.precourse.albusduke.lotto.domain.LottoGames;

public interface Output {

    void outputPurchaseGameAmount(PurchaseGameAmount purchasedGameAmount);

    void outputLottoNumbers(LottoGames lottoGames);

    void outputStatistics(Statistics statistics);

    void outputError(IllegalArgumentException e);
}

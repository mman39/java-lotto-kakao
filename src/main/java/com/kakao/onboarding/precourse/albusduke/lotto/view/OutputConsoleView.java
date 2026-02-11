package com.kakao.onboarding.precourse.albusduke.lotto.view;

import com.kakao.onboarding.precourse.albusduke.lotto.domain.Prize;
import com.kakao.onboarding.precourse.albusduke.lotto.domain.PurchaseGameAmount;
import com.kakao.onboarding.precourse.albusduke.lotto.domain.Statistics;
import com.kakao.onboarding.precourse.albusduke.lotto.domain.WinningPrizes;
import com.kakao.onboarding.precourse.albusduke.lotto.domain.LottoGames;
import com.kakao.onboarding.precourse.albusduke.lotto.domain.LottoNumber;
import com.kakao.onboarding.precourse.albusduke.lotto.domain.LottoNumbers;
import com.kakao.onboarding.precourse.albusduke.lotto.util.Output;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringJoiner;

public class OutputConsoleView {

    private static final String PURCHASE_COUNT_FORMAT = "%d개를 구매했습니다.\n";
    private static final String STATISTICS_PREFIX = "당첨 통계\n-------------";
    private static final String MATCHING_FORMAT = "%d개 일치";
    private static final String REWARD_FORMAT = "(%d원)";
    private static final String BONUS_MATCHING_FORMAT = "보너스 볼 일치";
    private static final String COUNT_FORMAT = "%d개";
    private static final String RATIO_FORMAT = "총 수익률은 %.2f입니다.(기준이 1이기 때문에 결과적으로 손해라는 의미임)\n";
    private static final String ERROR_PREFIX = "[ERROR] ";

    private final Output output;

    public OutputConsoleView(Output output) {
        this.output = output;
    }

    public void outputPurchaseGameAmount(PurchaseGameAmount purchasedGameAmount) {
        output.printf(PURCHASE_COUNT_FORMAT, purchasedGameAmount.count());
    }

    public void outputLottoNumbers(LottoGames lottoGames) {
        for (LottoNumbers game : lottoGames.games()) {
            StringJoiner sj =  new StringJoiner(", ", "[", "]");
            List<LottoNumber> sortedLottoNumbers = new ArrayList<>(game.getLottoNumbers());
            Collections.sort(sortedLottoNumbers);
            for (LottoNumber number : sortedLottoNumbers) {
                sj.add(String.valueOf(number.getNumber()));
            }
            output.println(sj.toString());
        }
    }

    public void outputStatistics(Statistics statistics) {
        WinningPrizes winningPrizes = statistics.winningPrizes();
        output.println(STATISTICS_PREFIX);
        output.println(createOutput(Prize.FIFTH, winningPrizes.getCounts().getOrDefault(Prize.FIFTH, 0)));
        output.println(createOutput(Prize.FORTH, winningPrizes.getCounts().getOrDefault(Prize.FORTH, 0)));
        output.println(createOutput(Prize.THIRD, winningPrizes.getCounts().getOrDefault(Prize.THIRD, 0)));
        output.println(createOutput(Prize.SECOND, winningPrizes.getCounts().getOrDefault(Prize.SECOND, 0)));
        output.println(createOutput(Prize.FIRST, winningPrizes.getCounts().getOrDefault(Prize.FIRST, 0)));
        output.printf(RATIO_FORMAT, statistics.ratio());
    }

    private String createOutput(Prize prize, int count) {
        return String.format(MATCHING_FORMAT, prize.getMatchingCount()) +
                (prize.hasBonusMatch() ? (", " + BONUS_MATCHING_FORMAT) : "") +
                String.format(REWARD_FORMAT, prize.getReward()) +
                " - " +
                String.format(COUNT_FORMAT, count);
    }

    public void outputError(IllegalArgumentException e) {
        output.println(ERROR_PREFIX + e.getMessage());
    }
}

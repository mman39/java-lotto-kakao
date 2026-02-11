package com.kakao.onboarding.precourse.albusduke.lotto.util;

import com.kakao.onboarding.precourse.albusduke.lotto.domain.Prize;
import com.kakao.onboarding.precourse.albusduke.lotto.domain.PurchaseGameAmount;
import com.kakao.onboarding.precourse.albusduke.lotto.domain.Statistics;
import com.kakao.onboarding.precourse.albusduke.lotto.domain.WinningPrizes;
import com.kakao.onboarding.precourse.albusduke.lotto.domain.LottoGames;
import com.kakao.onboarding.precourse.albusduke.lotto.domain.LottoNumber;
import com.kakao.onboarding.precourse.albusduke.lotto.domain.LottoNumbers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.StringJoiner;

public class Console implements Input, Output {
    private static final String NUMBER_FORMAT_ERROR_MSG = "숫자 형식이어야 합니다.";
    
    private static final String PURCHASE_COUNT_FORMAT = "%d개를 구매했습니다.\n";
    private static final String STATISTICS_PREFIX = "당첨 통계\n-------------";
    private static final String MATCHING_FORMAT = "%d개 일치";
    private static final String REWARD_FORMAT = "(%d원)";
    private static final String BONUS_MATCHING_FORMAT = "보너스 볼 일치";
    private static final String COUNT_FORMAT = "%d개";
    private static final String RATIO_FORMAT = "총 수익률은 %.2f입니다.(기준이 1이기 때문에 결과적으로 손해라는 의미임)\n";
    private static final String ERROR_PREFIX = "[ERROR] ";

    private final Scanner scanner = new Scanner(System.in);

    @Override
    public String readNext() {
        return scanner.nextLine().trim();
    }

    @Override
    public int readInt() {
        try {
            String next = readNext();
            return Integer.parseInt(next.trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(NUMBER_FORMAT_ERROR_MSG);
        }
    }

    private void output(String message) {
        System.out.println(message);
    }

    @Override
    public void outputPurchaseGameAmount(PurchaseGameAmount purchasedGameAmount) {
        output(String.format(PURCHASE_COUNT_FORMAT, purchasedGameAmount.count()));
    }

    @Override
    public void outputLottoNumbers(LottoGames lottoGames) {
        for (LottoNumbers game : lottoGames.games()) {
            StringJoiner sj = new StringJoiner(", ", "[", "]");
            List<LottoNumber> sortedLottoNumbers = new ArrayList<>(game.getLottoNumbers());
            Collections.sort(sortedLottoNumbers);
            for (LottoNumber number : sortedLottoNumbers) {
                sj.add(String.valueOf(number.getNumber()));
            }
            output(sj.toString());
        }
    }

    @Override
    public void outputStatistics(Statistics statistics) {
        WinningPrizes winningPrizes = statistics.winningPrizes();
        output(STATISTICS_PREFIX);
        output(formatPrizeOutput(Prize.FIFTH, winningPrizes.getCounts().getOrDefault(Prize.FIFTH, 0)));
        output(formatPrizeOutput(Prize.FORTH, winningPrizes.getCounts().getOrDefault(Prize.FORTH, 0)));
        output(formatPrizeOutput(Prize.THIRD, winningPrizes.getCounts().getOrDefault(Prize.THIRD, 0)));
        output(formatPrizeOutput(Prize.SECOND, winningPrizes.getCounts().getOrDefault(Prize.SECOND, 0)));
        output(formatPrizeOutput(Prize.FIRST, winningPrizes.getCounts().getOrDefault(Prize.FIRST, 0)));
        output(String.format(RATIO_FORMAT, statistics.ratio()));
    }

    private String formatPrizeOutput(Prize prize, int count) {
        return String.format(MATCHING_FORMAT, prize.getMatchingCount()) +
                (prize.hasBonusMatch() ? (", " + BONUS_MATCHING_FORMAT) : "") +
                String.format(REWARD_FORMAT, prize.getReward()) +
                " - " +
                String.format(COUNT_FORMAT, count);
    }

    @Override
    public void outputError(IllegalArgumentException e) {
        output(ERROR_PREFIX + e.getMessage());
    }
}

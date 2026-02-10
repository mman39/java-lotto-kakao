package com.kakao.onboarding.precourse.albusduke.lotto;

public class OutputConsoleView {

    private static final String MATCHING_FORMAT = "%d개 일치";
    private static final String REWARD_FORMAT = "(%d원)";
    private static final String BONUS_MATCHING_FORMAT = ", 보너스 볼 일치";
    private static final String COUNT_FORMAT = "%d개";

    public void outputStatistics(Statistics statistics) {
        System.out.println("당첨 통계");
        System.out.println("-------------");
        System.out.println(createOutput(Prize.FIFTH, statistics.counts().getOrDefault(Prize.FIFTH, 0)));
        System.out.println(createOutput(Prize.FORTH, statistics.counts().getOrDefault(Prize.FORTH, 0)));
        System.out.println(createOutput(Prize.THIRD, statistics.counts().getOrDefault(Prize.THIRD, 0)));
        System.out.println(createOutput(Prize.SECOND, statistics.counts().getOrDefault(Prize.SECOND, 0)));
        System.out.println(createOutput(Prize.FIRST, statistics.counts().getOrDefault(Prize.FIRST, 0)));
        System.out.printf("총 수익률은 %02f입니다.(기준이 1이기 때문에 결과적으로 손해라는 의미임\n", statistics.ratio());
    }

    private String createOutput(Prize prize, int count) {
        return String.format(MATCHING_FORMAT, prize.getMatchingCount()) +
                (prize.getBonusMatchingCount() != 0 ? (" " + BONUS_MATCHING_FORMAT) : "") +
                REWARD_FORMAT +
                " - " +
                String.format(COUNT_FORMAT, count);
    }

    public void outputPurchaseAmount(int countLotto) {
        System.out.println(countLotto + "개를 구매했습니다.");
    }
}

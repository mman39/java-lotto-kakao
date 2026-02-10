package com.kakao.onboarding.precourse.albusduke.lotto;

import java.util.HashMap;
import java.util.Map;

public class PrizeStore {

    private final Map<Prize, Integer> counts = new HashMap<>();

    public void addGameResult(GameResult gameResult) {
        Prize prize = Prize.of(gameResult);
        counts.put(prize, counts.getOrDefault(prize, 0) + 1);
    }

    public Statistics calculateStatistics() {
        int gameCount = 0;
        long totalReward = 0L;
        for (Prize prize : Prize.values()) {
            gameCount += counts.getOrDefault(prize, 0);
            totalReward += counts.getOrDefault(prize, 0) * prize.getReward();
        }

        double ratio = totalReward / ((double)gameCount * 1000);

        return new Statistics(counts, ratio);
    }

    public Map<Prize, Integer> getCounts() {
        return counts;
    }
}

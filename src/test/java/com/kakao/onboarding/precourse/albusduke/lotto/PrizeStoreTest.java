package com.kakao.onboarding.precourse.albusduke.lotto;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class PrizeStoreTest {

    private PrizeStore prizeStore;

    @BeforeEach
    void setUp() {
        prizeStore = new PrizeStore();
    }

    @Test
    void GameResult_를_추가할_수_있다() {
        prizeStore.addGameResult(new GameResult(6, 0));
        assertThat(prizeStore.getCounts().get(Prize.FIRST)).isEqualTo(1);
        assertThat(prizeStore.getCounts().getOrDefault(Prize.SECOND, 0)).isEqualTo(0);
        assertThat(prizeStore.getCounts().getOrDefault(Prize.NONE, 0)).isEqualTo(0);

        prizeStore.addGameResult(new GameResult(6, 0));
        assertThat(prizeStore.getCounts().get(Prize.FIRST)).isEqualTo(2);
        assertThat(prizeStore.getCounts().getOrDefault(Prize.SECOND, 0)).isEqualTo(0);
        assertThat(prizeStore.getCounts().getOrDefault(Prize.NONE, 0)).isEqualTo(0);

        prizeStore.addGameResult(new GameResult(5, 1));
        assertThat(prizeStore.getCounts().get(Prize.FIRST)).isEqualTo(2);
        assertThat(prizeStore.getCounts().get(Prize.SECOND)).isEqualTo(1);
        assertThat(prizeStore.getCounts().getOrDefault(Prize.NONE, 0)).isEqualTo(0);

        prizeStore.addGameResult(new GameResult(2, 1));
        assertThat(prizeStore.getCounts().get(Prize.FIRST)).isEqualTo(2);
        assertThat(prizeStore.getCounts().get(Prize.SECOND)).isEqualTo(1);
        assertThat(prizeStore.getCounts().get(Prize.NONE)).isEqualTo(1);
    }

    @Test
    void Statistics_를_계산할_수_있다() {
        prizeStore.addGameResult(new GameResult(6, 0));
        assertThat(prizeStore.calculateStatistics()).isEqualTo(new Statistics(prizeStore.getCounts(), (double)Prize.FIRST.getReward() / 1000));

        prizeStore.addGameResult(new GameResult(2, 0));
        assertThat(prizeStore.calculateStatistics()).isEqualTo(new Statistics(prizeStore.getCounts(), (double)Prize.FIRST.getReward() / 2000));

        prizeStore.addGameResult(new GameResult(0, 0));
        assertThat(prizeStore.calculateStatistics()).isEqualTo(new Statistics(prizeStore.getCounts(), (double)Prize.FIRST.getReward() / 3000));
    }
}

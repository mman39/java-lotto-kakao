package com.kakao.onboarding.precourse.albusduke.lotto;

import com.kakao.onboarding.precourse.albusduke.lotto.domain.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class WinningPrizesTest {

    private WinningPrizes winningPrizes;

    @BeforeEach
    void setUp() {
        LottoNumbers lottoNumbers = new LottoNumbers(List.of(1, 2, 3, 4, 5, 6));
        LottoNumber lottoNumber = new LottoNumber(7);
        WinningNumbers winningNumbers = new WinningNumbers(lottoNumbers, lottoNumber);
        LottoGames lottoGames = new LottoGames(List.of(
                new LottoNumbers(List.of(1, 2, 3, 4, 5, 6)),
                new LottoNumbers(List.of(1, 2, 3, 4, 5, 7)),
                new LottoNumbers(List.of(1, 2, 3, 4, 5, 8))
        ));
        winningPrizes = new WinningPrizes(winningNumbers, lottoGames);
    }

    @Test
    void 주어진_WinningNumbers_와_LottoGames_에_대해_당첨_수를_계산할_수_있다() {
        assertThat(winningPrizes.getCounts().get(Prize.FIRST)).isEqualTo(1);
        assertThat(winningPrizes.getCounts().get(Prize.SECOND)).isEqualTo(1);
        assertThat(winningPrizes.getCounts().getOrDefault(Prize.NONE, 0)).isEqualTo(0);
//
//        prizeStore.addGameResult(new GameResult(6, 0));
//        assertThat(prizeStore.getCounts().get(Prize.FIRST)).isEqualTo(2);
//        assertThat(prizeStore.getCounts().getOrDefault(Prize.SECOND, 0)).isEqualTo(0);
//        assertThat(prizeStore.getCounts().getOrDefault(Prize.NONE, 0)).isEqualTo(0);
//
//        prizeStore.addGameResult(new GameResult(5, 1));
//        assertThat(prizeStore.getCounts().get(Prize.FIRST)).isEqualTo(2);
//        assertThat(prizeStore.getCounts().get(Prize.SECOND)).isEqualTo(1);
//        assertThat(prizeStore.getCounts().getOrDefault(Prize.NONE, 0)).isEqualTo(0);
//
//        prizeStore.addGameResult(new GameResult(2, 1));
//        assertThat(prizeStore.getCounts().get(Prize.FIRST)).isEqualTo(2);
//        assertThat(prizeStore.getCounts().get(Prize.SECOND)).isEqualTo(1);
//        assertThat(prizeStore.getCounts().get(Prize.NONE)).isEqualTo(1);
    }

//    @Test
//    void Statistics_를_계산할_수_있다() {
//        prizeStore.addGameResult(new GameResult(6, 0));
//        assertThat(prizeStore.calculateStatistics()).isEqualTo(new Statistics(prizeStore.getCounts(), (double)Prize.FIRST.getReward() / 1000));
//
//        prizeStore.addGameResult(new GameResult(2, 0));
//        assertThat(prizeStore.calculateStatistics()).isEqualTo(new Statistics(prizeStore.getCounts(), (double)Prize.FIRST.getReward() / 2000));
//
//        prizeStore.addGameResult(new GameResult(0, 0));
//        assertThat(prizeStore.calculateStatistics()).isEqualTo(new Statistics(prizeStore.getCounts(), (double)Prize.FIRST.getReward() / 3000));
//    }
}

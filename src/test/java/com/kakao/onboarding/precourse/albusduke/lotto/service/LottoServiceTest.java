package com.kakao.onboarding.precourse.albusduke.lotto.service;

import com.kakao.onboarding.precourse.albusduke.lotto.domain.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class LottoServiceTest {

    private LottoService lottoService;
    private FixedLottoNumbersGenerator fixedGenerator;

    @BeforeEach
    void setUp() {
        fixedGenerator = new FixedLottoNumbersGenerator();
        lottoService = new LottoService(fixedGenerator);
    }

    @Test
    void 구매_금액으로_Money를_생성할_수_있다() {
        PurchaseAmount purchaseAmount = new PurchaseAmount(5000);

        Money result = lottoService.createMoneyBy(purchaseAmount);

        assertThat(result.getAmount()).isEqualTo(5000);
    }

    @Test
    void 구매_금액_0원으로_Money를_생성할_수_있다() {
        PurchaseAmount purchaseAmount = new PurchaseAmount(0);

        Money result = lottoService.createMoneyBy(purchaseAmount);

        assertThat(result.getAmount()).isEqualTo(0);
    }

    @Test
    void 금액과_수동_개수로_티켓_수량을_계산할_수_있다() {
        Money money = new Money(5000);
        ManualTicketQuantity manualTicketQuantity = new ManualTicketQuantity(2);

        TicketQuantity result = lottoService.calculateTicketQuantity(money, manualTicketQuantity);

        assertThat(result.getRandomQuantity()).isEqualTo(3);
        assertThat(result.getManualQuantity()).isEqualTo(2);
    }

    @Test
    void 금액이_1000원이고_수동_1장이면_자동_0장_수동_1장이다() {
        Money money = new Money(1000);
        ManualTicketQuantity manualTicketQuantity = new ManualTicketQuantity(1);

        TicketQuantity result = lottoService.calculateTicketQuantity(money, manualTicketQuantity);

        assertThat(result.getRandomQuantity()).isEqualTo(0);
        assertThat(result.getManualQuantity()).isEqualTo(1);
    }

    @Test
    void 금액이_3000원이고_수동_0장이면_자동_3장_수동_0장이다() {
        Money money = new Money(3000);
        ManualTicketQuantity manualTicketQuantity = new ManualTicketQuantity(0);

        TicketQuantity result = lottoService.calculateTicketQuantity(money, manualTicketQuantity);

        assertThat(result.getRandomQuantity()).isEqualTo(3);
        assertThat(result.getManualQuantity()).isEqualTo(0);
    }

    @Test
    void purchaseLottoGame은_생성기를_지정된_횟수만큼_호출한다() {
        TicketQuantity ticketQuantity = new TicketQuantity(3, 0);
        ManualTickets manualTickets = new ManualTickets(List.of());
        fixedGenerator.setFixedLottoNumbers(new LottoNumbers(List.of(1, 2, 3, 4, 5, 6)));

        LottoGames result = lottoService.purchaseLottoGame(ticketQuantity, manualTickets);

        assertThat(fixedGenerator.getCallCount()).isEqualTo(3);
        assertThat(result.games()).hasSize(3);
    }

    @Test
    void purchaseLottoGame은_생성된_로또_번호들을_LottoGames로_반환한다() {
        TicketQuantity ticketQuantity = new TicketQuantity(2, 0);
        ManualTickets manualTickets = new ManualTickets(List.of());
        LottoNumbers lottoNumbers1 = new LottoNumbers(List.of(1, 2, 3, 4, 5, 6));
        LottoNumbers lottoNumbers2 = new LottoNumbers(List.of(7, 8, 9, 10, 11, 12));
        fixedGenerator.setLottoNumbersSequence(List.of(lottoNumbers1, lottoNumbers2));

        LottoGames result = lottoService.purchaseLottoGame(ticketQuantity, manualTickets);

        assertThat(result.games()).hasSize(2);
        assertThat(result.games().get(0)).isEqualTo(lottoNumbers1);
        assertThat(result.games().get(1)).isEqualTo(lottoNumbers2);
    }

    @Test
    void purchaseLottoGame은_0개의_자동_게임을_요청하면_수동_개수만큼_생성한다() {
        TicketQuantity ticketQuantity = new TicketQuantity(0, 2);
        ManualTickets manualTickets = new ManualTickets(List.of(1, 2));
        fixedGenerator.setFixedLottoNumbers(new LottoNumbers(List.of(1, 2, 3, 4, 5, 6)));

        LottoGames result = lottoService.purchaseLottoGame(ticketQuantity, manualTickets);

        assertThat(fixedGenerator.getCallCount()).isEqualTo(2);
        assertThat(result.games()).hasSize(2);
    }

    @Test
    void purchaseLottoGame은_자동과_수동_개수를_합쳐_LottoGames로_반환한다() {
        TicketQuantity ticketQuantity = new TicketQuantity(1, 1);
        ManualTickets manualTickets = new ManualTickets(List.of(1));
        LottoNumbers auto = new LottoNumbers(List.of(1, 2, 3, 4, 5, 6));
        LottoNumbers manual = new LottoNumbers(List.of(7, 8, 9, 10, 11, 12));
        fixedGenerator.setLottoNumbersSequence(List.of(auto, manual));

        LottoGames result = lottoService.purchaseLottoGame(ticketQuantity, manualTickets);

        assertThat(result.games()).hasSize(2);
        assertThat(result.games().get(0)).isEqualTo(auto);
        assertThat(result.games().get(1)).isEqualTo(manual);
    }

    @Test
    void purchaseLottoGame은_0개의_게임을_요청하면_빈_LottoGames를_반환한다() {
        TicketQuantity ticketQuantity = new TicketQuantity(0, 0);
        ManualTickets manualTickets = new ManualTickets(List.of());

        LottoGames result = lottoService.purchaseLottoGame(ticketQuantity, manualTickets);

        assertThat(fixedGenerator.getCallCount()).isEqualTo(0);
        assertThat(result.games()).isEmpty();
    }

    @Test
    void purchaseLottoGame은_여러_번_호출해도_정상적으로_동작한다() {
        TicketQuantity ticketQuantity = new TicketQuantity(2, 0);
        ManualTickets manualTickets = new ManualTickets(List.of());
        fixedGenerator.setFixedLottoNumbers(new LottoNumbers(List.of(1, 2, 3, 4, 5, 6)));

        LottoGames result1 = lottoService.purchaseLottoGame(ticketQuantity, manualTickets);
        LottoGames result2 = lottoService.purchaseLottoGame(ticketQuantity, manualTickets);

        assertThat(result1.games()).hasSize(2);
        assertThat(result2.games()).hasSize(2);
        assertThat(fixedGenerator.getCallCount()).isEqualTo(4);
    }

    /**
     * 테스트용 고정값을 반환하는 LottoNumbersGenerator 구현체
     */
    private static class FixedLottoNumbersGenerator implements LottoNumbersGenerator {
        private LottoNumbers fixedLottoNumbers;
        private List<LottoNumbers> lottoNumbersSequence;
        private int callCount = 0;
        private int sequenceIndex = 0;

        public void setFixedLottoNumbers(LottoNumbers lottoNumbers) {
            this.fixedLottoNumbers = lottoNumbers;
            this.lottoNumbersSequence = null;
            this.sequenceIndex = 0;
        }

        public void setLottoNumbersSequence(List<LottoNumbers> sequence) {
            this.lottoNumbersSequence = new ArrayList<>(sequence);
            this.fixedLottoNumbers = null;
            this.sequenceIndex = 0;
        }

        @Override
        public LottoNumbers generate() {
            callCount++;

            if (lottoNumbersSequence != null && !lottoNumbersSequence.isEmpty()) {
                LottoNumbers result = lottoNumbersSequence.get(sequenceIndex % lottoNumbersSequence.size());
                sequenceIndex++;
                return result;
            }

            if (fixedLottoNumbers != null) {
                return fixedLottoNumbers;
            }

            return new LottoNumbers(List.of(1, 2, 3, 4, 5, 6));
        }

        public int getCallCount() {
            return callCount;
        }
    }
}

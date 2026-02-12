package com.kakao.onboarding.precourse.albusduke.lotto.service;

import com.kakao.onboarding.precourse.albusduke.lotto.domain.PurchaseAmount;
import com.kakao.onboarding.precourse.albusduke.lotto.domain.PurchaseGameAmount;
import com.kakao.onboarding.precourse.albusduke.lotto.domain.TicketQuantity;
import com.kakao.onboarding.precourse.albusduke.lotto.domain.LottoGames;
import com.kakao.onboarding.precourse.albusduke.lotto.domain.LottoNumbers;
import com.kakao.onboarding.precourse.albusduke.lotto.domain.LottoNumbersGenerator;
import com.kakao.onboarding.precourse.albusduke.lotto.domain.ManualTicketQuantity;
import com.kakao.onboarding.precourse.albusduke.lotto.domain.ManualTickets;
import com.kakao.onboarding.precourse.albusduke.lotto.domain.Money;

import java.util.List;
import java.util.stream.IntStream;

public class LottoService {

    private static final int LOTTO_COST = 1_000;

    private final LottoNumbersGenerator lottoNumbersGenerator;

    public LottoService(LottoNumbersGenerator lottoNumbersGenerator) {
        this.lottoNumbersGenerator =  lottoNumbersGenerator;
    }

    public Money createMoneyBy(PurchaseAmount purchaseAmount) {
        return new Money(purchaseAmount.purchaseAmount());
    }

    public TicketQuantity calculateTicketQuantity(Money money, ManualTicketQuantity manualTicketQuantity) {
        Money lottoCost = new Money(LOTTO_COST);
        int totalQuantity = money.divide(lottoCost);
        int manualQuantity = manualTicketQuantity.getQuantity();
        int randomQuantity = totalQuantity - manualQuantity;
        return new TicketQuantity(randomQuantity, manualQuantity);
    }

    public LottoGames purchaseLottoGame(TicketQuantity ticketQuantity, ManualTickets manualTickets) {
        List<LottoNumbers> lottoNumbers = IntStream.range(0, ticketQuantity.getRandomQuantity())
                .mapToObj(i -> lottoNumbersGenerator.generate())
                .toList();
        List<LottoNumbers> manualLottoNumbers = manualTickets.getTicketNumbers().stream()
                .map(i -> lottoNumbersGenerator.generate())
                .toList();
        lottoNumbers.addAll(manualLottoNumbers);
        return new LottoGames(lottoNumbers);
    }
}

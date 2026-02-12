package com.kakao.onboarding.precourse.albusduke.lotto.controller;

import com.kakao.onboarding.precourse.albusduke.lotto.domain.*;
import com.kakao.onboarding.precourse.albusduke.lotto.service.LottoService;
import com.kakao.onboarding.precourse.albusduke.lotto.service.StatisticsService;
import com.kakao.onboarding.precourse.albusduke.lotto.view.InputView;
import com.kakao.onboarding.precourse.albusduke.lotto.view.OutputView;

public class LottoController {

    private final InputView inputConsoleView;
    private final OutputView outputConsoleView;

    private final LottoService lottoService;
    private final StatisticsService statisticsService;

    public LottoController(InputView inputConsoleView, OutputView outputConsoleView, LottoService lottoService, StatisticsService statisticsService) {
        this.inputConsoleView = inputConsoleView;
        this.outputConsoleView = outputConsoleView;
        this.lottoService = lottoService;
        this.statisticsService = statisticsService;
    }

    public void runLottoGame() {
        TicketQuantity ticketQuantity = calculateTicketQuantity();
        LottoGames lottoGames = purchaseLottoGame(ticketQuantity);
        WinningNumbers winningNumbers = createWinningNumbers();
        calculateStatistics(winningNumbers, lottoGames);
    }

    public TicketQuantity calculateTicketQuantity() {
        try {
            PurchaseAmount purchaseAmount = inputConsoleView.inputPurchaseAmount();
            Money money = lottoService.createMoneyBy(purchaseAmount);
            ManualTicketQuantity manualTicketQuantity = inputConsoleView.inputManualTicketQuantity();
            TicketQuantity ticketQuantity = lottoService.calculateTicketQuantity(money, manualTicketQuantity);
            return ticketQuantity;
        } catch (IllegalArgumentException e) {
            outputConsoleView.outputError(e);
            return calculateTicketQuantity();
        }
    }

    public LottoGames purchaseLottoGame(TicketQuantity ticketQuantity) {
        try {
            ManualTickets manualTickets = inputConsoleView.inputManualTickets();
            LottoGames lottoGames = lottoService.purchaseLottoGame(ticketQuantity, manualTickets);
            outputConsoleView.outputLottoNumbers(lottoGames);
            return lottoGames;
        } catch (IllegalArgumentException e) {
            outputConsoleView.outputError(e);
            return purchaseLottoGame(ticketQuantity);
        }
    }

    public WinningNumbers createWinningNumbers() {
        try {
            return inputConsoleView.inputWinningNumbers();
        } catch (IllegalArgumentException e) {
            outputConsoleView.outputError(e);
            return createWinningNumbers();
        }
    }

    public void calculateStatistics(WinningNumbers winningNumbers, LottoGames lottoGames) {
        Statistics statistics = statisticsService.calculateStatistics(winningNumbers, lottoGames);
        outputConsoleView.outputStatistics(statistics);
    }
}

package com.kakao.onboarding.precourse.albusduke.lotto;

import java.util.ArrayList;
import java.util.List;

public class LottoController {

    private final InputConsoleView inputConsoleView;
    private final OutputConsoleView outputConsoleView;

    private final LottoService lottoService;

    public LottoController(InputConsoleView inputConsoleView, OutputConsoleView outputConsoleView, LottoService lottoService) {
        this.inputConsoleView = inputConsoleView;
        this.outputConsoleView = outputConsoleView;
        this.lottoService = lottoService;
    }

    public int savePurchaseAmount() {
        PurchaseAmountDto purchaseAmountDto;
        try {
            purchaseAmountDto = inputConsoleView.inputPurchaseAmount();
            int countLotto = lottoService.savePurchaseAmount(purchaseAmountDto);
            outputConsoleView.outputPurchaseAmount(new PurchasedLottoCountDto(countLotto));
            return countLotto;
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return savePurchaseAmount();
        }
    }

    public List<LottoNumbers> purchaseLotto(int purchaseAmount) {
        List<LottoNumbers> lottoGames = lottoService.buyLottoGame(purchaseAmount);
        outputConsoleView.outputLottoNumbers(new LottoNumberListDto(lottoGames));
        return lottoGames;
    }

    public void statistics(List<LottoNumbers> lottoNumbersList) {
        PreviousWinningNumbersDto dto = inputConsoleView.inputPreviousWinningNumbers();
        WinningNumbers winningNumbers = new WinningNumbers(new LottoNumbers(dto.winingNumbers()), new LottoNumber(dto.bonusNumber()));
        PrizeStore prizeStore = new PrizeStore();
        for (LottoNumbers lottoNumbers : lottoNumbersList) {
            prizeStore.addGameResult(winningNumbers.calculateResult(lottoNumbers));
        }
        Statistics statistics = prizeStore.calculateStatistics();
        outputConsoleView.outputStatistics(statistics);
    }
}

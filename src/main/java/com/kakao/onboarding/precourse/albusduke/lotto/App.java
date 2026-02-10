package com.kakao.onboarding.precourse.albusduke.lotto;

import java.util.List;

public class App {
    public static void main(String[] args) {
        LottoController lottoController = createLottoController();
        runLottoGame(lottoController);
    }

    private static LottoController createLottoController() {
        OutputConsoleView outputConsoleView = new OutputConsoleView();
        InputConsoleView inputConsoleView = new InputConsoleView();
        LottoService lottoService  = new LottoService();
        return new LottoController(inputConsoleView, outputConsoleView, lottoService);
    }

    private static void runLottoGame(LottoController lottoController) {
        int purchaseAmount = lottoController.savePurchaseAmount();
        List<LottoNumbers> lottoGames = lottoController.purchaseLotto(purchaseAmount);
        lottoController.statistics(lottoGames);
    }
}

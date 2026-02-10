package com.kakao.onboarding.precourse.albusduke.lotto;

import java.util.List;

public class App {
    public static void main(String[] args) {

        OutputConsoleView outputConsoleView = new OutputConsoleView();
        InputConsoleView inputConsoleView = new InputConsoleView();
        LottoService lottoService  = new LottoService();
        LottoController lottoController = new LottoController(inputConsoleView, outputConsoleView, lottoService);

        while (true) {
            int purchaseAmount = lottoController.savePurchaseAmount();
            List<LottoNumbers> lottoNumbersList = lottoController.purchaseLotto(purchaseAmount);
            lottoController.statistics(lottoNumbersList);
        }
    }
}

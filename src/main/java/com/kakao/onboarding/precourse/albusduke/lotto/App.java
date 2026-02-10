package com.kakao.onboarding.precourse.albusduke.lotto;

public class App {
    public static void main(String[] args) {

        OutputConsoleView outputConsoleView = new OutputConsoleView();
        InputConsoleView inputConsoleView = new InputConsoleView();
        LottoService lottoService  = new LottoService();
        LottoController lottoController = new LottoController(inputConsoleView, outputConsoleView, lottoService);

        while (true) {
            int number = lottoController.savePurchaseAmount();
            lottoController.purchaseLotto();
            // lottoController.statistics();
        }
    }
}

package com.kakao.onboarding.precourse.albusduke.lotto;

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
            outputConsoleView.outputPurchaseAmount(countLotto);
            return countLotto;
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return savePurchaseAmount();
        }
    }

    public void purchaseLotto() {

    }
}

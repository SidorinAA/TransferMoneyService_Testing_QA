package Test;


import Data.DataHelper;
import Page.LoginPage;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.open;

public class TransferMoneyTest {

    @Test
    void shouldTransferMoney(){
        open("http://localhost:9999/");
        LoginPage loginPage = new LoginPage();
        loginPage
                .validLogin(DataHelper.getAuthInfo())
                .validVerify(DataHelper.getVerificationCodeFor(DataHelper.getAuthInfo()))
                .cardRefillButtonClickFirst()
                .transfer(DataHelper.Card.getCardInfo02(), 100);


    }




}

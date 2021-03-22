package ru.netology.Test;


import lombok.val;
import ru.netology.Data.DataHelper;
import ru.netology.Page.DashboardPage;
import ru.netology.Page.LoginPage;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TransferMoneyTest {

    @Test
    void shouldTransferMoney(){
        open("http://localhost:9999/");
        int amount = 1000;
        val loginPage = new LoginPage();
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        val dashboard = verificationPage.validVerify(verificationCode);
        val cardBalance01 = dashboard.getCardBalanceFirst();
        val cardBalance02 = dashboard.getCardBalanceSecond();
        val cardInfo = DataHelper.Card.getCardInfo01();
        val transferMoney = dashboard.cardRefillButtonClickSecond();
        transferMoney.transfer(cardInfo, amount);
        val cardBalanceAfterSend01 = DashboardPage.cardBalanceAfterSendMoney(cardBalance01, amount);
        val cardBalanceAfterSend02 = DashboardPage.cardBalanceAfterGetMoney(cardBalance02, amount);
        assertEquals(cardBalanceAfterSend01, dashboard.getCardBalanceFirst());
        assertEquals(cardBalanceAfterSend02, dashboard.getCardBalanceSecond());


    }

    @Test
    void shouldTransferMoneyFrom02To01Card() {
        open("http://localhost:9999/");
        int amount = 1500;
        val loginPage = new LoginPage();
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        val dashboard = verificationPage.validVerify(verificationCode);
        val cardBalance01 = dashboard.getCardBalanceFirst();
        val cardBalance02 = dashboard.getCardBalanceSecond();
        val cardInfo = DataHelper.Card.getCardInfo02();
        val transferMoney = dashboard.cardRefillButtonClickFirst();
        transferMoney.transfer(cardInfo, amount);
        val cardBalanceAfterSend02 = DashboardPage.cardBalanceAfterSendMoney(cardBalance02, amount);
        val cardBalanceAfterSend01 = DashboardPage.cardBalanceAfterGetMoney(cardBalance01, amount);
        assertEquals(cardBalanceAfterSend01, dashboard.getCardBalanceFirst());
        assertEquals(cardBalanceAfterSend02, dashboard.getCardBalanceSecond());
    }

    @Test
    void shouldReturnErrorIfSendMoreBalance() {
        open("http://localhost:9999/");
        int amount = 20000;
        val loginPage = new LoginPage();
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        val dashboard = verificationPage.validVerify(verificationCode);
        val cardInfo = DataHelper.Card.getCardInfo02();
        val transferMoney = dashboard.cardRefillButtonClickSecond();
        transferMoney.transfer(cardInfo, amount);
        transferMoney.errorMessage();
    }

    @Test
    void shouldReloadButtonWorked(){
        open("http://localhost:9999/");
        int amount = 3000;
        val loginPage = new LoginPage();
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        val dashboard = verificationPage.validVerify(verificationCode);
        val cardBalance01 = dashboard.getCardBalanceFirst();
        val cardBalance02 = dashboard.getCardBalanceSecond();
        val cardInfo = DataHelper.Card.getCardInfo01();
        val transferMoney = dashboard.cardRefillButtonClickSecond();
        transferMoney.transfer(cardInfo, amount);
        dashboard.setReload();
        assertEquals(10000, cardBalance01);
        assertEquals(10000, cardBalance02);

    }




}

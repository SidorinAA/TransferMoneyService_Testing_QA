package ru.netology.page;

import ru.netology.data.DataHelper;
import com.codeborne.selenide.SelenideElement;


import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;


public class LoginPage {

    private SelenideElement loginField = $("[type='text']");
    private SelenideElement passwordField = $("[type='password']");
    private SelenideElement loginButton = $(byText("Продолжить"));

    public VerificationPage validLogin(DataHelper.AuthInfo info) {
        loginField.setValue(info.getLogin());
        passwordField.setValue(info.getPassword());
        loginButton.click();
        return new VerificationPage();
    }
}

package org.algoriza.pages;

import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class LoginPage extends BaseAuthPage {
    private static final Logger log = LoggerFactory.getLogger(LoginPage.class);
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public String getTitle() {
        String titleXpath = "//h1[contains(text(), 'Sign in or create account')]";
        log.info(titleXpath);
        return getElementText(titleXpath, "Title");
    }

    @Override
    public String getSubtitle() {
        String subtitleXpath = "//label[@for='identifier']";
        return getElementText(subtitleXpath, "Subtitle");
    }

    public LoginPage2 login(String emailOrMobileNumber) {
        return enter(emailOrMobileNumber).clickContinue();
    }

    private LoginPage enter(String value) {
        String inputXpath = "//input[@id='identifier' or @name='identifier']";
        enterText(inputXpath, value, "Sign in or create account");
        return this;
    }

    private LoginPage2 clickContinue() {
        String continueButtonXpath = "//button[@type='submit']";
        clickElement(continueButtonXpath, "Continue");
        return new LoginPage2(driver);
    }
}

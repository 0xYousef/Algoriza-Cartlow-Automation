package org.algoriza.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoginPage2 extends BaseAuthPage {
    private static final Logger log = LoggerFactory.getLogger(LoginPage2.class);
    public LoginPage2(WebDriver driver) {
        super(driver);
    }

    @Override
    public String getTitle() {
        String titleXpath = "//h1[contains(text(), 'Sign In')]";
        return getElementText(titleXpath, "Sign In Title");
    }

    @Override
    public String getSubtitle() {
        String subtitleXpath = "//p[contains(text(), '@')]";
        return getElementText(subtitleXpath, "Email Subtitle");
    }

    public LoginPage clickChangeEmail() {
        String changeEmailXpath = "//span[contains(text(), 'Change')]";
        clickElement(changeEmailXpath, "Change Email Link");
        return new LoginPage(driver);
    }

    public LoginPage2 enterPassword(String password) {
        String passwordInputXpath = "//input[@id='password' or @name='password']";
        enterText(passwordInputXpath, password, "Password");
        return this;
    }


    public void clickForgotPassword() {
        String forgotPasswordXpath = "//a[contains(text(), 'Forgot password?')]";
        clickElement(forgotPasswordXpath, "Forgot Password Link");
    }

    public HomePage clickSignIn() {
        String signInButtonXpath = "//button[contains(@class, 'primary-button') and .//span[text()=' Sign In ']]";
        clickElement(signInButtonXpath, "Sign In");
        return new HomePage(driver);
    }

    public LoginPage2 clickGetOTP() {
        String getOtpButtonXpath = "//button[contains(@class, 'secondary-button') and .//span[text()='Get an OTP on your Email']]";
        clickElement(getOtpButtonXpath, "Get OTP");
        return this;
    }

    public HomePage loginWithPassword(String password) {
        return enterPassword(password).clickSignIn();
    }

    public LoginPage2 requestOTP() {
        return clickGetOTP();
    }
}

package org.algoriza.components.header;

import org.algoriza.enums.ACCOUNT_OPTIONS;
import org.algoriza.pages.LoginPage;
import org.algoriza.pages.HomePage;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class AccountComponent {
    private WebDriver driver;
    private Actions actions;

    public AccountComponent(WebDriver driver) {
        this.driver = driver;
        this.actions = new Actions(driver);
    }

    public void openDropdown() {
        String accountContainerXpath = "//div[contains(@class, 'relative inline-block group')]//span[contains(@class, 'icon-users')]";
        WebElement accountElement = driver.findElement(By.xpath(accountContainerXpath));
        actions.moveToElement(accountElement).perform();

    }

    private void selectOption(ACCOUNT_OPTIONS option) {
        openDropdown();
        String accountOptionXpath =  "//a[contains(text(), '%s')]".formatted(option.getDisplayName());
        WebElement accountOption = driver.findElement(By.xpath(accountOptionXpath));
        actions.moveToElement(accountOption).click().perform();
    }

    public String getName() {
        openDropdown();

        String accountNameXpath = "//p[@class='font-medium']";
        WebElement accountName = driver.findElement(By.xpath(accountNameXpath));
        return accountName.getText();
    }

    public HomePage logout() {
        selectOption(ACCOUNT_OPTIONS.LOGOUT);
        return new HomePage(driver);
    }

    public LoginPage signIn() {
        selectOption(ACCOUNT_OPTIONS.SIGN_IN);
        return new LoginPage(driver);
    }

    public LoginPage createNewAccount() {
        selectOption(ACCOUNT_OPTIONS.CREATE_NEW_ACCOUNT);
        return new LoginPage(driver);
    }

    public void viewMyOrders() {
        selectOption(ACCOUNT_OPTIONS.MY_ORDERS);
    }

    public void viewMyWishlist() {
        selectOption(ACCOUNT_OPTIONS.MY_WISHLIST);
    }

    public void viewProfileInfo() {
        selectOption(ACCOUNT_OPTIONS.PROFILE_INFO);
    }

    public boolean isAccountIconVisible() {
        try {
            String accountIconXpath = "//span[contains(@class, 'icon-users')]";
            return driver.findElement(By.xpath(accountIconXpath)).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean isUserLoggedIn() {
        try {
            openDropdown();

            String accountNameXpath = "//p[@class='font-medium']";
            WebElement accountName = driver.findElement(By.xpath(accountNameXpath));
            String name = accountName.getText();
            boolean isLoggedIn = !name.equals("Welcome Guest");

            String logoutOptionXpath = "//a[.//span[contains(text(), 'Logout')]]";
            boolean hasLogoutOption = !driver.findElements(By.xpath(logoutOptionXpath)).isEmpty();

            actions.moveByOffset(0, 0).perform();

            return isLoggedIn && hasLogoutOption;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean isUserLoggedOut() {
        try {
            openDropdown();

            String accountNameXpath = "//p[@class='font-medium']";
            WebElement accountName = driver.findElement(By.xpath(accountNameXpath));
            String name = accountName.getText();
            boolean isGuest = name.equals("Welcome Guest");

            // Check if sign in option is present (user is logged out)
            String signInOptionXpath = "//a[contains(@href, '/customer/login') and contains(text(), 'Sign In')]";
            boolean hasSignInOption = !driver.findElements(By.xpath(signInOptionXpath)).isEmpty();

            actions.moveByOffset(0, 0).perform();

            return isGuest && hasSignInOption;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public String getWelcomeMessage() {
        openDropdown();

        String welcomeMessageXpath = "//p[@class='font-medium']";
        WebElement welcomeMessage = driver.findElement(By.xpath(welcomeMessageXpath));
        String message = welcomeMessage.getText();

        // Move away to close dropdown
        actions.moveByOffset(0, 0).perform();

        return message;
    }

    public boolean isCreateAccountButtonVisible() {
        try {
            openDropdown();

            String createAccountXpath = "//a[contains(@href, '/customer/register') and contains(text(), 'Create new account')]";
            boolean isVisible = driver.findElement(By.xpath(createAccountXpath)).isDisplayed();

            // Move away to close dropdown
            actions.moveByOffset(0, 0).perform();

            return isVisible;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean isSignInButtonVisible() {
        try {
            openDropdown();

            String signInXpath = "//a[contains(@href, '/customer/login') and contains(text(), 'Sign In')]";
            boolean isVisible = driver.findElement(By.xpath(signInXpath)).isDisplayed();

            // Move away to close dropdown
            actions.moveByOffset(0, 0).perform();

            return isVisible;
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}
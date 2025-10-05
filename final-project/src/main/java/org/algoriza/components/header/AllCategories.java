package org.algoriza.components.header;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AllCategories {
    private WebDriver driver;

    public AllCategories(WebDriver driver) {
        this.driver = driver;
    }

    // Main "All" button
    public void openMenu() {
        String allButtonXpath = "//span[contains(@class, 'icon-hamburger')]/ancestor::span[contains(@class, 'cursor-pointer')]";
        WebElement allButton = driver.findElement(By.xpath(allButtonXpath));
        allButton.click();

        // Wait for menu to open
        String menuContainerXpath = "//div[contains(@class, 'fixed z-[21]') and contains(@class, 'bg-white')]";
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(menuContainerXpath)));
    }

    public void closeMenu() {
        String closeMenuXpath = "//span[contains(@class, 'icon-cancel')]";
        WebElement closeButton = driver.findElement(By.xpath(closeMenuXpath));
        closeButton.click();
    }

    public void selectCategory(String section, String categoryName) {
        openMenu();

        String categoryXpath = String.format("//div[contains(text(), '%s')]/following-sibling::div//span[contains(text(), '%s')]", section, categoryName);
        WebElement categoryElement = driver.findElement(By.xpath(categoryXpath));
        categoryElement.click();
    }

    public void selectCategory(String categoryName) {
        openMenu();

        String categoryXpath = String.format("//span[contains(text(), '%s')]", categoryName);
        WebElement categoryElement = driver.findElement(By.xpath(categoryXpath));
        categoryElement.click();
    }

    // Check if category exists
    public boolean isCategoryPresent(String section, String categoryName) {
        try {
            openMenu();
            String categoryXpath = String.format("//div[contains(text(), '%s')]/following-sibling::div//span[contains(text(), '%s')]", section, categoryName);
            boolean isPresent = driver.findElement(By.xpath(categoryXpath)).isDisplayed();
            closeMenu();
            return isPresent;
        } catch (NoSuchElementException e) {
            closeMenu();
            return false;
        }
    }

    public boolean isCategoryPresent(String categoryName) {
        try {
            openMenu();
            String categoryXpath = String.format("//span[contains(text(), '%s')]", categoryName);
            boolean isPresent = driver.findElement(By.xpath(categoryXpath)).isDisplayed();
            closeMenu();
            return isPresent;
        } catch (NoSuchElementException e) {
            closeMenu();
            return false;
        }
    }

    public boolean isMenuOpen() {
        try {
            String menuContainerXpath = "//div[contains(@class, 'fixed z-[21]') and contains(@class, 'bg-white')]";
            return driver.findElement(By.xpath(menuContainerXpath)).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean isAllButtonVisible() {
        try {
            String allButtonXpath = "//span[contains(@class, 'icon-hamburger')]/ancestor::span[contains(@class, 'cursor-pointer')]";
            return driver.findElement(By.xpath(allButtonXpath)).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }



}

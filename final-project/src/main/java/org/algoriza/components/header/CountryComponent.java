package org.algoriza.components.header;

import org.algoriza.enums.COUNTRIES;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CountryComponent {
    private WebDriver driver;

    public CountryComponent(WebDriver driver) {
        this.driver = driver;
    }

    public void openDropdown() {
        String countryButtonXpath = "//div[contains(@class, 'relative flex-shrink-0')]//button";
        WebElement countryBtn = driver.findElement(By.xpath(countryButtonXpath));
        countryBtn.click();
    }

    public void select(COUNTRIES country) {
        openDropdown();

        String dropdownMenuXpath = "//div[contains(@class, 'group-hover:block')]";
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(dropdownMenuXpath)));

        String countryOptionXpath = "//form[.//span[contains(text(), '%s')]]//button".formatted(country.current());
        WebElement countryOption = driver.findElement(By.xpath(countryOptionXpath));
        countryOption.click();
    }

    public String getSelected() {
        String selectedCountryXpath = "//span[@class='ms-2 max-lg:hidden']";
        WebElement countrySpan = driver.findElement(By.xpath(selectedCountryXpath));
        return countrySpan.getText();
    }

    public boolean isCountryDropdownVisible() {
        try {
            String countryButtonXpath = "//div[contains(@class, 'relative flex-shrink-0')]//button";
            return driver.findElement(By.xpath(countryButtonXpath)).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}
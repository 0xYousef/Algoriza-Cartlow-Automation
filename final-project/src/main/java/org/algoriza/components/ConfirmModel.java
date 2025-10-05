package org.algoriza.components;

import org.algoriza.pages.CartPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ConfirmModel {
    private final WebDriverWait wait;
    public ConfirmModel(WebDriver driver) {
        wait =  new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void agree() {
        String XPATH = "//div[@id='app']//button[normalize-space(text())='Agree']\n";
        WebElement agreeButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(XPATH)));
        agreeButton.click();
    }
    public void disagree() {
        String XPATH = "//div[@id='app']//button[normalize-space(text())='Disagree']\n";
        WebElement agreeButton =wait.until(ExpectedConditions.elementToBeClickable(By.xpath(XPATH)));
        agreeButton.click();
    }
}

package org.algoriza.components.header;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LanguageComponent {
    private static final Logger log = LoggerFactory.getLogger(LanguageComponent.class);
    private WebDriver driver;

    public LanguageComponent(WebDriver driver) {
        this.driver = driver;
    }

    public void switchLanguage() {

        String languageButtonXpath = "//form[@id='locale-switcher']//button";
        WebElement languageButton = driver.findElement(By.xpath(languageButtonXpath));
        languageButton.click();
        log.info("Language changed to {}",getCurrent());
    }

    public String getCurrent() {
        String languageTextXpath = "//form[@id='locale-switcher']//span";
        WebElement languageText = driver.findElement(By.xpath(languageTextXpath));
        return languageText.getText();
    }

    public boolean isLanguageSwitcherVisible() {
        try {
            String languageButtonXpath = "//form[@id='locale-switcher']//button";
            boolean flag = driver.findElement(By.xpath(languageButtonXpath)).isDisplayed();
            log.info("Language switcher visible");
            return flag;
        } catch (NoSuchElementException e) {
            log.info("Language switcher not visible");
            return false;
        }
    }
}

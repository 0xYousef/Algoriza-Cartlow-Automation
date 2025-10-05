package org.algoriza.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class BaseAuthPage {
    protected static final Logger log = LoggerFactory.getLogger(BaseAuthPage.class);
    protected final WebDriver driver;

    public BaseAuthPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getValidationMessage() {
        String validationMessageXpath = "//p[contains(@class, 'text-red-500') or contains(@class, 'error')]";
        try {
            WebElement validationElement = driver.findElement(By.xpath(validationMessageXpath));
            if (!validationElement.isDisplayed()) {
                log.warn("Validation message is not displayed");
            }
            String message = validationElement.getText();
            log.info("Validation message: {}", message);
            return message;
        } catch (NoSuchElementException e) {
            log.error("Validation Failed To Fetch");
            throw new NoSuchElementException("Validation Failed To Fetch");
        }
    }

    protected String getElementText(String xpath, String elementName) {
        try {
            WebElement element = driver.findElement(By.xpath(xpath));
            if (element.isDisplayed()) {
                log.info("{} is visible", elementName);
                return element.getText();
            }
            log.warn("{} is not visible", elementName);
            return null;
        } catch (NoSuchElementException e) {
            log.warn("{} not found", elementName);
            return null;
        }
    }

    protected void clickElement(String xpath, String elementName) {
        try {
            WebElement element = driver.findElement(By.xpath(xpath));
            if (element.isDisplayed() && element.isEnabled()) {
                log.info("{} is visible and enabled", elementName);
                element.click();
            } else {
                log.warn("{} is not clickable", elementName);
            }
        } catch (NoSuchElementException e) {
            log.error("{} not found", elementName);
            throw new NoSuchElementException(elementName + " not found");
        }
    }

    protected void enterText(String xpath, String value, String elementName) {
        try {
            WebElement element = driver.findElement(By.xpath(xpath));
            element.clear();
            element.sendKeys(value);
            log.info("Entered {}: {}", elementName, value);
        } catch (NoSuchElementException e) {
            log.error("{} not found", elementName);
            throw new NoSuchElementException(elementName + " not found");
        }
    }

    public abstract String getTitle();
    public abstract String getSubtitle();
}

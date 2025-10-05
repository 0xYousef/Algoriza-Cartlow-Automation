package org.algoriza.components.header;

import org.algoriza.pages.ProductsPage;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.List;

public class CategoryComponent  {

    private static final Logger log = LoggerFactory.getLogger(CategoryComponent.class);
    private final WebDriver driver;
    private final WebDriverWait wait;

    public CategoryComponent(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public ProductsPage selectCategory(String categoryName) {
        String popularCategoryXpath = String.format("//ul[contains(@class, 'overflow-x-auto')]//a[contains(text(), '%s')]", categoryName);
        WebElement categoryElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(popularCategoryXpath)));
        categoryElement.click();
        log.info("Selected Category: {}", categoryName);
        return new ProductsPage(driver);
    }

    public void scrollLeft() {
        String scrollLeftButtonXpath = "//button[contains(@class, 'icon-arrow-left')]";
        WebElement scrollLeftButton = driver.findElement(By.xpath(scrollLeftButtonXpath));
        scrollLeftButton.click();
        log.info("Scroll Left Button: {}", scrollLeftButtonXpath);
    }

    public void scrollRight() {
        String scrollRightButtonXpath = "//button[contains(@class, 'icon-arrow-right')]";
        WebElement scrollRightButton = driver.findElement(By.xpath(scrollRightButtonXpath));
        scrollRightButton.click();
        log.info("Scroll Right Button: {}", scrollRightButtonXpath);
    }

    public List<String> getAllCategoryNames() {
        String popularCategoriesXpath = "//ul[contains(@class, 'overflow-x-auto')]//a";
        List<WebElement> categoryElements = driver.findElements(By.xpath(popularCategoriesXpath));
        return categoryElements.stream()
                .map(WebElement::getText)
                .toList();
    }

    public boolean isCategoryVisible(String categoryName) {
        try {
            String popularCategoryXpath = String.format("//ul[contains(@class, 'overflow-x-auto')]//a[contains(text(), '%s')]", categoryName);
            boolean res = driver.findElement(By.xpath(popularCategoryXpath)).isDisplayed();
            log.info("Category visible: {} is {}",categoryName,res);
            return res;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

}

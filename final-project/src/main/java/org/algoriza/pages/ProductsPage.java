package org.algoriza.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.MoveTargetOutOfBoundsException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProductsPage extends BasePage {
    private static final Logger log = LoggerFactory.getLogger(ProductsPage.class);
    private final Actions actions;

    public ProductsPage(WebDriver driver) {
        super(driver);
        actions = new Actions(driver);
    }

    public ProductPage select(String productName) {
        String xpath = "//div[contains(@class, 'lst_card')][.//span[contains(text(), '%s')]]";

        while (true) {
            try {
                WebElement product = wait.until(ExpectedConditions.presenceOfElementLocated(
                        By.xpath(xpath.formatted(productName))));

                // Try to scroll and click using actions
                actions.moveToElement(product).click().perform();
                return new ProductPage(driver);

            } catch (MoveTargetOutOfBoundsException e) {
                log.info("Element out of view, scrolling page to make it visible...");
                scrollMultipleTimes();

            } catch (TimeoutException e) {
                log.info("Product '{}' not found, scrolling to load more...", productName);
                scrollMultipleTimes();

                if (hasLoadMore()) {
                    clickLoadMore();
                }
            }
        }
    }

    private void scrollMultipleTimes() {
        actions.sendKeys(Keys.PAGE_DOWN).perform();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

    }

    private boolean hasLoadMore() {
        try {
            WebElement loadMore = driver.findElement(By.xpath("//button[contains(., 'Load More')]"));
            return loadMore.isDisplayed() && loadMore.isEnabled();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    private void clickLoadMore() {
        try {
            WebElement loadMore = driver.findElement(By.xpath("//button[contains(., 'Load More')]"));
            actions.moveToElement(loadMore).click().perform();
            log.info("Clicked Load More button");
            Thread.sleep(2000);
        } catch (Exception e) {
            log.warn("Could not click Load More button");
        }
    }
}
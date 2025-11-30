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
    private static final String LOAD_MORE_BUTTON_XPATH = "//button[contains(., 'Load More')]";
    private static final String PRODUCT_CARD_XPATH = "//div[contains(@class, 'lst_card')][.//span[contains(text(), '%s')]]";

    public ProductsPage(WebDriver driver) {
        super(driver);
        this.actions = new Actions(driver);
    }


    public void findAndClickProduct(String productName) {
        log.info("Searching for product: {}", productName);

        while (true) {
            boolean clicked = clickOnProduct(productName);
            if (clicked) {
                log.info("Successfully clicked product: {}", productName);
                return;
            }

            if (!hasLoadMore()) {
                log.warn("No more products to load. Product '{}' not found.", productName);
                return;
            }

            if (!clickLoadMore()) {
                log.warn("Could not load more products. Product '{}' not found.", productName);
                return;
            }
            actions.scrollByAmount(0, 200).perform();
        }
    }

 
    private boolean clickOnProduct(String productName) {
        String xpath = String.format(PRODUCT_CARD_XPATH, productName);
        try {
            WebElement product = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
            actions.moveToElement(product).pause(java.time.Duration.ofMillis(200)).perform();
            actions.moveToElement(product).click().perform();
            log.info("Clicked on product: {}", productName);
            return true;
        } catch (TimeoutException | MoveTargetOutOfBoundsException e) {
            log.debug("Product '{}' out of viewport, scrolling down.", productName);
            actions.scrollByAmount(0, 200).perform();
            return false;
        } catch ( NoSuchElementException e) {
            log.debug("Product '{}' not found on current page.", productName);
            return false;
        }
    }

    private boolean hasLoadMore() {
        try {
            WebElement loadMore = driver.findElement(By.xpath(LOAD_MORE_BUTTON_XPATH));
            return loadMore.isDisplayed() && loadMore.isEnabled();
        }catch (MoveTargetOutOfBoundsException e) {
            return true;
        }
        catch (NoSuchElementException e) {
            return false;
        }
    }

    private boolean clickLoadMore() {
        try {
            WebElement loadMore = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(LOAD_MORE_BUTTON_XPATH)));
            actions.moveToElement(loadMore).pause(java.time.Duration.ofMillis(200)).click().perform();
            log.info("Clicked 'Load More' button");
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[contains(@class, 'loading')]")));
            return true;
        } catch (MoveTargetOutOfBoundsException e) {
            log.debug("Load More button not found on current page.");
            return true;
        } catch (TimeoutException e) {
            log.warn("'Load More' button not clickable or loading took too long");
            return true;
        } catch (Exception e) {
            log.warn("Failed to click 'Load More': {}", e.getMessage());
            return false;
        }
    }
}

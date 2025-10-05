package org.algoriza.components.header;

import org.algoriza.pages.CartPage;
import org.algoriza.pages.CheckoutPage;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CartComponent {
    private WebDriver driver;
    private WebDriverWait wait;
    public CartComponent(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    public void open() {
        String cartIconXpath = "//span[@role='button' and normalize-space(text())='Cart']\n";
        WebElement cartElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(cartIconXpath)));
        cartElement.click();
    }

    public int getItemsCount() {
        try {
            String cartBadgeXpath = "//span[contains(@class, 'absolute -top-4')]";
            WebElement cartBadge = driver.findElement(By.xpath(cartBadgeXpath));
            String countText = cartBadge.getText().trim();
            return Integer.parseInt(countText);
        } catch (NoSuchElementException e) {
            return 0;
        }
    }

    public boolean isEmpty() {
        return getItemsCount() == 0;
    }

    public CartPage navigateToCartPage() {
        open();

        String cartSidebarXpath = "//a[contains(@href, '/checkout/cart') and contains(normalize-space(.), 'View Cart')]";
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(cartSidebarXpath))).click();

        return new CartPage(driver);
    }

    public void removeItem(int itemIndex) {
        open();

        String cartSidebarXpath = "//div[contains(@class, 'fixed z-[21]')]";
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(cartSidebarXpath)));

        String removeButtonXpath = "(//span[contains(text(), 'Remove')])[%d]".formatted(itemIndex);
        WebElement removeBtn = driver.findElement(By.xpath(removeButtonXpath));
        removeBtn.click();
    }

    public CheckoutPage continueToCheckout() {
        open();

        String cartSidebarXpath = "//div[contains(@class, 'fixed z-[21]')]";
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(cartSidebarXpath)));

        String checkoutButtonXpath = "//a[contains(@href, 'checkout/onepage')]";
        WebElement checkoutBtn = driver.findElement(By.xpath(checkoutButtonXpath));
        checkoutBtn.click();

        return new CheckoutPage(driver);
    }

    public CartPage viewFullCart() {
        open();

        String cartSidebarXpath = "//div[contains(@class, 'fixed z-[21]')]";
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(cartSidebarXpath)));

        String viewCartButtonXpath = "//a[contains(@href, 'checkout/cart')]";
        WebElement viewCartBtn = driver.findElement(By.xpath(viewCartButtonXpath));
        viewCartBtn.click();

        return new CartPage(driver);
    }

    public void closeSidebar() {
        try {
            String closeCartXpath = "//span[contains(@class, 'icon-cancel')]";
            WebElement closeBtn = driver.findElement(By.xpath(closeCartXpath));
            closeBtn.click();
        } catch (NoSuchElementException e) {
            // Cart sidebar not open, do nothing
        }
    }

    public String getSubtotal() {
        try {
            String subtotalXpath = "//p[contains(text(), 'Sub Total:')]/following-sibling::p";
            WebElement subtotal = driver.findElement(By.xpath(subtotalXpath));
            return subtotal.getText();
        } catch (NoSuchElementException e) {
            return "0.00";
        }
    }

    public boolean isCartIconVisible() {
        try {
            String cartIconXpath = "//span[contains(@class, 'icon-cart')]";
            return driver.findElement(By.xpath(cartIconXpath)).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}

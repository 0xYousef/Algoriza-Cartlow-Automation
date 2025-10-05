package org.algoriza.pages;

import org.algoriza.components.ConfirmModel;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CartPage extends BasePage{
    public final ConfirmModel confirmModel;
    public CartPage(WebDriver driver) {
        super(driver);
        confirmModel = new ConfirmModel(driver);
    }

    public CartPage removeProductFromCart(String productName) {
        String removeXPath = String.format(
                "//p[contains(normalize-space(.), '%s')]/ancestor::div[contains(@class,'main-1')]//span[normalize-space()='Remove']",
                productName
        );

        WebElement removeButton = driver.findElement(By.xpath(removeXPath));

        removeButton.click();
        return this;
    }


    public CheckoutPage  checkout(){
        String XPATH ="//a[contains(@href,'/checkout/onepage') and normalize-space(text())='Checkout']";
        WebElement checkoutButton = driver.findElement(By.xpath(XPATH));
        checkoutButton.click();
        return new CheckoutPage(driver);
    }
}

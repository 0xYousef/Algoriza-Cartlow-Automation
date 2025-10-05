package org.algoriza.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Map;

public class ProductPage extends BasePage {

    private WebDriver driver;
    private WebDriverWait wait;
    private Actions actions;

    public ProductPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.actions = new Actions(driver);
    }

    public ProductPage setQuantity(int quantity) {
        String quantityCss = "p.w-2\\.5";
        String increaseCss = "span.icon-plus";
        String decreaseCss = "span.icon-minus";

        // Alternative more stable approach using parent context
        WebElement quantityContainer = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[contains(@class, 'border-navyBlue') and contains(@class, 'rounded-xl')]")));

        WebElement qtyElement = quantityContainer.findElement(By.cssSelector("p"));
        int currentQty = Integer.parseInt(qtyElement.getText().trim());

        if (currentQty < quantity) {
            WebElement plus = quantityContainer.findElement(By.cssSelector("span.icon-plus"));
            while (currentQty < quantity) {
                actions.moveToElement(plus).pause(Duration.ofMillis(200)).click().perform();
                // Wait for quantity to update
                wait.until(ExpectedConditions.not(ExpectedConditions.textToBePresentInElement(qtyElement, String.valueOf(currentQty))));
                currentQty = Integer.parseInt(qtyElement.getText().trim());
            }
        } else if (currentQty > quantity) {
            WebElement minus = quantityContainer.findElement(By.cssSelector("span.icon-minus"));
            while (currentQty > quantity) {
                actions.moveToElement(minus).pause(Duration.ofMillis(200)).click().perform();
                // Wait for quantity to update
                wait.until(ExpectedConditions.not(ExpectedConditions.textToBePresentInElement(qtyElement, String.valueOf(currentQty))));
                currentQty = Integer.parseInt(qtyElement.getText().trim());
            }
        }
        return this;
    }

    public ProductPage selectCategory(String categoryName, String value) {
        String categoryXpath = String.format(
                "//h2[contains(text(), '%s')]/following-sibling::div//label[@title='%s']/input",
                categoryName, value);
        WebElement categoryOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(categoryXpath)));
        actions.moveToElement(categoryOption).pause(Duration.ofMillis(200)).click().perform();
        return this;
    }

    public ProductPage selectOptions(Map<String, String> options) {
        for (Map.Entry<String, String> entry : options.entrySet()) {
            String attributeName = entry.getKey();
            String attributeValue = entry.getValue();

            // More stable XPath using the actual attribute structure
            String optionXpath = String.format(
                    "//input[@name='super_attribute[%s]' and @value]//ancestor::label[contains(@title, '%s')]",
                    attributeName, attributeValue);

            WebElement optionElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(optionXpath)));
            actions.moveToElement(optionElement).pause(Duration.ofMillis(200)).click().perform();

            // Wait for selection to be applied (check for checkmark or selected state)
            wait.until(ExpectedConditions.attributeContains(optionElement, "class", "border-accent"));
        }
        return this;
    }

    public ProductPage addToCart() {
        // More stable locator using button text and type
        String addToCartXpath = "//button[contains(@class, 'secondary-button') and contains(text(), 'Add To Cart')]";
        WebElement cartButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(addToCartXpath)));
        actions.moveToElement(cartButton).pause(Duration.ofMillis(200)).click().perform();
        return this;
    }

    public String getQuantity() {
        // More stable locator using the quantity container
        WebElement quantityContainer = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[contains(@class, 'border-navyBlue') and contains(@class, 'rounded-xl')]")));
        WebElement qtyElement = quantityContainer.findElement(By.cssSelector("p"));
        return qtyElement.getText().trim();
    }

    public ProductPage addToCart(int quantity, Map<String, String> options) {
        setQuantity(quantity);
        selectOptions(options);
        clickAddToCart();
        return this;
    }

    private void clickAddToCart() {
        String addToCartXpath = "//button[contains(@class, 'secondary-button') and contains(text(), 'Add To Cart')]";
        WebElement cartButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(addToCartXpath)));
        actions.sendKeys(Keys.DOWN).perform();
        actions.sendKeys(Keys.DOWN).perform();
        actions.moveToElement(cartButton).pause(Duration.ofMillis(200)).click().perform();

    }

    // Additional stable locators for other elements
    public String getProductTitle() {
        String titleXpath = "//h1//span[contains(@class, 'pdp-variant-name')]";
        WebElement titleElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(titleXpath)));
        return titleElement.getText().trim();
    }

    public String getProductPrice() {
        String priceXpath = "//span[@itemprop='price']";
        WebElement priceElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(priceXpath)));
        return priceElement.getText().trim();
    }

    // Wait for page to load completely
    public ProductPage waitForPageToLoad() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[@id='pdp_configurable_section']")));
        return this;
    }
}
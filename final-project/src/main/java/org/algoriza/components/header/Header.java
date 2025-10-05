package org.algoriza.components.header;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Header {
    private static final Logger log = LoggerFactory.getLogger(Header.class);
    private WebDriver driver;
    public final CountryComponent country;
    public final LanguageComponent language;
    public final CartComponent cart;
    public final AccountComponent account;

    public Header(WebDriver driver) {
        this.driver = driver;
        this.country = new CountryComponent(driver);
        this.language = new LanguageComponent(driver);
        this.cart = new CartComponent(driver);
        this.account = new AccountComponent(driver);
    }

    public boolean isLoaded() {
        log.info("verify Header is Loaded");
        return country.isCountryDropdownVisible() &&
                cart.isCartIconVisible() &&
                account.isAccountIconVisible();
    }
}
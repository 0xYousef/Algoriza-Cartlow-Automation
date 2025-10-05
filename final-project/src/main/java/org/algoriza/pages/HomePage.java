package org.algoriza.pages;

import org.algoriza.components.header.CategoryComponent;
import org.algoriza.components.header.Header;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {
    public final Header header;
    public HomePage(WebDriver driver) {
        super(driver);
        header = new Header(driver);
    }

    public CategoryComponent categories(){
        return new CategoryComponent(driver);
    }




}

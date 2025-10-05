import io.qameta.allure.*;
import org.algoriza.data_package.dto.Add_Products_Cart_CheckoutDTO;
import org.algoriza.data_package.providers.CheckoutProvider;
import org.algoriza.dto.ProductDTO;
import org.algoriza.enums.COUNTRIES;
import org.algoriza.enums.DISPLAYED_ITEMS;
import org.algoriza.pages.*;
import org.testng.Assert;
import org.testng.annotations.Test;

@Epic("Cartlow E-Commerce")
@Feature("User adds/removes products and checkout")
public class TC01_Automation_En extends BaseTest {



    @Story("Login, add products to cart, remove one, and proceed to checkout")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify user can login, add multiple products, remove an item, and proceed to checkout")
    @Test(dataProvider = "add_products_cart_checkout", dataProviderClass = CheckoutProvider.class)
    public void addAndRemoveItemsFlow(Add_Products_Cart_CheckoutDTO form) {
        System.out.println(form.getAccount().getEmail());

        HomePage homePage = new HomePage(driver);
        homePage.begin();
        Assert.assertTrue(homePage.header.isLoaded());
        homePage.header.account.signIn().
                login(form.getAccount().getEmail()).
                enterPassword(form.getAccount().getPassword())
                .clickSignIn();
        for (ProductDTO product : form.getProducts()) {
            homePage.categories().selectCategory(product.getCategory()).select(product.getTitle()).setQuantity(product.getQuantity()).addToCart();
        }
        homePage.header.cart.navigateToCartPage().checkout();
    }
}

package org.algoriza.data_package.providers;

import org.algoriza.data_package.dto.Add_Products_Cart_CheckoutDTO;
import org.algoriza.repository.AccountsRepository;
import org.algoriza.repository.ProductsRepository;
import org.testng.annotations.DataProvider;

public class CheckoutProvider {

    @DataProvider(name = "add_products_cart_checkout")
    public static Object[][] getAccounts() {
        AccountsRepository accountsRepo = new AccountsRepository();
        ProductsRepository productsRepo = new ProductsRepository("uae");

        Add_Products_Cart_CheckoutDTO from = Add_Products_Cart_CheckoutDTO.builder()
                .account(accountsRepo.getRandom())
                .products(productsRepo.getAll())
                .build();

        return new Object[][] { { from } };
    }
}

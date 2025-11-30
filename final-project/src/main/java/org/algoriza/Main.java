package org.algoriza;


import org.algoriza.repository.AccountsRepository;
import org.algoriza.repository.ProductsRepository;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
//        SelectedProducts selectedProducts = JsonParser.
//                readFromResources("data/selected_products.json", SelectedProducts.class);
//
//        System.out.println("=== LOADED PRODUCTS ===");
//        selectedProducts.getSelectedProducts()
//                .forEach((region, products) -> {
//            System.out.println("\nRegion: " + region.toUpperCase());
//            for (int i = 0; i < products.size(); i++) {
//                ProductDTO product = products.get(i);
//                System.out.println((i + 1) + ". " + product.toString());
//                System.out.println(product.getProperties().get("condition"));
//
//            }
//        });
//
//
//        Accounts accountsWrapper = JsonParser.readFromResources("data/accounts.json", Accounts.class);
//        List<AccountDTO> accounts = accountsWrapper.getAccounts();
//
//        AccountDTO randomAccount = RandomUtil.getRandomElement(accounts);
//
//        System.out.println("Random account:");
//        System.out.println("Email: " + randomAccount.getEmail());
//        System.out.println("Password: " + randomAccount.getPassword());

        ProductsRepository productsRepo = new ProductsRepository("uae");
        AccountsRepository  accountsRepo = new AccountsRepository();
        productsRepo.getAll().stream().forEach(product -> {
            System.out.println("Product: " + product.toString());
        });
        System.out.println("Random Product: " + productsRepo.getRandom().toString());
        System.out.println("-----------------------------------------");
        accountsRepo.getAll().stream().forEach(account -> {
            System.out.println("Account: " + account.getEmail());
        });
        System.out.println("Random Account: " + accountsRepo.getRandom().getEmail());
    }
}
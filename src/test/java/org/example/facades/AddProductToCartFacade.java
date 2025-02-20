package org.example.facades;

import org.example.pages.HomePage;
import org.example.pages.modals.AddToCartConfirmationModalPage;

public class AddProductToCartFacade {

    private HomePage homePage;

    public AddProductToCartFacade(HomePage homePage) {
        this.homePage = homePage;
    }

    public AddToCartConfirmationModalPage addProductWithCustomizationToCart(String productName) {
        return homePage
                .searchForProduct(productName)
                .viewProductDetails(productName)
                .customizeProduct(productName)
                .addProductToCart();
    }

}

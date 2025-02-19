package org.example.tests;

import org.assertj.core.api.Assertions;
import org.example.pages.*;
import org.example.pages.modals.AddToCartConfirmationModalPage;
import org.example.pages.sections.orderDetailsPage.OrderAddressSection;
import org.example.pages.sections.orderDetailsPage.OrderPaymentSection;
import org.example.pages.sections.orderDetailsPage.OrderShippingSection;
import org.example.utils.Properties;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FullPurchaseTest extends BaseTest{

    private HomePage homePage;

    @BeforeEach
    void beforeEach() {
        homePage = new HomePage(page);
        page.navigate(Properties.getProperty("app.url"));
        homePage.getTopNavigationSection().setPageLanguageToEN();
    }

    @Test
    void shouldPurchaseSelectedProductTest() {
        SearchResultPage searchResultPage = homePage.getTopMenuAndSearchSection().searchForProducts("Customizable Mug");
        ProductDetailsPage productDetailsPage = searchResultPage.getSearchResultsSection().viewProductDetails("Customizable Mug");
        productDetailsPage.getProductCustomizationSection().customizeProduct("Customizable Mug");
        AddToCartConfirmationModalPage confirmationModal = productDetailsPage.getAddToCartSection().addProductToCart();

        Assertions.assertThat(confirmationModal.getConfirmationMessage()).contains("Product successfully added to your shopping cart");

        ShoppingCartPage shoppingCartPage = confirmationModal.clickCheckoutButton();
        OrderDetailsPage orderDetailsPage = shoppingCartPage.getPurchaseSummarySection().proceedToCheckout();

        OrderAddressSection orderAddressSection = orderDetailsPage.getOrderPersonalInformationSection().fillOrderForm();
        OrderShippingSection orderShippingSection = orderAddressSection.enterAddress();
        OrderPaymentSection orderPaymentSection = orderShippingSection.selectShippingMethod();
        OrderConfirmationPage orderConfirmationPage = orderPaymentSection.placeOrder();

        Assertions.assertThat(orderConfirmationPage.getOrderConfirmationDetailsSection()
                .getConfirmationTitle()).containsIgnoringCase("your order is confirmed");

        page.waitForTimeout(3000);

    }
}

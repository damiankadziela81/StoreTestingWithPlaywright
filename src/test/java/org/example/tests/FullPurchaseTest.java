package org.example.tests;

import org.assertj.core.api.Assertions;
import org.example.facades.AddProductToCartFacade;
import org.example.facades.OrderProductFacade;
import org.example.pages.*;
import org.example.pages.modals.AddToCartConfirmationModalPage;
import org.example.pages.sections.orderDetailsPage.OrderAddressSection;
import org.example.pages.sections.orderDetailsPage.OrderPaymentSection;
import org.example.pages.sections.orderDetailsPage.OrderShippingSection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FullPurchaseTest extends BaseTest{

    private HomePage homePage;

    private AddProductToCartFacade addProductToCartFacade;
    private OrderProductFacade orderProductFacade;

    private final String productName = "Customizable Mug";

    @BeforeEach
    void beforeEach() {
        homePage = new HomePage(page);
        addProductToCartFacade = new AddProductToCartFacade(homePage);
        orderProductFacade = new OrderProductFacade();
    }

    @Test
    void shouldPurchaseSelectedProductTest() {
        SearchResultPage searchResultPage = homePage.getTopMenuAndSearchSection().searchForProducts(productName);
        ProductDetailsPage productDetailsPage = searchResultPage.getSearchResultsSection().viewProductDetails(productName);
        productDetailsPage.getProductCustomizationSection().customizeProduct(productName);
        AddToCartConfirmationModalPage confirmationModal = productDetailsPage.getAddToCartSection().addProductToCart();

        Assertions.assertThat(confirmationModal.getConfirmationMessage()).contains("Product successfully added to your shopping cart");

        ShoppingCartPage shoppingCartPage = confirmationModal.proceedToCheckoutOnModal();
        OrderDetailsPage orderDetailsPage = shoppingCartPage.getPurchaseSummarySection().proceedToCheckout();

        OrderAddressSection orderAddressSection = orderDetailsPage.getOrderPersonalInformationSection().fillOrderForm();
        OrderShippingSection orderShippingSection = orderAddressSection.enterAddress();
        OrderPaymentSection orderPaymentSection = orderShippingSection.selectShippingMethod();
        OrderConfirmationPage orderConfirmationPage = orderPaymentSection.placeOrder();

        Assertions.assertThat(orderConfirmationPage.getOrderConfirmationDetailsSection()
                .getConfirmationTitle()).containsIgnoringCase("your order is confirmed");

    }

    @Test
    void shouldPurchaseSelectedProductTestV2() {
        AddToCartConfirmationModalPage confirmationModal = homePage
                .searchForProduct(productName)
                .viewProductDetails(productName)
                .customizeProduct(productName)
                .addProductToCart();

        Assertions.assertThat(confirmationModal.getConfirmationMessage()).contains("Product successfully added to your shopping cart");

        OrderConfirmationPage orderConfirmationPage = confirmationModal
                .proceedToCheckoutOnModal()
                .proceedToCheckoutOnShoppingCartPage()
                .enterOrderDetails();

        Assertions.assertThat(orderConfirmationPage.getOrderConfirmationDetailsSection()
                .getConfirmationTitle()).containsIgnoringCase("your order is confirmed");
    }

    @Test
    void shouldPurchaseSelectedProductTestV3() {
        AddToCartConfirmationModalPage confirmationModal = addProductToCartFacade.addProductWithCustomizationToCart(productName);
        Assertions.assertThat(confirmationModal.getConfirmationMessage()).contains("Product successfully added to your shopping cart");

        OrderConfirmationPage confirmationPage = orderProductFacade.orderProduct(confirmationModal);
        Assertions.assertThat(confirmationPage.getOrderConfirmationDetailsSection()
                .getConfirmationTitle()).containsIgnoringCase("your order is confirmed");
    }


}

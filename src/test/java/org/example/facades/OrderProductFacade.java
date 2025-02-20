package org.example.facades;

import org.example.pages.OrderConfirmationPage;
import org.example.pages.modals.AddToCartConfirmationModalPage;

public class OrderProductFacade {

    public OrderConfirmationPage orderProduct(AddToCartConfirmationModalPage confirmationModal){
        return confirmationModal
                .proceedToCheckoutOnModal()
                .proceedToCheckoutOnShoppingCartPage()
                .enterOrderDetails();
    }

}

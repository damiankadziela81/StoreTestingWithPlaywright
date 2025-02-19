package org.example.pages;

import com.microsoft.playwright.Page;
import lombok.Getter;
import org.example.pages.sections.orderDetailsPage.OrderAddressSection;
import org.example.pages.sections.orderDetailsPage.OrderPersonalInformationSection;
import org.example.utils.PageUtils;

@Getter
public class OrderDetailsPage extends BasePage{

    private OrderPersonalInformationSection orderPersonalInformationSection;
    private OrderAddressSection orderAddressSection;


    public OrderDetailsPage(Page page) {
        super(page);
        PageUtils.waitForPageToLoad(page);
        this.orderPersonalInformationSection = new OrderPersonalInformationSection(page);
        this.orderAddressSection = new OrderAddressSection(page);
    }

    public OrderConfirmationPage enterOrderDetails() {
        return orderPersonalInformationSection
                .fillOrderForm()
                .enterAddress()
                .selectShippingMethod()
                .placeOrder();
    }
}

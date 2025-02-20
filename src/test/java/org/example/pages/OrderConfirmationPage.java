package org.example.pages;

import com.microsoft.playwright.Page;
import lombok.Getter;
import org.example.pages.sections.orderConfirmationPage.OrderConfirmationDetailsSection;
import org.example.utils.PageUtils;

@Getter
public class OrderConfirmationPage extends BasePage{

    private OrderConfirmationDetailsSection orderConfirmationDetailsSection;
    public OrderConfirmationPage(Page page) {
        super(page);
        PageUtils.waitForPageToLoad(page);
        this.orderConfirmationDetailsSection = new OrderConfirmationDetailsSection(page);
    }
}

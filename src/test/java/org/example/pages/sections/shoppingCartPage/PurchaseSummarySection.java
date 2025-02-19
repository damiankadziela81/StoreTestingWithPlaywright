package org.example.pages.sections.shoppingCartPage;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import org.example.pages.BasePage;
import org.example.pages.OrderDetailsPage;

public class PurchaseSummarySection extends BasePage {

    private Locator proceedToCheckoutButton;

    public PurchaseSummarySection(Page page) {
        super(page);
        this.proceedToCheckoutButton = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Proceed to checkout"));
    }

    public OrderDetailsPage proceedToCheckout() {
        proceedToCheckoutButton.click();
        return new OrderDetailsPage(page);
    }
}

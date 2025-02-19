package org.example.pages.modals;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import org.example.pages.BasePage;
import org.example.pages.ShoppingCartPage;
import org.example.utils.PageUtils;

public class AddToCartConfirmationModalPage extends BasePage {

    private Locator confirmationLabel;
    private Locator checkoutButton;

    public AddToCartConfirmationModalPage(Page page) {
        super(page);
        PageUtils.waitForPageToLoad(page);
        this.confirmationLabel = page.locator("#myModalLabel");
        this.checkoutButton = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Proceed to checkout"));
    }

    public String getConfirmationMessage() {
        return confirmationLabel.innerText();
    }

    public ShoppingCartPage clickCheckoutButton() {
        checkoutButton.click();
        return new ShoppingCartPage(page);
    }
}

package org.example.pages;

import com.microsoft.playwright.Page;
import lombok.Getter;
import org.example.pages.sections.shoppingCartPage.PurchaseSummarySection;
import org.example.utils.PageUtils;

@Getter
public class ShoppingCartPage extends BasePage{

    private PurchaseSummarySection purchaseSummarySection;

    public ShoppingCartPage(Page page) {
        super(page);
        PageUtils.waitForPageToLoad(page);
        this.purchaseSummarySection = new PurchaseSummarySection(page);
    }
}

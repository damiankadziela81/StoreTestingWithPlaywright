package org.example.pages.sections;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import org.example.pages.SearchResultPage;

public class TopMenuAndSearchSection {

    private Locator searchInput;
    private Page page;

    public TopMenuAndSearchSection(Page page) {
        this.page = page;
        this.searchInput = page.locator("input[name=s]");
    }

    public SearchResultPage searchForProducts(String productName) {
        searchInput.fill(productName);
        page.keyboard().press("Enter");
        return new SearchResultPage(page);
    }
}

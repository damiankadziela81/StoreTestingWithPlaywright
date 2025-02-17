package org.example.pages.sections.searchResultsPage;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import lombok.Getter;

import java.util.List;

public class SearchResultsSection {

    @Getter
    private List<Locator> products;

    public SearchResultsSection(Page page) {
        products = page.locator(".js-product").all();
    }
}

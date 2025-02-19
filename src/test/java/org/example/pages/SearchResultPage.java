package org.example.pages;

import com.microsoft.playwright.Page;
import lombok.Getter;
import org.example.pages.sections.searchResultsPage.SearchResultsSection;
import org.example.utils.PageUtils;

public class SearchResultPage extends BasePage{

    @Getter
    private SearchResultsSection searchResultsSection;

    public SearchResultPage(Page page) {
        super(page);
        PageUtils.waitForPageToLoad(page);
        this.searchResultsSection = new SearchResultsSection(page);
    }

    public ProductDetailsPage viewProductDetails(String productName) {
        return getSearchResultsSection().viewProductDetails(productName);
    }
}

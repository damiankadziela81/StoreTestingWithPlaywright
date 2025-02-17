package org.example.pages;

import com.microsoft.playwright.Page;
import lombok.Getter;
import org.example.pages.sections.searchResultsPage.SearchResultsSection;
import org.example.utils.PageUtils;

public class SearchResultPage {

    @Getter
    private SearchResultsSection searchResultsSection;

    public SearchResultPage(Page page) {
        PageUtils.waitForPageToLoad(page);
        this.searchResultsSection = new SearchResultsSection(page);
    }
}

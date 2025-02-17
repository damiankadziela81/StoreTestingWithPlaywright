package org.example.pages;

import com.microsoft.playwright.Page;
import lombok.Getter;
import org.example.pages.sections.searchResultsPage.SearchResultsSection;

public class SearchResultPage {

    @Getter
    private SearchResultsSection searchResultsSection;

    public SearchResultPage(Page page) {
        this.searchResultsSection = new SearchResultsSection(page);
    }
}

package org.example.pages;

import com.microsoft.playwright.Page;
import lombok.Getter;
import org.example.pages.sections.common.TopMenuAndSearchSection;
import org.example.pages.sections.common.TopNavigationSection;

public class BasePage {

    protected Page page;

    @Getter
    protected TopMenuAndSearchSection topMenuAndSearchSection;

    @Getter
    protected TopNavigationSection topNavigationSection;

    public BasePage(Page page) {
        this.page = page;
        this.topMenuAndSearchSection = new TopMenuAndSearchSection(page);
        this.topNavigationSection = new TopNavigationSection(page);
    }

    public SearchResultPage searchForProduct(String productName) {
        return topMenuAndSearchSection.searchForProducts(productName);
    }

    public void setPageLanguageToEn() {
        topNavigationSection.setPageLanguageToEn();
    }

    public ArtPage clickArtLink() {
        return topMenuAndSearchSection.clickArtLink();
    }
}

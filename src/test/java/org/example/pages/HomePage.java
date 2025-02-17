package org.example.pages;

import com.microsoft.playwright.Page;
import lombok.Getter;
import org.example.pages.sections.TopMenuAndSearchSection;

public class HomePage {

    @Getter
    private TopMenuAndSearchSection topMenuAndSearchSection;
    private Page page;

    public HomePage(Page page) {
        this.page = page;
        this.topMenuAndSearchSection = new TopMenuAndSearchSection(page);
    }
}

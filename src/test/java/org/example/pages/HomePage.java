package org.example.pages;

import com.microsoft.playwright.Page;
import lombok.Getter;
import org.example.pages.sections.TopMenuAndSearchSection;
import org.example.pages.sections.TopNavigationSection;

@Getter
public class HomePage extends BasePage{

    private TopMenuAndSearchSection topMenuAndSearchSection;
    private TopNavigationSection topNavigationSection;

    public HomePage(Page page) {
        super(page);
        this.topMenuAndSearchSection = new TopMenuAndSearchSection(page);
        this.topNavigationSection = new TopNavigationSection(page);
    }
}

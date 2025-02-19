package org.example.pages.sections;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import org.example.pages.BasePage;

public class TopNavigationSection extends BasePage {

    private Locator languageSelector;
    private Locator english;
    private Locator selectedLanguage;


    public TopNavigationSection(Page page) {
        super(page);
        this.languageSelector = page.locator(".language-selector");
        this.english = page.locator("a[data-iso-code=en]");
        this.selectedLanguage = page.locator("span[class=expand-more]");
    }

    public void setPageLanguageToEN() {
        if (!selectedLanguage.innerText().equalsIgnoreCase("english")) {
            languageSelector.click();
            english.click();
        }

    }
}

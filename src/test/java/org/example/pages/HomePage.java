package org.example.pages;

import com.microsoft.playwright.Page;
import lombok.Getter;
import org.example.utils.Properties;

@Getter
public class HomePage extends BasePage{

    public HomePage(Page page) {
        super(page);
        page.navigate(Properties.getProperty("app.url"));
        setPageLanguageToEn();
    }
}

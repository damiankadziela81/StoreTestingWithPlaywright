package org.example;

import com.microsoft.playwright.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

class BaseTest {

    public static Playwright pw;
    protected static Browser browser;

    protected BrowserContext browserContext;
    protected Page page;

    @BeforeAll
    static void launchBrowser() {
        pw = Playwright.create();
        browser = pw.chromium().launch(new BrowserType.LaunchOptions()
                .setHeadless(false)
                .setSlowMo(1000));
    }

    @BeforeEach
    void createContextAndPage() {
        browserContext = browser.newContext();
        page = browserContext.newPage();
        page.setViewportSize(1920,1080);
    }

    @AfterEach
    void closeContext() {
        browserContext.close();
    }

    @AfterAll
    static void closeBrowser() {
        pw.close();
    }
}

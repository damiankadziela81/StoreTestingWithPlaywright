package org.example;

import com.microsoft.playwright.*;
import org.example.utils.StringUtils;
import org.junit.jupiter.api.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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

        browserContext.tracing().start(new Tracing.StartOptions()
                .setScreenshots(true)
                .setSnapshots(true)
                .setSources(true));

        page = browserContext.newPage();
        page.setViewportSize(1920,1080);
    }

    @AfterEach
    void closeContext(TestInfo testInfo) {
        String traceName = "traces/trace_"
                + StringUtils.removeParentheses(testInfo.getDisplayName()) + "_"
                + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss")) + ".zip";

        browserContext.close();
    }

    @AfterAll
    static void closeBrowser() {
        pw.close();
    }
}

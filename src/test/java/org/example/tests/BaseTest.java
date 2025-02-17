package org.example.tests;

import com.microsoft.playwright.*;
import org.example.utils.Properties;
import org.example.utils.StringUtils;
import org.junit.jupiter.api.*;

import java.nio.file.Paths;
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
                .setHeadless(Boolean.parseBoolean(Properties.getProperty("browser.headless")))
                .setSlowMo(Integer.parseInt(Properties.getProperty("browser.slow.mo"))));
    }

    @BeforeEach
    void createContextAndPage() {
        browserContext = browser.newContext();

        if(isTracingEnabled()) {
            browserContext.tracing().start(new Tracing.StartOptions()
                    .setScreenshots(true)
                    .setSnapshots(true)
                    .setSources(true));
        }

        page = browserContext.newPage();
        page.setViewportSize(1920,1080);
    }

    @AfterEach
    void closeContext(TestInfo testInfo) {

        if(isTracingEnabled()) {
            String traceName = "traces/trace_"
                    + StringUtils.removeParentheses(testInfo.getDisplayName()) + "_"
                    + LocalDateTime.now().format(DateTimeFormatter.ofPattern(Properties.getProperty("tracing.date.format"))) + ".zip";
            browserContext.tracing().stop(new Tracing.StopOptions().setPath(Paths.get(traceName)));
        }

        browserContext.close();
    }

    @AfterAll
    static void closeBrowser() {
        pw.close();
    }

    private boolean isTracingEnabled() {
        return Boolean.parseBoolean(Properties.getProperty("tracing.enabled"));
    }
}

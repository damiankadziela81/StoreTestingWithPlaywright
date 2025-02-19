package org.example.tests;

import com.microsoft.playwright.*;
import org.example.factory.BrowserFactory;
import org.example.utils.Properties;
import org.example.utils.StringUtils;
import org.junit.jupiter.api.*;

import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class BaseTest {

    private BrowserFactory browserFactory;
    private Browser browser;
    protected BrowserContext browserContext;
    protected Page page;

    @BeforeAll
    void launchBrowser() {
        browserFactory = new BrowserFactory();
        browser = browserFactory.getBrowser();
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
        page.navigate(Properties.getProperty("app.url"));
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
    void closeBrowser() {
        browserFactory.getPlaywright().close();
    }

    private boolean isTracingEnabled() {
        return Boolean.parseBoolean(Properties.getProperty("tracing.enabled"));
    }
}

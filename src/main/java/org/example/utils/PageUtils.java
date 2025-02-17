package org.example.utils;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;


public abstract class PageUtils {

    public static void waitForPageToLoad(Page page) {
        page.waitForLoadState(LoadState.NETWORKIDLE);
    }

}

package org.example.pages.sections.products;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import java.util.Arrays;

public class FilterBySection {

    private Page page;

    private Locator leftHandleSlider;
    private Locator priceFilter;


    public FilterBySection(Page page) {
        this.page = page;
        this.leftHandleSlider = page.locator(".ui-slider-handle").first();
        this.priceFilter = page.locator("#search_filters li p");
    }

    public void showLeftSlider() {
        System.out.println("x: " + leftHandleSlider.boundingBox().x);
        System.out.println("y: " + leftHandleSlider.boundingBox().y);
        System.out.println("height: " + leftHandleSlider.boundingBox().height);
        System.out.println("width " + leftHandleSlider.boundingBox().width);
    }

    public void filterProductsByPriceWithMouse(double fromPrice) {

        while (fromPrice > getParsedFilteredPriceValue()) {
            double x = leftHandleSlider.boundingBox().x;
            double y = leftHandleSlider.boundingBox().y;
            // calculate center of the handle
            double middleX = x + leftHandleSlider.boundingBox().width / 2;
            double middleY = y + leftHandleSlider.boundingBox().height / 2;

            leftHandleSlider.scrollIntoViewIfNeeded();
            page.mouse().move(middleX, middleY);
            page.mouse().down();
            page.mouse().move(x + 7.03, y);
            page.mouse().up();
            page.waitForCondition(() -> page.locator(".overlay__inner").isHidden());
        }

    }

    private Double getParsedFilteredPriceValue() {
        return Arrays.asList(page.locator("#search_filters li p").innerText().split(" "))
                .stream()
                .map(p -> p.replaceAll("zł",""))
                .map(Double::parseDouble)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Invalid price format"));
    }
}

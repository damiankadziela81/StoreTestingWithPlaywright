package org.example.pages.sections.products;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import org.example.pages.ArtPage;
import org.example.utils.StringUtils;

import java.util.Arrays;

public class FilterBySection {

    private Page page;

    private Locator leftHandleSlider;
    private Locator priceFilter;
    private Locator compositionCheckbox;


    public FilterBySection(Page page) {
        this.page = page;
        this.leftHandleSlider = page.locator(".ui-slider-handle").first();
        this.priceFilter = page.locator("#search_filters li p");
        this.compositionCheckbox = page.getByRole(AriaRole.CHECKBOX,new Page.GetByRoleOptions().setName("Matt paper"));
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

    public void filterProductsByPriceWithKeyboard(double fromPrice) {

        while (fromPrice > getParsedFilteredPriceValue()) {
            leftHandleSlider.press("ArrowRight");
            page.waitForCondition(() -> page.locator(".overlay__inner").isHidden());
        }
    }

    public ArtPage filterByComposition() {
        compositionCheckbox.click();
        page.waitForCondition(() -> page.locator(".overlay__inner").isHidden());
        return new ArtPage(page); // to "reload" products in PLP
    }

    private Double getParsedFilteredPriceValue() {
        return Arrays.asList(page.locator("#search_filters li p").innerText().split(" "))
                .stream()
                .map(p -> p.replaceAll(StringUtils.toUTF8("zł"),""))
                .map(Double::parseDouble)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Invalid price format"));
    }
}

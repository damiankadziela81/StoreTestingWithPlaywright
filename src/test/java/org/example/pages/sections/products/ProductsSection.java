package org.example.pages.sections.products;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import lombok.Getter;
import org.example.utils.StringUtils;

import java.util.List;

public class ProductsSection {

    private Page page;

    @Getter
    private List<Locator> products;

    public ProductsSection(Page page) {
        this.page = page;
        this.products = page.locator(".js-product").all();
    }

    private List<String> getProductPricesText() {
        return page.locator(".js-product .price").allInnerTexts();
    }

    public List<Double> getProductPrices() {
        return getProductPricesText()
                .stream()
                .map(p -> p.replaceAll(StringUtils.toUTF8("zł"),""))
                .map(Double::parseDouble)
                .toList();
    }
}

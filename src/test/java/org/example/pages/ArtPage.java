package org.example.pages;

import com.microsoft.playwright.Page;
import lombok.Getter;
import org.example.pages.sections.products.FilterBySection;
import org.example.pages.sections.products.ProductsSection;
import org.example.utils.PageUtils;

@Getter
public class ArtPage {

    private ProductsSection productsSection;

    private FilterBySection filterBySection;


    public ArtPage(Page page) {
        PageUtils.waitForPageToLoad(page);
        this.productsSection = new ProductsSection(page);
        this.filterBySection = new FilterBySection(page);
    }
}

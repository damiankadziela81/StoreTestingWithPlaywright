package org.example.pages;

import com.microsoft.playwright.Page;
import lombok.Getter;
import org.example.pages.sections.productDetailPage.AddToCartSection;
import org.example.pages.sections.productDetailPage.ProductCustomizationSection;
import org.example.utils.PageUtils;

@Getter
public class ProductDetailsPage extends BasePage{

    private ProductCustomizationSection productCustomizationSection;
    private AddToCartSection addToCartSection;

    public ProductDetailsPage(Page page) {
        super(page);
        PageUtils.waitForPageToLoad(page);
        this.productCustomizationSection = new ProductCustomizationSection(page);
        this.addToCartSection = new AddToCartSection(page);
    }
}

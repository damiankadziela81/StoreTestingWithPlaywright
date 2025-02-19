package org.example.pages.sections.searchResultsPage;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import lombok.Getter;
import org.example.dto.ProductDTO;
import org.example.pages.BasePage;
import org.example.pages.ProductDetailsPage;
import org.example.utils.StringUtils;

import java.util.List;

public class SearchResultsSection extends BasePage {

    @Getter
    private List<Locator> products;

    public SearchResultsSection(Page page) {
        super(page);
        products = page.locator(".js-product").all();
    }

    public ProductDetailsPage viewProductDetails(String productName) {
        ProductDTO productDTO = productsToDTO().stream()
                .filter(p -> p.getName().equals(productName))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Can't find product with naem: " + productName));

        productDTO.getThumbnail().click();

        return new ProductDetailsPage(page);
    }

    public List<ProductDTO> productsToDTO() {
        return products.stream()
                .map(p -> ProductDTO.builder()
                        .thumbnail(p.locator(".thumbnail"))
                        .name(p.locator(".product-title").innerText())
                        .price(Double.parseDouble(p.locator(".price").innerText().replaceAll(StringUtils.toUTF8("zł"),"")))
                        .build())
                .toList();
    }
}

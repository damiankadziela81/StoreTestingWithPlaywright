package org.example.tests;

import org.assertj.core.api.Assertions;
import org.example.pages.ArtPage;
import org.example.pages.HomePage;
import org.example.utils.Properties;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FilterByPriceTest extends BaseTest{

    private HomePage homePage;

    @BeforeEach
    void beforeEach() {
        homePage = new HomePage(page);
        page.navigate(Properties.getProperty("app.url"));
        homePage.getTopNavigationSection().setPageLanguageToEN();
    }

    @Test
    void shouldReturnProductsWithPriceGreaterThan40() {
        ArtPage artPage = homePage.getTopMenuAndSearchSection().clickArtLink();
        String newUrl = page.url() + "&q=Price-z%C5%82-40-44"; // adding query param to url hack
        page.navigate(newUrl);
        Assertions.assertThat(artPage.getProductsSection().getProductPrices().stream()
                .allMatch(p -> p > 40)).isTrue();
    }

    @Test
    void shouldReturnProductsWithPriceGreaterThan40WithSlider() {
        ArtPage artPage = homePage.getTopMenuAndSearchSection().clickArtLink();
        artPage.getFilterBySection().filterProductsByPriceWithMouse(40.0);
        page.waitForTimeout(3000);
        Assertions.assertThat(artPage.getProductsSection().getProductPrices().stream()
                .allMatch(p -> p > 40)).isTrue();
    }

}

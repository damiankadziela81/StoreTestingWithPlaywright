package org.example.tests;

import org.assertj.core.api.Assertions;
import org.example.pages.ArtPage;
import org.example.pages.HomePage;
import org.example.utils.Properties;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FilterByCompositionTest extends BaseTest{

    private HomePage homePage;

    @BeforeEach
    void beforeEach() {
        homePage = new HomePage(page);
        page.navigate(Properties.getProperty("app.url"));
        homePage.getTopNavigationSection().setPageLanguageToEN();
    }

    @Test
    void shouldReturn3ProductsWithMattPaperComposition() {
        ArtPage artPage = homePage.getTopMenuAndSearchSection().clickArtLink();
        ArtPage artPage1 = artPage.getFilterBySection().filterByComposition();
        // this is tricky - be careful on which objects/methods you work on
        Assertions.assertThat(artPage.getProductsSection().getProductPrices()).size().isEqualTo(3);
        // this is 7 because it was the number of products displayed before filter was applied, and
        // artPage objects holds the state (when constructor was used) even-though checkbox was already clicked
        Assertions.assertThat(artPage.getProductsSection().getProducts()).size().isEqualTo(7);
        Assertions.assertThat(artPage1.getProductsSection().getProducts()).size().isEqualTo(3);
    }
}

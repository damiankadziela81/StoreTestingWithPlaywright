package org.example.tests;

import org.assertj.core.api.Assertions;
import org.example.pages.HomePage;
import org.example.pages.SearchResultPage;
import org.example.utils.Properties;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class SearchTest extends BaseTest{

    private HomePage homePage;

    @BeforeEach
    void beforeEach() {
        homePage = new HomePage(page);
        page.navigate(Properties.getProperty("app.url"));
        homePage.getTopNavigationSection().setPageLanguageToEN();
    }

    @DisplayName("Search for products")
    @ParameterizedTest(name = "Search for {0} should return {1} product(s)")
    @MethodSource("searchData")
    void shouldReturnProductsByProductName(String productName, int productCount) {
        SearchResultPage searchResultPage = homePage.getTopMenuAndSearchSection().searchForProducts(productName);
        Assertions.assertThat(searchResultPage.getSearchResultsSection().getProducts().size()).isEqualTo(productCount);
    }

    private static Stream<Arguments> searchData() {
        return Stream.of(
                Arguments.of("mug", 5),
                Arguments.of("t-shirt", 1),
                Arguments.of("frame", 4),
                Arguments.of("notebook", 3),
                Arguments.of("graphics", 3)
        );
    }
}

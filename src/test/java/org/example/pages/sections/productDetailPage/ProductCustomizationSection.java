package org.example.pages.sections.productDetailPage;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import org.example.pages.BasePage;

public class ProductCustomizationSection extends BasePage {

    private Locator customMessageInput;
    private Locator saveCustomizationButton;
    private Locator customizationLabel;

    public ProductCustomizationSection(Page page) {
        super(page);
        this.customMessageInput = page.locator("#field-textField1");
        this.saveCustomizationButton = page.locator("button[name=submitCustomizedData]");
        this.customizationLabel = page.locator(".customization-message");
    }

    public void customizeProduct(String customMessage) {
        customMessageInput.fill(customMessage);
        saveCustomizationButton.click();
        page.waitForCondition(() -> customizationLabel.isVisible());
    }
}

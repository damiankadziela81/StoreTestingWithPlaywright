package org.example.dto;

import com.microsoft.playwright.Locator;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductDTO {

    private String name;
    private Double price;
    private Locator thumbnail;

}

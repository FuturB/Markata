package com.futureB.backend.dtos;

import lombok.*;

import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductCategoryDTO {
    private String name;
    private Set<ProductDTO> productSet;
}

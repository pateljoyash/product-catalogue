package com.ecommerce.service;

import java.util.List;

import com.ecommerce.dto.ProductDTO;
import com.ecommerce.entity.Product;

public interface ProductService {
    List<ProductDTO> getAllProducts();

    List<ProductDTO> getProductsByBrand(String val);

    List<ProductDTO> getProductsByColor(String val);

    List<ProductDTO> getProductsBySize(String val);

    List<ProductDTO> getProductsBySKU(String sku);

    List<ProductDTO> getProductsBySeller(String sku);
}

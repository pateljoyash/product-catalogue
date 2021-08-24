package com.ecommerce.service.impl;

import com.ecommerce.dto.*;
import com.ecommerce.entity.Brand;
import com.ecommerce.entity.Product;
import com.ecommerce.entity.ProductSeller;
import com.ecommerce.exception.ProductNotFound;
import com.ecommerce.repository.ProductSellerRepository;
import com.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductSellerRepository repo;

    @Autowired
    public ProductServiceImpl(ProductSellerRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<ProductDTO> getAllProducts() {
        List<ProductSeller> products = repo.findAll();
        return mapToProductsDTO(products);
    }

    @Override
    public List<ProductDTO> getProductsByBrand(String val) {
        List<ProductSeller> productSellers;
        try {
            Long id = Long.valueOf(val);
            productSellers = repo.findByBrandId(id);
        } catch (NumberFormatException ex) {
            productSellers = repo.findByBrandName(val);
        }
        validateResponse(productSellers);
        return mapToProductsDTO(productSellers);
    }

    @Override
    public List<ProductDTO> getProductsByColor(String val) {
        List<ProductSeller> productSellers;
        try {
            Long id = Long.valueOf(val);
            productSellers = repo.findByColorId(id);
        } catch (NumberFormatException ex) {
            productSellers = repo.findByColorName(val);
        }
        validateResponse(productSellers);
        return mapToProductsDTO(productSellers);
    }

    @Override
    public List<ProductDTO> getProductsBySize(String val) {
        List<ProductSeller> productSellers;
        try {
            Long id = Long.valueOf(val);
            productSellers = repo.findBySizeId(id);
        } catch (NumberFormatException ex) {
            productSellers = repo.findBySizeCode(val);
        }
        validateResponse(productSellers);
        return mapToProductsDTO(productSellers);
    }

    public List<ProductDTO> getProductsBySKU(String sku) {
        List<ProductSeller> productSellers = repo.findBySku(sku);
        validateResponse(productSellers);
        return mapToProductsDTO(productSellers);
    }

    public List<ProductDTO> getProductsBySeller(String val) {
        List<ProductSeller> productSellers;
        try {
            Long id = Long.valueOf(val);
            productSellers = repo.findBySellerId(id);
        } catch (NumberFormatException ex) {
            productSellers = repo.findBySellerSellerName(val);
        }
        validateResponse(productSellers);
        return mapToProductsDTO(productSellers);
    }

    private List<ProductDTO> mapToProductsDTO(List<ProductSeller> products) {
        List<ProductDTO> productsDTO = new ArrayList<>();
        Map<Product, List<ProductSeller>> productsMap = products.stream().collect(Collectors.groupingBy(ProductSeller::getProduct));

        productsMap.forEach((product, productSeller) ->
        {
            ProductDTO productDTO = new ProductDTO();
            productDTO.setProductName(product.getName());
            productDTO.setProductId(product.getId());
            productDTO.setCategory(product.getProductCategory().getName());

            List<SellerDTO> sellersInfo = new ArrayList<>();
            productSeller.forEach(ps -> {
                SellerDTO sellerDTO = new SellerDTO();
                sellerDTO.setSellerId(ps.getSeller().getId());
                sellerDTO.setSellerName(ps.getSeller().getSellerName());
                sellerDTO.setPrice(ps.getPrice());
                sellerDTO.setQuantity(ps.getQuantity());
                sellerDTO.setSku(ps.getSku());

                setBrandDTO(ps, sellerDTO);
                setColorDTO(ps, sellerDTO);
                setSIzeDTO(ps, sellerDTO);

                sellersInfo.add(sellerDTO);
            });
            productDTO.setSellerInfo(sellersInfo);
            productsDTO.add(productDTO);
        });
        return productsDTO;
    }

    private void setSIzeDTO(ProductSeller ps, SellerDTO sellerDTO) {
        SizeDTO sizeDTO = new SizeDTO();
        sizeDTO.setSizeCode(ps.getSize().getCode());
        sizeDTO.setSizeId(ps.getSize().getId());
        sellerDTO.setSizeDTO(sizeDTO);
    }

    private void setColorDTO(ProductSeller ps, SellerDTO sellerDTO) {
        ColorDTO colorDTO = new ColorDTO();
        colorDTO.setColorId(ps.getColor().getId());
        colorDTO.setColorName(ps.getColor().getName());
        sellerDTO.setColorDTO(colorDTO);
    }

    private void setBrandDTO(ProductSeller ps, SellerDTO sellerDTO) {
        Brand brand = ps.getBrand();
        BrandDTO brandDTO = new BrandDTO();
        brandDTO.setBrandName(brand.getName());
        brandDTO.setBrandId(brand.getId());
        sellerDTO.setBrandDTO(brandDTO);
    }

    private void validateResponse(List<ProductSeller> productSellers) {
        if (null == productSellers || productSellers.isEmpty()) {
            throw new ProductNotFound("No Products Found for Given Criteria");
        }
    }

}

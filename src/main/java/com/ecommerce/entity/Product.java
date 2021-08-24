package com.ecommerce.entity;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "PRODUCT")
public class Product {
    @Id
    private Long id;
    private String name;


    @ManyToOne
    @JoinColumn(name = "PRODUCT_CATEGORY_ID", nullable = false)
    private ProductCategory productCategory;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "product")
    private Set<ProductSeller> productSellers = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

 /*   public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }*/

   /* public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }*/

   /* public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }*/

    public ProductCategory getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(ProductCategory productCategory) {
        this.productCategory = productCategory;
    }

    public Set<ProductSeller> getProductSellers() {
        return productSellers;
    }

    public void setProductSellers(Set<ProductSeller> productSellers) {
        this.productSellers = productSellers;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id) && Objects.equals(name, product.name) && Objects.equals(productCategory, product.productCategory);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, productCategory);
    }
}

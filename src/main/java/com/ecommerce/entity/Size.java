package com.ecommerce.entity;

import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "SIZE")
public class Size {
    @Id
    private Long id;
    private String code;

    @OneToMany(mappedBy = "size")
    private Set<ProductSeller> products;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Set<ProductSeller> getProducts() {
        return products;
    }

    public void setProducts(Set<ProductSeller> products) {
        this.products = products;
    }
}





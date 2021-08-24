package com.ecommerce.service.impl;

import com.ecommerce.dto.BrandDTO;
import com.ecommerce.entity.Brand;
import com.ecommerce.repository.BrandRepository;
import com.ecommerce.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BrandServiceImpl implements BrandService {

    @Autowired
    private BrandRepository brandRepository;

    @Override
    public List<BrandDTO> getAllBrands() {
        List<Brand> brands = brandRepository.findAll();
        return mapToBrandDTO(brands);
    }

    private List<BrandDTO> mapToBrandDTO(List<Brand> brands) {
        List<BrandDTO> brandDTOList = new ArrayList<>();
        brands.forEach(brand -> {
            BrandDTO brandDTO = new BrandDTO();
            brandDTO.setBrandId(brand.getId());
            brandDTO.setBrandName(brand.getName());
            brandDTOList.add(brandDTO);
        });
        return brandDTOList;
    }
}

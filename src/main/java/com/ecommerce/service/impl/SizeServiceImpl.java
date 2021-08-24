package com.ecommerce.service.impl;

import com.ecommerce.dto.SizeDTO;
import com.ecommerce.entity.Size;
import com.ecommerce.repository.SizeRepository;
import com.ecommerce.service.SizeService;
import com.ecommerce.service.SizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SizeServiceImpl implements SizeService {

    @Autowired
    private SizeRepository sizeRepository;

    @Override
    public List<SizeDTO> getAllSize() {
        List<Size> sizes = sizeRepository.findAll();
        return mapToSizeDTO(sizes);
    }

    private List<SizeDTO> mapToSizeDTO(List<Size> sizes) {
        List<SizeDTO> sizeDTOList = new ArrayList<>();
        sizes.forEach(size -> {
            SizeDTO sizeDTO = new SizeDTO();
            sizeDTO.setSizeCode(size.getCode());
            sizeDTO.setSizeId(size.getId());
            sizeDTOList.add(sizeDTO);
        });
        return sizeDTOList;
    }
}

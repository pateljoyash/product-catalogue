package com.ecommerce.service.impl;

import com.ecommerce.dto.ColorDTO;
import com.ecommerce.entity.Color;
import com.ecommerce.repository.ColorRepository;
import com.ecommerce.service.ColorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ColorServiceImpl implements ColorService {

    @Autowired
    private ColorRepository colorRepository;

    @Override
    public List<ColorDTO> getAllColors() {
        List<Color> colors = colorRepository.findAll();
        return mapToColorDTO(colors);
    }

    private List<ColorDTO> mapToColorDTO(List<Color> colors) {
        List<ColorDTO> colorDTOList = new ArrayList<>();
        colors.forEach(color -> {
            ColorDTO colorDTO = new ColorDTO();
            colorDTO.setColorName(color.getName());
            colorDTO.setColorId(color.getId());
            colorDTOList.add(colorDTO);
        });
        return colorDTOList;
    }
}

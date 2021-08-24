package com.ecommerce.controller;

import com.ecommerce.dto.ColorDTO;
import com.ecommerce.entity.Color;
import com.ecommerce.service.ColorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/colors")
public class ColorController {

    @Autowired
    private ColorService colorService;

    @GetMapping
    public ResponseEntity<List<ColorDTO>> getAllColors() {
        List<ColorDTO> list = colorService.getAllColors();
        return new ResponseEntity<List<ColorDTO>>(list, new HttpHeaders(), HttpStatus.OK);
    }
}

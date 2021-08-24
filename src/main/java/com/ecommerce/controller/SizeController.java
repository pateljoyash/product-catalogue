package com.ecommerce.controller;

import com.ecommerce.dto.ColorDTO;
import com.ecommerce.dto.SizeDTO;
import com.ecommerce.service.ColorService;
import com.ecommerce.service.SizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/sizes")
public class SizeController {

    @Autowired
    private SizeService sizeService;

    @GetMapping
    public ResponseEntity<List<SizeDTO>> getAllSize() {
        List<SizeDTO> list = sizeService.getAllSize();
        return new ResponseEntity<List<SizeDTO>>(list, new HttpHeaders(), HttpStatus.OK);
    }
}

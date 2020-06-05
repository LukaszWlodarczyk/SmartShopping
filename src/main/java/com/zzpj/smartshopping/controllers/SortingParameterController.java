package com.zzpj.smartshopping.controllers;


import com.zzpj.smartshopping.model.SortingParameter;
import com.zzpj.smartshopping.repositories.SortingParameterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/sortParams")
public class SortingParameterController {

    @Autowired
    private SortingParameterRepository sortingParameterRepository;

    @GetMapping("/")
    public ResponseEntity<Iterable<SortingParameter>> getSortingParameters() {
        return ResponseEntity.ok(sortingParameterRepository.findAll());
    }

    @GetMapping(params = "name")
    public ResponseEntity<SortingParameter> getSortParamByName(@RequestParam String name) {
        Optional<SortingParameter> parameter = sortingParameterRepository.findByName(name);
        return parameter.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}

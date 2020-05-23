package com.zzpj.smartshopping.services.impl;

import com.zzpj.smartshopping.model.SortingParameter;
import com.zzpj.smartshopping.repositories.SortingParameterRepository;
import com.zzpj.smartshopping.services.OfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OfferServiceImpl implements OfferService {

    @Autowired
    private SortingParameterRepository sortingParameterRepository;


    @Override
    public boolean checkSortingParameters(String sortParam, String direction) {
        Optional<Sort.Direction> dir = Sort.Direction.fromOptionalString(direction);
        Optional<SortingParameter> param = sortingParameterRepository.findByName(sortParam);
        return dir.isPresent() && param.isPresent();
    }
}

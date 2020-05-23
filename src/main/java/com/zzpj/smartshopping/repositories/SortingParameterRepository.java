package com.zzpj.smartshopping.repositories;

import com.zzpj.smartshopping.model.SortingParameter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SortingParameterRepository extends JpaRepository<SortingParameter,Long> {
    Optional<SortingParameter> findByName(String name);
}

package com.zzpj.smartshopping.repositories;

import com.zzpj.smartshopping.model.Category;
import com.zzpj.smartshopping.model.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {
}

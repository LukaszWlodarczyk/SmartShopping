package com.zzpj.smartshopping.repositories;

import com.zzpj.smartshopping.model.Offer;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OfferRepository extends JpaRepository<Offer, Long> {
    List<Offer> findAllByOrderByIsFavouriteDesc();

    List<Offer> findAllByOrderByIsFavouriteDesc(Sort sort);

    List<Offer> findOffersByCategoryOrderByIsFavouriteDesc(String category);

    List<Offer> findOffersByCategoryOrderByIsFavouriteDesc(String category, Sort sort);

    List<Offer> findByIsGoodPriceTrueOrderByIsFavouriteDesc();

    List<Offer> findByIsGoodPriceTrueOrderByIsFavouriteDesc(Sort sort);

    List<Offer> findByIsGoodPriceTrueAndCategoryOrderByIsFavouriteDesc(String category);

    List<Offer> findByIsGoodPriceTrueAndCategoryOrderByIsFavouriteDesc(String category, Sort sort);

    List<Offer> findOffersByDisplayedNameContainingIgnoreCaseOrderByIsFavouriteDesc(String name);

}

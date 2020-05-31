package com.zzpj.smartshopping.repositories;

import com.zzpj.smartshopping.model.Offer;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OfferRepository extends JpaRepository<Offer, Long> {
    List<Offer> findAllByOrderByIsFavouriteDesc();

    List<Offer> findAllByOrderByIsFavouriteDesc(Sort sort);


    List<Offer> findOffersByCategoryNameOrderByIsFavouriteDesc(String name);

    List<Offer> findOffersByCategoryNameOrderByIsFavouriteDesc(String name, Sort sort);

    List<Offer> findOffersByCategoryIdOrderByIsFavouriteDesc(Long id);

    List<Offer> findOffersByCategoryIdOrderByIsFavouriteDesc(Long id, Sort sort);

    List<Offer> findByIsGoodPriceTrueOrderByIsFavouriteDesc();

    List<Offer> findByIsGoodPriceTrueOrderByIsFavouriteDesc(Sort sort);

    List<Offer> findByIsGoodPriceTrueAndCategoryIdOrderByIsFavouriteDesc(Long id);

    List<Offer> findByIsGoodPriceTrueAndCategoryIdOrderByIsFavouriteDesc(Long id, Sort sort);

    List<Offer> findByIsGoodPriceTrueAndCategoryNameOrderByIsFavouriteDesc(String name);

    List<Offer> findByIsGoodPriceTrueAndCategoryNameOrderByIsFavouriteDesc(String name, Sort sort);

    @Query("Select o from Offer o where o.offerName like %:name% order by o.isFavourite desc")
    List<Offer> findOffersByOfferNameOrderByIsFavouriteDesc(String name);

}

package com.zzpj.smartshopping.repositories;

import com.zzpj.smartshopping.model.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OfferRepository extends JpaRepository<Offer,Long> {
    List<Offer> findOffersByCategoryName(String name);
    List<Offer> findOffersByCategoryId(Long id);
    @Query("Select o from Offer o where o.offerName like %:name%")
    List<Offer> findOffersByOfferName(String name);

}

package com.zzpj.smartshopping.repositories;

import com.zzpj.smartshopping.model.OfferHistory;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface OfferHistoryRepository extends JpaRepository <OfferHistory, Long> {
    List<OfferHistory> findAllByOfferId(Long offerId);
}

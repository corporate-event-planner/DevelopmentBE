package com.cep.corporateeventplanner.service;


import com.cep.corporateeventplanner.model.Purchase;

import java.util.List;

public interface PurchaseService {
    List<Purchase> findAll();

    Purchase findById(long id);

    void create(Purchase purchase);

    void updatePurchase(Purchase purchase, long id);

    void deletePurchase(long id);
}
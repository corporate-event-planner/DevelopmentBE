package com.cep.corporateeventplanner.service;

import com.cep.corporateeventplanner.model.Purchase;
import com.cep.corporateeventplanner.repo.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
public class PurchaseServiceImpl implements PurchaseService {

    @Autowired
    PurchaseRepository repo;

    @Override
    public List<Purchase> findAll() {
        List<Purchase> list = new ArrayList<>();
        repo.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    @Override
    public Purchase findById(long id) {
        return repo.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public void create(Purchase purchase) {
        repo.save(purchase);
    }

    @Override
    public void updatePurchase(Purchase purchase, long id) {

    }

    @Override
    public void deletePurchase(long id) {
        if (repo.findById(id).isPresent()){
            repo.deleteById(id);
        }else{
            throw new EntityNotFoundException();
        }

    }
}

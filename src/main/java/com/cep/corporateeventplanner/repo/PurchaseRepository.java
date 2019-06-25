package com.cep.corporateeventplanner.repo;

import com.cep.corporateeventplanner.model.Purchase;
import org.springframework.data.repository.CrudRepository;

public interface PurchaseRepository extends CrudRepository<Purchase, Long> {
}

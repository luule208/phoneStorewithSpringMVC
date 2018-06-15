package com.tma.intern.luule.phonestore.repository;


import com.tma.intern.luule.phonestore.model.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SupplierRepository extends JpaRepository<Supplier, Integer> {
    List<Supplier> findAllByName(String name);

}

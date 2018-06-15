package com.tma.intern.luule.phonestore.service.impl;


import com.tma.intern.luule.phonestore.model.Supplier;
import com.tma.intern.luule.phonestore.repository.SupplierRepository;
import com.tma.intern.luule.phonestore.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class SupplierServiceImpl implements SupplierService {

    @Autowired
    private SupplierRepository supplierRepository;

    @Transactional(readOnly = true)
    public List<Supplier> findAll() {
        return supplierRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Supplier> findAllbyName(String name) {
        return supplierRepository.findAllByName(name);
    }

    @Transactional
    public Supplier findById(Integer id) {
        return supplierRepository.getOne(id);
    }

    @Transactional
    public Supplier addSupplier(Supplier supplier) {

        return supplierRepository.save(supplier);
    }

    @Transactional
    public void deteleSupplier(Integer id) {

        supplierRepository.deleteById(id);
    }
}

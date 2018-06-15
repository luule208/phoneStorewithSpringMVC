package com.tma.intern.luule.phonestore.service;

import com.tma.intern.luule.phonestore.model.Supplier;

import java.util.List;

public interface SupplierService {

    public List<Supplier> findAll();

    public List<Supplier> findAllbyName(String name);

    public Supplier findById(Integer id);

    public Supplier addSupplier(Supplier supplier);

    public void deteleSupplier(Integer id);


}

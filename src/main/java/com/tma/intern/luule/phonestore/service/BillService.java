package com.tma.intern.luule.phonestore.service;

import com.tma.intern.luule.phonestore.model.Bill;
import com.tma.intern.luule.phonestore.model.Phone;

import java.util.List;

public interface BillService {

    public List<Bill> findAll();

    public Bill findById(Integer id);

    public Bill addBill(Bill bill);

    public void deteleBill(Integer id) throws Exception;

    public List<Bill> findAllbyName(String name);

    List<Bill> findByJPQLQueryforPrice( Long price );


}

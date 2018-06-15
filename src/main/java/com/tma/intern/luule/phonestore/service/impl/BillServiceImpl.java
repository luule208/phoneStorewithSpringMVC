package com.tma.intern.luule.phonestore.service.impl;


import com.tma.intern.luule.phonestore.model.Bill;
import com.tma.intern.luule.phonestore.model.Phone;
import com.tma.intern.luule.phonestore.repository.BillRepository;
import com.tma.intern.luule.phonestore.service.BillService;
import com.tma.intern.luule.phonestore.service.PhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class BillServiceImpl implements BillService {

    @Autowired
    private BillRepository billRepository;
    @Autowired
    PhoneService phoneService;


    @Transactional(readOnly = true)
    public List<Bill> findAll() {
        return billRepository.findAll();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public List<Bill> findAllbyName(String name) {
        return billRepository.findAllByUsername(name);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Bill> findByJPQLQueryforPrice(Long price) {
        return billRepository.findJPQLQueryforPrice(price);
    }

    @Transactional
    public Bill findById(Integer id) {
        return billRepository.getOne(id);
    }

    @Transactional
    public Bill addBill(Bill bill) {
        return billRepository.save(bill);
    }

    @Transactional
    public void deteleBill(Integer id) throws Exception {
        Bill bill = billRepository.findAllById(id);
        if (bill == null) {
            throw new Exception("Bill with id " + id + " does not exist in database");
        }
        billRepository.deleteById(id);
    }



}

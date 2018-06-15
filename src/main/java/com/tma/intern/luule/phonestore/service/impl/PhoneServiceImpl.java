package com.tma.intern.luule.phonestore.service.impl;

import com.tma.intern.luule.phonestore.model.Bill;
import com.tma.intern.luule.phonestore.model.Phone;
import com.tma.intern.luule.phonestore.repository.PhoneRepository;
import com.tma.intern.luule.phonestore.service.BillService;
import com.tma.intern.luule.phonestore.service.PhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Component
public class PhoneServiceImpl implements PhoneService {

    @Autowired
    private PhoneRepository phonerepository;

    @Autowired
    BillService billService;

    @Transactional(readOnly = true)
    @Override
    public List<Phone> findAll() {
        return phonerepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public List<Phone> findByDescriptionAndName(String description,String name ) {
        return phonerepository.findByDescriptionAndName(description,name);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Phone addPhone(Phone phone) {
        return phonerepository.save(phone);
    }

    @Transactional
    @Override
    public Phone findById(Integer id) {
        return phonerepository.findAllById(id);
    }

    @Transactional(readOnly = false)
    @Override
    public void detelePhoneById(Integer id) throws Exception {
        Phone phone = phonerepository.findAllById(id);
        if (phone == null) {
            throw new Exception("Phone with id " + id + " does not exist in database");
        }

        phonerepository.delete(phone);
    }

    @Transactional
    @Override
    public void detelePhone(Phone phone) {
        phonerepository.delete(phone);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Phone> findByName(String name) {
        return phonerepository.findAllByName(name);
    }

    public void addPhonev2(Phone phone) {

        phonerepository.save(phone);
    }

    public List<Phone> getPage(int pageNumber,int PAGESIZE) {
        PageRequest request = new PageRequest(pageNumber - 1, PAGESIZE, Sort.Direction.DESC, "name");
        return phonerepository.findAll(request).getContent();
    }

    @Override
    @Transactional
    public Page<Phone> listAllByPage(Pageable pageable) {
        return phonerepository.findAll(pageable);
    }

    @Override
    @Transactional
    public List<Phone> findAllByNativeQueryforDescription(String description) {
        return phonerepository.findAllByNativeQueryforDescription(description);
    }

}

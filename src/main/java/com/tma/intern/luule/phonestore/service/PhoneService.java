package com.tma.intern.luule.phonestore.service;

import com.tma.intern.luule.phonestore.model.Phone;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PhoneService {
    public List<Phone> findAll();

    public Phone addPhone(Phone phone);

    public Phone findById(Integer id);

    public void detelePhone(Phone phone);

    public void detelePhoneById(Integer id) throws Exception;

    public List<Phone> findByName(String name);

    public void addPhonev2(Phone phone);

    public List<Phone> findByDescriptionAndName(String description,String name );

    public List<Phone> getPage(int pageNumber,int PAGESIZE);

    Page<Phone> listAllByPage(Pageable pageable);

    List<Phone> findAllByNativeQueryforDescription(String description);

}

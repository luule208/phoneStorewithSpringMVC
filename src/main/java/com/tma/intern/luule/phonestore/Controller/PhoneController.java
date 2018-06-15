package com.tma.intern.luule.phonestore.Controller;

import com.tma.intern.luule.phonestore.model.Phone;
import com.tma.intern.luule.phonestore.service.BillService;
import com.tma.intern.luule.phonestore.service.PhoneService;
import com.tma.intern.luule.phonestore.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.List;

@RestController
@RequestMapping("/phones")
public class PhoneController {

    public static final Logger logger = LoggerFactory.getLogger(PhoneController.class);

    @Autowired
    private PhoneService phoneService;

    @RequestMapping(method = RequestMethod.GET, headers = "Accept=application/json", produces = "application/json")
    public ResponseEntity<List<Phone>> listall() {
        List<Phone> phones = phoneService.findAll();

        if (phones.isEmpty()) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(phones, HttpStatus.OK);
    }

    @RequestMapping(params = {"name"}, method = RequestMethod.GET, headers = "Accept=application/json", produces = "application/json")
    public ResponseEntity<List<Phone>> listallbyName(@RequestParam("description") String name) {

        List<Phone> phones = phoneService.findByName(name);

        if (phones.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(phones, HttpStatus.OK);

    }

    @RequestMapping(params = {"description"},method = RequestMethod.GET, headers = "Accept=application/json", produces = "application/json")
    public ResponseEntity<List<Phone>> listall(@RequestParam("description") String description) {

        List<Phone> phones = phoneService.findAllByNativeQueryforDescription(description);

        if (phones.isEmpty()) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(phones, HttpStatus.OK);
    }

    @RequestMapping(params = {"description","name"},method = RequestMethod.GET, headers = "Accept=application/json", produces = "application/json")
    public ResponseEntity<List<Phone>> listall(@RequestParam("description") String description ,@RequestParam("name") String name) {

        List<Phone> phones = phoneService.findByDescriptionAndName(description,name);

        if (phones.isEmpty()) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(phones, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, headers = "Accept=application/json", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> createPhone(@RequestBody Phone phone) {
        logger.info("Creating a Phone : {}", phone);
        phoneService.addPhone(phone);
        return new ResponseEntity<>(phone, HttpStatus.CREATED);

    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, headers = "Accept=application/json", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> updatePhone(@PathVariable(value = "id") Integer id, @RequestBody Phone phoneDetails) {
        logger.info("Updating Phone with id {}", id);

        Phone phone = phoneService.findById(id);
        if (phone == null) {
            logger.error("Unable to update. Phone with id {} not found.", id);
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        } else {
            phone.setName(phoneDetails.getName());
            phone.setDescription(phoneDetails.getDescription());
            phone.setSupplier(phoneDetails.getSupplier());
            phoneService.addPhone(phone);
            return new ResponseEntity<>(phone, HttpStatus.OK);
        }
    }


    @RequestMapping(value = "/{id_phone}", method = RequestMethod.DELETE, headers = "Accept=application/json", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> detelePhoneById(@PathVariable(value = "id_phone") Integer id) throws Exception {
        logger.info("Deleting Phone with id {}", id);
        Phone phone = phoneService.findById(id);
        if (phone == null) {
            logger.error("Unable to delete .Phone with id {} not found.", id);
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        } else {
            phoneService.detelePhoneById(id);
            return new ResponseEntity<>("delete complete", HttpStatus.OK);
        }
    }

    @RequestMapping(method = RequestMethod.DELETE, headers = "Accept=application/json", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> deletePhone(@RequestBody Phone phone) {
        logger.info("Deleting Phone: {}", phone);
        Phone p = phoneService.findById(phone.getId());
        if (p == null) {
            logger.error("Unable to delete .Phone : {} not found.", phone);
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        } else {
            phoneService.detelePhone(p);
            return new ResponseEntity<>("delete complete", HttpStatus.OK);
        }
    }

    @RequestMapping(method=RequestMethod.GET)
    public ResponseEntity<Page<Phone>> list(Pageable pageable){
        Page<Phone> phones = phoneService.listAllByPage(pageable);

        return new ResponseEntity<>(phones, HttpStatus.OK);

    }


}

package com.tma.intern.luule.phonestore.controller;


import com.tma.intern.luule.phonestore.model.Supplier;
import com.tma.intern.luule.phonestore.service.SupplierService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/suppliers")
public class SupplierController {

    public static final Logger logger = LoggerFactory.getLogger(SupplierController.class);

    @Autowired
    private SupplierService supplierService;


    @RequestMapping(method = RequestMethod.GET, headers = "Accept=application/json", produces = "application/json")
    public ResponseEntity<List<Supplier>> listall() {
        List<Supplier> suppliers = supplierService.findAll();
        if (suppliers.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(suppliers, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, headers = "Accept=application/json", produces = "application/json")
    public ResponseEntity<Supplier> findById(@PathVariable(value = "id") Integer id) {

        Supplier supplier = supplierService.findById(id);
        if (supplier == null) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(supplier, HttpStatus.OK);
    }


    @RequestMapping(value = "/{name}", method = RequestMethod.GET, headers = "Accept=application/json", produces = "application/json")
    public ResponseEntity<List<Supplier>> listallByName(@PathVariable(value = "name") String name) {

        List<Supplier> suppliers = supplierService.findAllbyName(name);
        if (suppliers.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(suppliers, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, headers = "Accept=application/json", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> create(@RequestBody Supplier supplier) {

        logger.info("Creating Supplier : {}", supplier.toString());
        supplierService.addSupplier(supplier);
        return new ResponseEntity<>(supplier, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, headers = "Accept=application/json", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> updatePhone(@PathVariable(value = "id") Integer id, @RequestBody Supplier Details) {

        logger.info("Updating Supplier with id {}", id);
        Supplier supplier = supplierService.findById(id);
        if (supplier == null) {
            logger.error("Unable to update. Supplier with id {} not found.", id);
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        } else {
            supplier.setName(Details.getName());
            supplierService.addSupplier(supplier);

        }
        return new ResponseEntity<>(supplier, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> deletePhone(@PathVariable(value = "id") Integer Id) {
        logger.info("Deleting Supplier ");

        supplierService.deteleSupplier(Id);
        return new ResponseEntity<Supplier>(HttpStatus.OK);

    }

}

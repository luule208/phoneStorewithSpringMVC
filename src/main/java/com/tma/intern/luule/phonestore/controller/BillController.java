package com.tma.intern.luule.phonestore.controller;


import com.tma.intern.luule.phonestore.model.Bill;
import com.tma.intern.luule.phonestore.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@RestController
@RequestMapping("/bills")
public class BillController {

    public static final Logger logger = LoggerFactory.getLogger(BillController.class);

    @Autowired
    private BillService billService;


    @RequestMapping(method = RequestMethod.GET, headers = "Accept=application/json", produces = "application/json")
    public ResponseEntity<List<Bill>> listall() throws Exception {

        List<Bill> bill = billService.findAll();

        if (bill.isEmpty()) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<Bill>>(bill, HttpStatus.OK);
    }


    @RequestMapping(params = "name", method = RequestMethod.GET, headers = "Accept=application/json", produces = "application/json")
    public ResponseEntity<List<Bill>> listall(@RequestParam("name") String name) throws Exception {

        List<Bill> bill = billService.findAllbyName(name);

        if (bill.isEmpty()) {
            //return new ResponseEntity(HttpStatus.NOT_FOUND);
            throw new Exception();
        }
        return new ResponseEntity<List<Bill>>(bill, HttpStatus.OK);
    }


    @RequestMapping(method = RequestMethod.POST, headers = "Accept=application/json", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> createBill(@RequestBody Bill bill) {
        logger.info("Creating a Bill : {}", bill);
        return new ResponseEntity<Bill>(billService.addBill(bill), HttpStatus.CREATED);
    }


    @RequestMapping(method = RequestMethod.PUT, headers = "Accept=application/json", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> updatePhone(@RequestBody Bill bill) throws Exception {

        logger.info("Updating Bill with id {}");

        Bill b = billService.findById(bill.getId());
        if (b == null) {

            logger.error("Unable to update. Bill with id {} not found.", bill.getId());
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        } else {
            b.setPrice(bill.getPrice());
            b.setUsername(bill.getUsername());
            b.setPhones(bill.getPhones());
            billService.addBill(b);
            return new ResponseEntity<>("uppdate complete", HttpStatus.OK);
        }

    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> deletePhone(@PathVariable(value = "id") Integer id) throws Exception {

        logger.info("Deleting Bill with id {}", id);

        Bill bill = billService.findById(id);

        if (bill == null) {
            logger.error("Unable to delete .Bill with id {} not found.", id);
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        } else {
            billService.deteleBill(id);
            return new ResponseEntity<>("delete complete", HttpStatus.OK);

        }

    }

    @RequestMapping(params = {"price"}, method = RequestMethod.GET, headers = "Accept=application/json", produces = "application/json")
    public ResponseEntity<List<Bill>> listall(@RequestParam("price") Long price) {

        List<Bill> bills = billService.findByJPQLQueryforPrice(price);

        if (bills.isEmpty()) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(bills, HttpStatus.OK);
    }


}

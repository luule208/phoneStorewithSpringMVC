package com.tma.intern.luule.phonestore.repository;

import com.tma.intern.luule.phonestore.model.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface BillRepository extends JpaRepository<Bill, Integer> {

    Bill findAllById(Integer id);
    List<Bill> findAllByUsername(String name );


    @Query("select b from Bill b where b.price = :price ")
    List<Bill> findJPQLQueryforPrice(@Param("price") Long price );

}

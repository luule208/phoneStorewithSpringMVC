package com.tma.intern.luule.phonestore.repository;

import com.tma.intern.luule.phonestore.model.Phone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PhoneRepository extends JpaRepository<Phone, Integer> {
    Phone findAllById(Integer id);

    List<Phone> findAllByName(String Name);

    List<Phone> findByDescriptionAndName(String description, String name);

    @Query(value = "SELECT * FROM phones where description = ?1 ", nativeQuery = true)
    List<Phone> findAllByNativeQueryforDescription(String description);
}

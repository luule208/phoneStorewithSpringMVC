package com.tma.intern.luule.phonestore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.tma.intern.luule.phonestore.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    User findAllById(Integer id);

    User findByUsername(String username);
}

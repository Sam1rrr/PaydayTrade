package com.gradiant.paydaytrade.repository;

import com.gradiant.paydaytrade.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByMail(String mail);
   Optional<User> findById(int id);
    User findByUserEmailIgnoreCase(String emailId);
    Boolean existsByUserEmail(String email);


//    @Transactional
//    @Modifying
//    @Query("UPDATE User a " +
//            "SET a.enabled = TRUE WHERE a.mail = ?1")
//    int enableAppUser(String mail);
}

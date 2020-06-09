package com.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bookstore.models.UserBilling;

@Repository
public interface BillingRepository extends JpaRepository<UserBilling, Long>{

}

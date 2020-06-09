package com.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bookstore.models.UserPayment;

@Repository
public interface PaymentRepository extends JpaRepository<UserPayment, Long> {

}

package com.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookstore.models.UserShipping;

public interface ShippingRepository extends JpaRepository<UserShipping, Long>{

}

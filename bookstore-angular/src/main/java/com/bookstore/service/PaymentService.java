package com.bookstore.service;

import java.util.Optional;

import com.bookstore.models.UserPayment;

public interface PaymentService {

	public Optional<UserPayment> findById(Long id);

	void removeById(Long id);
}

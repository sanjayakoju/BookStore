package com.bookstore.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookstore.models.UserPayment;
import com.bookstore.repository.UserPaymentRepository;

@Service
public class UserPaymentServiceImpl {
	@Autowired
	private UserPaymentRepository userPaymentRepository;
	
	public Optional<UserPayment> findById(Long id) {
		return userPaymentRepository.findById(id);
	}
	
	public void removeById(Long id) {
		userPaymentRepository.deleteById(id);
	}
	
}

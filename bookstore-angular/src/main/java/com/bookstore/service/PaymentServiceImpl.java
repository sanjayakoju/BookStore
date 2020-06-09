package com.bookstore.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookstore.models.UserPayment;
import com.bookstore.repository.PaymentRepository;
import com.bookstore.repository.UserRepository;

@Service
public class PaymentServiceImpl implements PaymentService{
	
	@Autowired
	PaymentRepository paymentRepository;

	@Override
	public Optional<UserPayment> findById(Long id) {
		
		return paymentRepository.findById(id);
	}

	@Override
	public void removeById(Long id) {
		paymentRepository.deleteById(id);
		
	}

}

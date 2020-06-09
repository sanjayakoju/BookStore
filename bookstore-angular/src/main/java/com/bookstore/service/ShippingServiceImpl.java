package com.bookstore.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookstore.models.UserShipping;
import com.bookstore.repository.ShippingRepository;

@Service
public class ShippingServiceImpl implements ShippingService{
	
	@Autowired
	ShippingRepository shippingRepository;

	@Override
	public Optional<UserShipping> findById(long id) {
		
		return shippingRepository.findById(id);
	}

	@Override
	public void removeById(long id) {
		shippingRepository.deleteById(id);
		
	}

}

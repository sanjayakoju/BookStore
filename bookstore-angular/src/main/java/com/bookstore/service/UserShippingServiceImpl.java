package com.bookstore.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookstore.models.UserShipping;
import com.bookstore.repository.UserShippingRepository;
import com.bookstore.service.UserShippingService;

@Service
public class UserShippingServiceImpl implements UserShippingService{
	
	@Autowired
	private UserShippingRepository userShippingRepository;
	
	public Optional<UserShipping> findById(Long id) {
		return userShippingRepository.findById(id);
	}
	
	public void removeById(Long id) {
		userShippingRepository.deleteById(id);
	}
}

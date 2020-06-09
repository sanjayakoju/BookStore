package com.bookstore.service;

import java.util.Optional;

import com.bookstore.models.UserShipping;

public interface UserShippingService {
	
	Optional<UserShipping> findById(Long id);
	
	void removeById(Long id);

}

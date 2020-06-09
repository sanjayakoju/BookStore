package com.bookstore.service;

import java.util.Optional;

import com.bookstore.models.UserShipping;

public interface ShippingService {

	Optional<UserShipping> findById(long id);
	
	void removeById(long id);

}

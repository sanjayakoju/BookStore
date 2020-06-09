package com.bookstore.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookstore.models.Order;
import com.bookstore.models.User;
import com.bookstore.service.UserService;

@RestController
@RequestMapping("/order")
public class OrderController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/getOrderList")
	public List<com.bookstore.models.Order> getOrderList(Principal principal) {
		User user = userService.findByUsername(principal.getName());
		List<Order> orderList = user.getOrderList();
		
		return orderList;
	}

}

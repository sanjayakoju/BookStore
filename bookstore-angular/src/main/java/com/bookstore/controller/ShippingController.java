package com.bookstore.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookstore.models.User;
import com.bookstore.models.UserShipping;
import com.bookstore.service.ShippingService;
import com.bookstore.service.UserService;

@RestController
@RequestMapping("/shipping")
public class ShippingController {

	@Autowired
	UserService userService;
	
	@Autowired
	ShippingService shippingService;
	
	@PostMapping("/add")
	public ResponseEntity addNewUserShippingPost(
			@RequestBody UserShipping userShipping, Principal principal
			) {
		User user = userService.findByUsername(principal.getName());
		userService.updateUserShipping(userShipping, user);
		
		return new ResponseEntity("Shipping Added(Updated) Successfully!", HttpStatus.OK);
	}
	
	@GetMapping("/getUserShippingList")
	public List<UserShipping> getUserShippingList(
			Principal principal
			){
		User user = userService.findByUsername(principal.getName());
		List<UserShipping> userShippingList = user.getUserShippingList();
		
		return userShippingList;
	}
	
	@PostMapping("/remove")
	public ResponseEntity removeUserShippingPost(
			@RequestBody String id,
			Principal principal
			) {
		shippingService.removeById(Long.parseLong(id));
		return new ResponseEntity("Shipping Removed Successfully!", HttpStatus.OK);
	}
	
	@PostMapping("/setDefault")
	public ResponseEntity setDefaultUserShippingPost(
			@RequestBody String id, Principal principal
			){
		User user = userService.findByUsername(principal.getName());
		userService.setUserDefaultShipping(Long.parseLong(id), user);
		
		return new ResponseEntity("Set Default Shipping Successfully!", HttpStatus.OK);
	}
}

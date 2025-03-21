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
import com.bookstore.models.UserBilling;
import com.bookstore.models.UserPayment;
import com.bookstore.service.PaymentService;
import com.bookstore.service.UserService;

@RestController
@RequestMapping("/payment")
public class PaymentController {

	@Autowired
	UserService userService;

	@Autowired
	PaymentService paymentService;

	@PostMapping("/add")
	public ResponseEntity addNewCreditCardPost(@RequestBody UserPayment userPayment, Principal principal) {
		User user = userService.findByUsername(principal.getName());

		UserBilling userBilling = userPayment.getUserBilling();

		userService.updateUserBilling(userBilling, userPayment, user);

		return new ResponseEntity("Payment Added(Updated) Successfully!", HttpStatus.OK);
	}

	@PostMapping("/remove")
	public ResponseEntity removePaymentPost(@RequestBody String id, Principal principal) {
//		User user = userService.findByUsername(principal.getName());

		paymentService.removeById(Long.valueOf(id));

		return new ResponseEntity("Payment Removed Successfully!", HttpStatus.OK);
	}

	@PostMapping("/setDefault")
	public ResponseEntity setDefaultPaymentPost(@RequestBody String id, Principal principal) {
		User user = userService.findByUsername(principal.getName());

		userService.setUserDefaultPayment(Long.parseLong(id), user);

		return new ResponseEntity("Payment Removed Successfully!", HttpStatus.OK);
	}

	@GetMapping("/getUserPaymentList")
	public List<UserPayment> getUserPaymentList(Principal principal) {
		User user = userService.findByUsername(principal.getName());

		List<UserPayment> userPaymentList = user.getUserPaymentList();

		return userPaymentList;
	}

}

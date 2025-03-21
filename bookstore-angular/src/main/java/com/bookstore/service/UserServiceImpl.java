package com.bookstore.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bookstore.models.User;
import com.bookstore.models.UserBilling;
import com.bookstore.models.UserPayment;
import com.bookstore.models.UserShipping;
import com.bookstore.repository.BillingRepository;
import com.bookstore.repository.PaymentRepository;
import com.bookstore.repository.RoleRepository;
import com.bookstore.repository.ShippingRepository;
import com.bookstore.repository.UserRepository;
import com.bookstore.security.UserRole;

@Service
public class UserServiceImpl implements UserService {

	private static final Logger LOG = LoggerFactory.getLogger(UserService.class);

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	BillingRepository billingRepository;

	@Autowired
	PaymentRepository paymentRepository;

	@Autowired
	ShippingRepository shippingRepository;

	@Override
	public User createUser(User user, Set<UserRole> userRole) {
		User localUser = userRepository.findByUsername(user.getUsername());
		if (localUser != null) {
			LOG.info("User with username {} already exist. Nothing will be done. ", user.getUsername());
		} else {
			for (UserRole ur : userRole) {
				roleRepository.save(ur.getRole());
			}
			user.getUserRoles().addAll(userRole);

			user.setUserPaymentList(new ArrayList<UserPayment>());

			localUser = userRepository.save(user);
		}
		return localUser;
	}

	@Override
	public User findByUsername(String username) {

		return userRepository.findByUsername(username);
	}

	@Override
	public User findByEmail(String email) {

		return userRepository.findByEmail(email);
	}

	@Override
	public User save(User user) {

		return userRepository.save(user);
	}

	@Override
	public User findById(long id) {

		return userRepository.findById(id);
	}

	@Override
	public void updateUserPaymentInfo(UserBilling userBilling, UserPayment userPayment, User user) {
		save(user);
		billingRepository.save(userBilling);
		paymentRepository.save(userPayment);

	}

	@Override
	public void updateUserBilling(UserBilling userBilling, UserPayment userPayment, User user) {
		userPayment.setUser(user);
		userPayment.setUserBilling(userBilling);
		userPayment.setDefaultPayment(true);
		userBilling.setUserPayment(userPayment);
		user.getUserPaymentList().add(userPayment);
		save(user);

	}

	@Override
	public void setUserDefaultPayment(Long userPaymentId, User user) {
		List<UserPayment> userPaymentList = (List<UserPayment>) paymentRepository.findAll();

		for (UserPayment userPayment : userPaymentList) {
			if (userPayment.getId() == userPaymentId) {
				userPayment.setDefaultPayment(true);
				paymentRepository.save(userPayment);
			} else {
				userPayment.setDefaultPayment(false);
				paymentRepository.save(userPayment);
			}
		}

	}

	@Override
	public void updateUserShipping(UserShipping userShipping, User user) {
		userShipping.setUser(user);
		userShipping.setUserShippingDefault(true);
		user.getUserShippingList().add(userShipping);
		save(user);

	}

	@Override
	public void setUserDefaultShipping(Long userShippingId, User user) {
		List<UserShipping> userShippingList = (List<UserShipping>) shippingRepository.findAll();

		for (UserShipping userShipping : userShippingList) {
			if (userShipping.getId() == userShippingId) {
				userShipping.setUserShippingDefault(true);
				shippingRepository.save(userShipping);
			} else {
				userShipping.setUserShippingDefault(false);
				shippingRepository.save(userShipping);
			}
		}

	}

}

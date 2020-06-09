package com.bookstore.service;

import java.util.Set;

import com.bookstore.models.User;
import com.bookstore.models.UserBilling;
import com.bookstore.models.UserPayment;
import com.bookstore.models.UserShipping;
import com.bookstore.security.UserRole;

public interface UserService {

	User createUser(User user, Set<UserRole> userRole) throws Exception;

	User findByUsername(String username);

	User findByEmail(String email);

	User save(User user);

	User findById(long id);

	void updateUserPaymentInfo(UserBilling userBilling, UserPayment userPayment, User user);

	void updateUserBilling(UserBilling userBilling, UserPayment userPayment, User user);

	void setUserDefaultPayment(Long userPaymentId, User user);

	void updateUserShipping(UserShipping userShipping, User user);

	void setUserDefaultShipping(Long userShippingId, User user);
}

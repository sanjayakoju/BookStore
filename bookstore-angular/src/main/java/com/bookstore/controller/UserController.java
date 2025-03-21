package com.bookstore.controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bookstore.models.User;
import com.bookstore.security.Role;
import com.bookstore.security.UserRole;
import com.bookstore.security.config.SecurityConfig;
import com.bookstore.service.UserService;
import com.bookstore.utility.MailConstuctor;
import com.bookstore.utility.SecurityUtility;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private MailConstuctor mailConstuctor;
	
	@Autowired
	private JavaMailSender mailSender;
	
	@PostMapping("/newUser")
	public ResponseEntity addNewUser(HttpServletRequest request,@RequestBody HashMap<String, String> mapper) throws Exception
	{
		String username=mapper.get("username");
		String email=mapper.get("email");
		
		if(userService.findByUsername(username)!=null)
		{
			return new ResponseEntity("usernameExists",HttpStatus.BAD_REQUEST);
		}
		
		if(userService.findByEmail(email)!=null)
		{
			return new ResponseEntity("emailExists",HttpStatus.BAD_REQUEST);
		}
		
		User user=new User();
		user.setUsername(username);
		user.setEmail(email);
		
		String password=SecurityUtility.randomPasswor();
		
		String encryptedPassword=SecurityUtility.passwordEncoder().encode(password);
		user.setPassword(encryptedPassword);
		
		Role role=new Role();
		role.setRoleId(1);
		role.setName("ROLE_USER");
		Set<UserRole> userRole=new HashSet<>();
		userRole.add(new UserRole(user,role));
		userService.createUser(user, userRole);
		
		SimpleMailMessage newEmail=mailConstuctor.constructNewUserEmail(user,password);
		mailSender.send(newEmail);
		
		return new ResponseEntity("User added successfully!",HttpStatus.OK);
	}
	
	@RequestMapping(value = "/forgetPassword", method = RequestMethod.POST)
	public ResponseEntity forgetPasswordPost(HttpServletRequest request, @RequestBody HashMap<String, String> mapper)
			throws Exception {

		User user = userService.findByEmail(mapper.get("email"));

		if (user == null) {
			return new ResponseEntity("Email not found", HttpStatus.BAD_REQUEST);
		}
		String password = SecurityUtility.randomPasswor();

		String encryptedPassword = SecurityUtility.passwordEncoder().encode(password);
		user.setPassword(encryptedPassword);
		userService.save(user);

		SimpleMailMessage newEmail = mailConstuctor.constructNewUserEmail(user, password);
		mailSender.send(newEmail);

		return new ResponseEntity("Email sent!", HttpStatus.OK);

	}

	@RequestMapping(value="/updateUserInfo", method=RequestMethod.POST)
	public ResponseEntity profileInfo(
				@RequestBody HashMap<String, Object> mapper
			) throws Exception{
		
		int id = (Integer) mapper.get("id");
		String email = (String) mapper.get("email");
		String username = (String) mapper.get("username");
		String firstName = (String) mapper.get("firstName");
		String lastName = (String) mapper.get("lastName");
		String newPassword = (String) mapper.get("newPassword");
		String currentPassword = (String) mapper.get("currentPassword");
		
		User currentUser = userService.findById(Long.valueOf(id));
		
		if(currentUser == null) {
			throw new Exception ("User not found");
		}
		
		if(userService.findByEmail(email) != null) {
			if(userService.findByEmail(email).getId() != currentUser.getId()) {
				return new ResponseEntity("Email not found!", HttpStatus.BAD_REQUEST);
			}
		}
		
		if(userService.findByUsername(username) != null) {
			if(userService.findByUsername(username).getId() != currentUser.getId()) {
				return new ResponseEntity("Username not found!", HttpStatus.BAD_REQUEST);
			}
		}
		
		SecurityConfig securityConfig = new SecurityConfig();
		
		
			BCryptPasswordEncoder passwordEncoder = SecurityUtility.passwordEncoder();
			String dbPassword = currentUser.getPassword();
			
			if(null != currentPassword)
			if(passwordEncoder.matches(currentPassword, dbPassword)) {
				if(newPassword != null && !newPassword.isEmpty() && !newPassword.equals("")) {
					currentUser.setPassword(passwordEncoder.encode(newPassword));
				}
				currentUser.setEmail(email);
			} else {
				return new ResponseEntity("Incorrect current password!", HttpStatus.BAD_REQUEST);
			}
		
		
		currentUser.setFirstName(firstName);
		currentUser.setLastName(lastName);
		currentUser.setUsername(username);
		
		
		userService.save(currentUser);
		
		return new ResponseEntity("Update Success", HttpStatus.OK);
	}

	@RequestMapping("/getCurrentUser")
	public User getCurrentUser(Principal principal) {
		User user = new User();
		if (null != principal) {
			user = userService.findByUsername(principal.getName());
		}

		return user;
	}
 

}

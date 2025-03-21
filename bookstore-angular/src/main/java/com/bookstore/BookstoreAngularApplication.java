package com.bookstore;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.bookstore.models.User;
import com.bookstore.security.Role;
import com.bookstore.security.UserRole;
import com.bookstore.service.UserService;
import com.bookstore.utility.SecurityUtility;

@SpringBootApplication
public class BookstoreAngularApplication implements CommandLineRunner {

	@Autowired
	UserService userService;
	
	public static void main(String[] args) {
		SpringApplication.run(BookstoreAngularApplication.class, args);
	}

//	@Override
//	public void run(String... args) throws Exception {
//		
//		User user1=new User();
//		user1.setFirstName("Sanjaya");
//		user1.setLastName("koju");
//		user1.setEmail("sanjayakoju42@gmail.com");
//		user1.setPhone("9860488322");
//		user1.setUsername("sanjaya");
//		user1.setPassword(SecurityUtility.passwordEncoder().encode("sanjaya"));
//		Set<UserRole> userRoles=new HashSet<>();
//		Role role1=new Role();
//		role1.setRoleId(1);
//		role1.setName("ROLE_USER");
//		userRoles.add(new UserRole(user1, role1));
//		
//		userService.createUser(user1,userRoles);
//		userRoles.clear();
//		
//		User user2=new User();
//		user2.setFirstName("admin");
//		user2.setLastName("admin");
//		user2.setEmail("admin@gmail.com");
//		user2.setPhone("9860488322");
//		user2.setUsername("admin");
//		user2.setPassword(SecurityUtility.passwordEncoder().encode("admin"));
//	
//		Role role2=new Role();
//		role2.setRoleId(2);
//		role2.setName("ROLE_ADMIN");
//		userRoles.add(new UserRole(user2, role2));
//		
//		userService.createUser(user2,userRoles);
//		
//	}
	
	@Override
	public void run(String... args) throws Exception, RuntimeException{
		User user1 = new User();
		user1.setFirstName("John");
		user1.setLastName("Adams");
		user1.setUsername("j");
		user1.setPassword(SecurityUtility.passwordEncoder().encode("p"));
		user1.setEmail("JAdams@gmail.com");
		
		Set<UserRole> userRoles = new HashSet<>();
		
		Role role1= new Role();
		role1.setRoleId(1);
		role1.setName("ROLE_USER");
		userRoles.add(new UserRole(user1, role1));
		
		userService.createUser(user1, userRoles);
		userRoles.clear();
	}

}

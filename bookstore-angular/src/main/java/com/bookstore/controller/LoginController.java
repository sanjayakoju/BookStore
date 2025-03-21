package com.bookstore.controller;

import java.util.Collections;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookstore.service.UserService;

@RestController
//@CrossOrigin(origins = "http://localhost:4201")
public class LoginController {

	@Autowired
	UserService userService;
	
	@RequestMapping("/token")
	public Map<String,String> token(HttpSession session,HttpServletRequest request)
	{
		System.out.println(request.getRemoteHost());
		
		String remoteHost=request.getRemoteHost();
		int portNumber=request.getRemotePort();
		
		System.out.println("Remote Host"+remoteHost+" Port Number :"+portNumber);
		System.out.println("Remote Address : "+request.getRemoteAddr());
		
		return Collections.singletonMap("token", session.getId());
	}
	
	@RequestMapping("/checkSession")
	public ResponseEntity checkSession() {
		return new ResponseEntity("Session Active!", HttpStatus.OK);
	}
	
	@PostMapping(value="/user/logout")
	public ResponseEntity logout(){
		SecurityContextHolder.clearContext();
		return new ResponseEntity("Logout Successfully!", HttpStatus.OK);
	}
}

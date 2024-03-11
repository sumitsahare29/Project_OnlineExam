package com.example.demo.controller;

import java.util.HashMap;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Admin;
import com.example.demo.entity.Answer;
import com.example.demo.entity.User;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@RestController
@CrossOrigin("http://localhost:4200")
public class LoginController {

	@Autowired
	SessionFactory sFactory;

	static HttpSession httpSession;

//	{"username":"sumit","password":"sumit123"}

	@PostMapping("validate")
	public boolean validate(@RequestBody User userfrombrowser, HttpServletRequest request) {

		System.out.println("user from browser " + userfrombrowser);

		Session session = sFactory.openSession();

		User userFromdatabase = session.get(User.class, userfrombrowser.getUsername());

		System.out.println("user from database " + userFromdatabase);

		boolean answer = userfrombrowser.equals(userFromdatabase);

		System.out.println("answer from equal() of object is " + answer);

		if (answer) {

			httpSession = request.getSession();

			httpSession.setAttribute("score", 0);

			httpSession.setAttribute("questionIndex", 0);

			HashMap<Integer, Answer> hashMap = new HashMap<>();

			httpSession.setAttribute("submittedDetails", hashMap);

		}

		return answer;

	}

//	{"username":"admin","password":"admin123"}

	@PostMapping("validate2")
	public boolean validate2(@RequestBody Admin userfrombrowser, HttpServletRequest request) {

		System.out.println("user from browser " + userfrombrowser);

		Session session = sFactory.openSession();

		Admin userFromdatabase = session.get(Admin.class, userfrombrowser.getUsername());

		System.out.println("user from database " + userFromdatabase);

		if (userFromdatabase == null) {
			
			return false;
		
		}

		boolean answer = userfrombrowser.equals(userFromdatabase);

		System.out.println("answer from equal() of object is " + answer);

		return answer;
	}
}

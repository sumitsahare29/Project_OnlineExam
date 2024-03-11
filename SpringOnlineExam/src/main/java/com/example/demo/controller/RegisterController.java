package com.example.demo.controller;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.SecurityQuestion;
import com.example.demo.entity.User;

@RestController
@CrossOrigin("http://localhost:4200")
public class RegisterController {

	@Autowired
	SessionFactory sFactory;

	@PostMapping("saveToDB")
	public void saveToDB(@RequestBody User user) {

		System.out.println(user);

		Session session = sFactory.openSession();

		Transaction transaction = session.beginTransaction();

		session.persist(user);

		transaction.commit();

	}

	@PostMapping("saveToDB2")
	public void saveToDB(@RequestBody SecurityQuestion user) {

		System.out.println(user);

		Session session = sFactory.openSession();

		Transaction transaction = session.beginTransaction();

		session.persist(user);

		transaction.commit();

	}

	@GetMapping("updatePassword/{username}/{password}")
	public boolean updatePassword(@PathVariable String username, @PathVariable String password) {

//		System.out.println(username);

		Session session = sFactory.openSession();

		Transaction transaction = session.beginTransaction();

		Query query = session.createQuery("update User set password=:password where username=:username");
		query.setParameter("username", username);
		query.setParameter("password", password);
		
		query.executeUpdate();

		transaction.commit();

		return true;

	}

	@RequestMapping("checking")
	public boolean checking(@RequestBody SecurityQuestion user) {

		Session session = sFactory.openSession();
		
		SecurityQuestion obj = session.get(SecurityQuestion.class, user.getUsername());
		
		if(user.getAnswer().equals(obj.getAnswer())) {
			return true;
		}
		else {
			return false;
		}
	}

}

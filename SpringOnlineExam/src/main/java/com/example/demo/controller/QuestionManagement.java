package com.example.demo.controller;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Question;

@RestController
@CrossOrigin("http://localhost:4200")
public class QuestionManagement {

	@Autowired
	SessionFactory sFactory;

	@PostMapping("addQuestion")
	public boolean addQuestion(@RequestBody Question question) {

		Session session = sFactory.openSession();

		Transaction transaction = session.beginTransaction();

		session.persist(question);

		transaction.commit();

		return true;
	}

	@PutMapping("updateQuestion")
	public boolean updateQuestion(@RequestBody Question question) {

		Session session = sFactory.openSession();

		Transaction transaction = session.beginTransaction();

		session.merge(question);

		transaction.commit();

		return true;
	}

	@GetMapping("viewQuestion/{qno}/{subject}")
	public Question viewQuestion(@PathVariable Integer qno, @PathVariable String subject) {

		Session session = sFactory.openSession();

		Query query = session.createQuery("from Question where qno=:qno and subject=:subject");
		query.setParameter("qno", qno);
		query.setParameter("subject", subject);

//		List<Question> list = query.list();
//
//		Question question = list.get(0);

		Question question = (Question) query.uniqueResult();

		return question;
	}

	@DeleteMapping("deleteQuestion/{qno}/{subject}")
	public boolean deleteQuestion(@PathVariable Integer qno, @PathVariable String subject) {

		Session session = sFactory.openSession();

		Transaction transaction = session.beginTransaction();

		Query query = session.createQuery("from Question where qno=:qno and subject=:subject");
		query.setParameter("qno", qno);
		query.setParameter("subject", subject);

//		List<Question> list = query.list();
//
//		Question question = list.get(0);

		Question question = (Question) query.uniqueResult();

		session.remove(question);

		transaction.commit();

		return true;
	}

}

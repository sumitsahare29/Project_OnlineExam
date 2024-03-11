package com.example.demo.controller;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.entity.Answer;
import com.example.demo.entity.Question;
import jakarta.servlet.http.HttpSession;

@RestController
@CrossOrigin("http://localhost:4200")
public class QuestionController {

	@Autowired
	SessionFactory sFactory;

	@GetMapping("getFirstQuestion/{subjectFromAngular}")
	public Question getFirstQuestion(@PathVariable String subjectFromAngular) {

		HttpSession httpSession = LoginController.httpSession;

		Session session = sFactory.openSession();

		Query query = session.createQuery("from Question where subject=:subjectName");

		query.setParameter("subjectName", subjectFromAngular);

		List<Question> list = query.list();

		httpSession.setAttribute("allquestions", list);

		Question question = list.get(0);

		return question;

	}

	@RequestMapping("nextQuestion")
	public Question nextQuestion() {

		HttpSession httpSession = LoginController.httpSession;

		List<Question> listofquestions = (List<Question>) httpSession.getAttribute("allquestions");

		Question question = null;

		if ((int) httpSession.getAttribute("questionIndex") < listofquestions.size() - 1) {

			httpSession.setAttribute("questionIndex", (int) httpSession.getAttribute("questionIndex") + 1); // increase
																											// index

			question = listofquestions.get((int) httpSession.getAttribute("questionIndex"));// read object from
																							// list

		}

		else {
//			return listofquestions.get(listofquestions.size() - 1);

			httpSession.setAttribute("questionIndex", 0); // show first question

			question = listofquestions.get((int) httpSession.getAttribute("questionIndex"));// read object from list
		}

		return question;
	}

	@RequestMapping("getAllAnswers")
	public Collection<Answer> getAllAnswers() {

		HttpSession httpSession = LoginController.httpSession;

		HashMap<Integer, Answer> hashmap = (HashMap<Integer, Answer>) httpSession.getAttribute("submittedDetails");

		Collection<Answer> collection = hashmap.values();

		return collection;

	}

	@GetMapping("getAllSubjects")
	List<String> getAllSubjects() {

		Session session = sFactory.openSession();

		Query query = session.createQuery("select distinct subject from Question");

		List<String> list = query.list();

		return list;
	}

	@GetMapping("getAllQuestions/{subject}")
	List<Integer> getAllQuestions(@PathVariable String subject) {

		Session session = sFactory.openSession();

		Query query = session.createQuery("select distinct qno from Question where subject=:subject");

		query.setParameter("subject", subject);

		List<Integer> list = query.list();

		return list;
	}

	@RequestMapping("previousQuestion")
	public Question previousQuestion() {

		HttpSession httpSession = LoginController.httpSession;

		List<Question> listofquestions = (List<Question>) httpSession.getAttribute("allquestions");

		Question question = null;

		if ((int) httpSession.getAttribute("questionIndex") > 0) {

			httpSession.setAttribute("questionIndex", (int) httpSession.getAttribute("questionIndex") - 1); // decrease
																											// index

			question = listofquestions.get((int) httpSession.getAttribute("questionIndex"));// read object from list

		}

		else {
//			return listofquestions.get(listofquestions.size() - 1);

			httpSession.setAttribute("questionIndex", listofquestions.size() - 1);// show last question

			question = listofquestions.get((int) httpSession.getAttribute("questionIndex"));// read object from list
		}

		return question;
	}

	// {"qno":1, "qtext":"when", "submittedAnswer":"A", "correctAnswer":"B"}

	@PostMapping("saveAnswer")
	public void saveAnswer(@RequestBody Answer answer) {

		HttpSession httpSession = LoginController.httpSession;

		HashMap<Integer, Answer> hashmap = (HashMap<Integer, Answer>) httpSession.getAttribute("submittedDetails");

		hashmap.put(answer.getQno(), answer);

		System.out.println(hashmap);

	}

	@RequestMapping("calculateScore")
	public Integer calculateScore() {

		HttpSession httpSession = LoginController.httpSession;

		HashMap<Integer, Answer> hashmap = (HashMap<Integer, Answer>) httpSession.getAttribute("submittedDetails");

		Collection<Answer> collection = hashmap.values();

		for (Answer answer : collection) {

			if (answer.getSubmittedAnswer().equals(answer.getCorrectAnswer())) {

				httpSession.setAttribute("score", (int) httpSession.getAttribute("score") + 1);

			}
		}

		int score = (int) httpSession.getAttribute("score");

		// it remove all the attributes from httpSession Object
		httpSession.invalidate();

		return score;

	}

	@GetMapping("getQuestion/{questionNumber}")
	public Question getQuestion(@PathVariable int questionNumber) {

		HttpSession httpSession = LoginController.httpSession;

		List<Question> listofquestions = (List<Question>) httpSession.getAttribute("allquestions");

		Question expectedQuestion = null;

		for (Question question : listofquestions) {

			if (question.qno == questionNumber) {

				expectedQuestion = question;

			}
		}
		return expectedQuestion;
	}

}

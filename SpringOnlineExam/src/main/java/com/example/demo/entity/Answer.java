package com.example.demo.entity;


public class Answer {

	public int qno;
	public String qtext;
	public String submittedAnswer;
	public String correctAnswer;

	public int getQno() {
		return qno;
	}

	public void setQno(int qno) {
		this.qno = qno;
	}

	public String getQtext() {
		return qtext;
	}

	public void setQtext(String qtext) {
		this.qtext = qtext;
	}

	public String getSubmittedAnswer() {
		return submittedAnswer;
	}

	public void setSubmittedAnswer(String submittedAnswer) {
		this.submittedAnswer = submittedAnswer;
	}

	public String getCorrectAnswer() {
		return correctAnswer;
	}

	public void setCorrectAnswer(String correctAnswer) {
		this.correctAnswer = correctAnswer;
	}

	@Override
	public String toString() {
		return "Answer [qno=" + qno + ", qtext=" + qtext + ", submittedAnswer=" + submittedAnswer + ", correctAnswer="
				+ correctAnswer + "]";
	}

}

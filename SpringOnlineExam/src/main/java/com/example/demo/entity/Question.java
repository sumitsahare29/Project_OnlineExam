package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Question {

	@Id
	public int qno;
	public String qtext;
	public String op1;
	public String op2;
	public String op3;
	public String op4;
	public String answer;

	@Id
	public String subject;

	
	public Question() {
		super();
	}


	public Question(int qno, String qtext, String op1, String op2, String op3, String op4, String answer,
			String subject) {
		super();
		this.qno = qno;
		this.qtext = qtext;
		this.op1 = op1;
		this.op2 = op2;
		this.op3 = op3;
		this.op4 = op4;
		this.answer = answer;
		this.subject = subject;
	}


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


	public String getOp1() {
		return op1;
	}


	public void setOp1(String op1) {
		this.op1 = op1;
	}


	public String getOp2() {
		return op2;
	}


	public void setOp2(String op2) {
		this.op2 = op2;
	}


	public String getOp3() {
		return op3;
	}


	public void setOp3(String op3) {
		this.op3 = op3;
	}


	public String getOp4() {
		return op4;
	}


	public void setOp4(String op4) {
		this.op4 = op4;
	}


	public String getAnswer() {
		return answer;
	}


	public void setAnswer(String answer) {
		this.answer = answer;
	}


	public String getSubject() {
		return subject;
	}


	public void setSubject(String subject) {
		this.subject = subject;
	}


	@Override
	public String toString() {
		return "Question [qno=" + qno + ", qtext=" + qtext + ", op1=" + op1 + ", op2=" + op2 + ", op3=" + op3 + ", op4="
				+ op4 + ", answer=" + answer + ", subject=" + subject + "]";
	}
	
	
	
	
}

package com.csus.csc133.student;

public class StudentAngry extends Student {
	
	public StudentAngry() {
		super();
		this.talkativeLevel = talkativeLevel*2;
		
	}
	
	public String toString() {
		return super.toString() + ", I am angry!";
	}

}
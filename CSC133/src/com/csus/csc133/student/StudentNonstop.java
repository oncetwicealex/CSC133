package com.csus.csc133.student;

public class StudentNonstop extends Student {

	@Override
	public void move() {
		setTimeRemain(0);
		super.move();
	}

	@Override
	public String getTypeName() {
		// TODO Auto-generated method stub
		return "StudentNonstop";
	}

}
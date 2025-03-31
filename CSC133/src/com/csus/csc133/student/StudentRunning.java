package com.csus.csc133.student;

public class StudentRunning extends Student {

	public StudentRunning() {
		super();
		this.setSweatingRate(this.getSweatingRate() * 2);
	}

	@Override
	public String getTypeName() {
		// TODO Auto-generated method stub
		return "StudentRunning";
	}

}
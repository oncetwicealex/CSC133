package com.csus.csc133.student;

public class StudentBiking extends Student {

	public StudentBiking() {
		super();
		this.setSpeed(this.getSpeed() * 3);
		this.setSweatingRate(this.getSweatingRate() * 2);
	}

	@Override
	public String getTypeName() {
		// TODO Auto-generated method stub
		return "StudentBiking";
	}

}
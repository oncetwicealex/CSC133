package com.csus.csc133.student;

public class StudentSleeping extends Student {

	public StudentSleeping() {
		super();
		this.setSweatingRate(0.0);
	}

	public void move() {

	};

	@Override
	public String toString() {
		return super.toString() + " zzzZZZ!";
	}

	@Override
	public String getTypeName() {
		// TODO Auto-generated method stub
		return "StudentSleeping";
	}

}
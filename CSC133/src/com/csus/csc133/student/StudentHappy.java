package com.csus.csc133.student;

import java.util.Random;

public class StudentHappy extends Student {
	private static final Random rand = new Random();

	public StudentHappy() {
		super();
	}

	@Override
	public void move() {
		double beforeSpeed = getSpeed();

		if (rand.nextInt(10) < 2) {
			setSpeed(getDefaultSpeed() * 10);
		}

		super.move();

		setSpeed(beforeSpeed);
	}

	@Override
	public String toString() {
		return super.toString() + ", I am happy!";
	}

	@Override
	public String getTypeName() {
		// TODO Auto-generated method stub
		return "StudentHappy";
	}

}
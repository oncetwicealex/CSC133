package com.csus.csc133.student;

import java.util.Random;

public class StudentHappy extends Student {
	private static final Random rand = new Random();

	public StudentHappy() {
		super();
	}

	@Override
	public void move() {
		double beforeSpeed = speed;

		if (rand.nextInt(10) < 2) {
			speed = DEFAULT_SPEED * 10;
		}

		super.move();

		speed = beforeSpeed;
	}

	@Override
	public String toString() {
		return super.toString() + ", I am happy!";
	}

}
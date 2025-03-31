package com.csus.csc133.student;

import java.util.Random;

public class StudentCar extends Student {

	public StudentCar() {
		super();
		this.setSpeed(this.getSpeed() * 5);
		this.setSweatingRate(0);

		int[] directions = { 90, 270 };
		this.setHead(directions[new Random().nextInt(directions.length)]);

	}

	@Override
	public String getTypeName() {
		// TODO Auto-generated method stub
		return "StudentCar";
	}

}
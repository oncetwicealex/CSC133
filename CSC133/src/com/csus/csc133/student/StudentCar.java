package com.csus.csc133.student;

import java.util.Random;

public class StudentCar extends Student {

	public StudentCar() {
		super();
		this.speed *= 5;
		this.sweatingRate = 0;
		
		int[] directions = { 90, 270 };
		this.head = directions[new Random().nextInt(directions.length)];

	}

}
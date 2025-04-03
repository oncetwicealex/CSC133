package com.csus.csc133.student;

import java.util.Random;

import com.codename1.ui.Component;
import com.codename1.ui.Graphics;

public class StudentConfused extends Student {
	private static final Random rand = new Random();

	public StudentConfused() {
		super();
		this.setHead(rand.nextInt(360));

	}

	@Override
	public void move() {

		/*
		 * add randomness to movements
		 *
		 * rand.nextInt(21) - 10 generates a small random change between -10 and +10
		 * degrees to make movement unpredictable
		 *
		 * Makes a random 10 degree turn for each time move() is called
		 */

		this.setHead((this.getHead() + rand.nextInt(21) - 10) % 360);

		super.move();

	}

	@Override
	public String getTypeName() {
		// TODO Auto-generated method stub
		return "Confused";
	}

	@Override
	public void draw(Graphics g, Component c) {
		super.draw(g, c);		
	}

}
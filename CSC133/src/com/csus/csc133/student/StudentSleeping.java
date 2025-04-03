package com.csus.csc133.student;

import com.codename1.ui.Component;
import com.codename1.ui.Graphics;

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
		return "Sleeping";
	}

	@Override
	public void draw(Graphics g, Component c) {
		super.draw(g, c);		
	}

}
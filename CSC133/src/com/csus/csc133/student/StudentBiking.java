package com.csus.csc133.student;

import com.codename1.ui.Component;
import com.codename1.ui.Graphics;

public class StudentBiking extends Student {

	public StudentBiking() {
		super();
		this.setSpeed(this.getSpeed() * 3);
		this.setSweatingRate(this.getSweatingRate() * 2);
	}

	@Override
	public String getTypeName() {
		// TODO Auto-generated method stub
		return "Biking";
	}

	@Override
	public void draw(Graphics g, Component c) {
		super.draw(g, c);		
	}

}
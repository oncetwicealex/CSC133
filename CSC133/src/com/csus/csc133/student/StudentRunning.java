package com.csus.csc133.student;

import com.codename1.ui.Component;
import com.codename1.ui.Graphics;

public class StudentRunning extends Student {

	public StudentRunning() {
		super();
		this.setSweatingRate(this.getSweatingRate() * 2);
	}

	@Override
	public String getTypeName() {
		// TODO Auto-generated method stub
		return "Running";
	}

	@Override
	public void draw(Graphics g, Component c) {
		super.draw(g, c);		
	}

}
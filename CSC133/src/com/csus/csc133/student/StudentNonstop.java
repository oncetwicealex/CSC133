package com.csus.csc133.student;

import com.codename1.ui.Component;
import com.codename1.ui.Graphics;

public class StudentNonstop extends Student {

	@Override
	public void move() {
		setTimeRemain(0);
		super.move();
	}

	@Override
	public String getTypeName() {
		// TODO Auto-generated method stub
		return "Nonstop";
	}

	@Override
	public void draw(Graphics g, Component c) {
		super.draw(g, c);		
	}

}
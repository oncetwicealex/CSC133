package com.csus.csc133.student;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Component;
import com.codename1.ui.Graphics;

public class StudentAngry extends Student {

	public StudentAngry() {
		super();
		this.setTalkativeLevel(getTalkativeLevel() * 2);

	}

	public String toString() {
		return super.toString() + ", I am angry!";
	}

	@Override
	public String getTypeName() {
		return "Angry";
	}

	@Override
	public void draw(Graphics g, Component c) {
		super.draw(g, c);
		
	}

}
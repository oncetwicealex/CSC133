package com.csus.csc133.student;

import com.codename1.ui.Component;
import com.codename1.ui.Graphics;

public class StudentFriendly extends Student {

	public StudentFriendly() {
		super();
		this.setTalkativeLevel(getDefaultTalkativelevel() * 0.5);
	}

	@Override
	public String getTypeName() {
		// TODO Auto-generated method stub
		return "Friendly";
	}

	@Override
	public void draw(Graphics g, Component c) {
		super.draw(g, c);		
	}

}
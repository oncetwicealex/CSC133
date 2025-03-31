package com.csus.csc133.student;

public class StudentFriendly extends Student {

	public StudentFriendly() {
		super();
		this.setTalkativeLevel(getDefaultTalkativelevel() * 0.5);
	}

	@Override
	public String getTypeName() {
		// TODO Auto-generated method stub
		return "StudentFriendly";
	}

}
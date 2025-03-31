package com.csus.csc133.facilities;

import com.csus.csc133.student.Student;

public class WaterDispenser extends Facility {
	private static int waterSize = 40;

	public WaterDispenser() {
		setSize(waterSize);
	}

	@Override
	public void handleCollide(Student s) {
		if (s == null)
			return;

		s.drinkWater();

	}

	public String toString() {
		return super.toString();

	}

}
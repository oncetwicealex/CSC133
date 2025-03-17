package com.csus.csc133.facilities;

import com.csus.csc133.student.Student;

public class Restroom extends Facility {

	@Override
	public void handleCollide(Student s) {
		if (s == null)
			return;

		s.useRestroom();
	}

	public String toString() {
		return super.toString();

	}

}
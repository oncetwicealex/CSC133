package com.csus.csc133.facilities;

import com.csus.csc133.lecture.Lecture;
import com.csus.csc133.student.Student;
import com.csus.csc133.student.StudentPlayer;

public class LectureHall extends Facility {
	private String name;
	private Lecture lecture;

	/**
	 * Constructor for LectureHall
	 * 
	 * @param name The name of the lecture hall
	 */

	public LectureHall(String name) {
		super();
		this.name = name;
		this.lecture = null;

	}

	public void setLecture(Lecture lecture) {
		this.lecture = lecture;
	}

	public String getLectureStatus() {
		if (lecture != null && lecture.isOngoing()) {
			return String.valueOf(lecture.getTimeRemaining());
		}
		return "null";

	}

	@Override
	public void handleCollide(Student s) {
		if (s instanceof StudentPlayer) {
			StudentPlayer player = (StudentPlayer) s;
		
			if (lecture != null && lecture.isOngoing()) {
				System.out.println("Player attended the lecture at " + name);
				player.markAttendance();
				lecture.endLecture();
			} else {
				System.out.println("No lecture atm.");
			}
		}

	}

	@Override
	public String toString() {
	    String lectureTime = (lecture != null && lecture.isOngoing()) ? 
	        String.valueOf(lecture.getTimeRemaining()) : "null";
	    
	    return super.toString() + " Lecture Hall Name: " + name + 
	           ", Current Lecture Remaining Time: " + lectureTime;
	}

}
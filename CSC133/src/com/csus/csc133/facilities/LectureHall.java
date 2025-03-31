package com.csus.csc133.facilities;

import java.util.Random;

import com.csus.csc133.lecture.Lecture;
import com.csus.csc133.student.Student;
import com.csus.csc133.student.StudentPlayer;

public class LectureHall extends Facility {
	private String name;
	private Lecture lecture;
	private int lectureSize = 90;
	private String[] halls = { "Riverside Hall", "Brighton Hall", "Eureka Hall", "Tahoe Hall", "Sequoia Hall",
			"Yosemite Hall" };
	private Random random = new Random();

	/**
	 * Constructor for LectureHall
	 * 
	 * @param name The name of the lecture hall
	 */

	public LectureHall(String name) {
		super();
		this.name = halls[random.nextInt(halls.length)];
		this.lecture = null;

		setSize(lectureSize);

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

	public void setLectureName(String name) {
		this.name = name;
	}

	public String getLectureName() {
		return this.name;
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
		String lectureTime = (lecture != null && lecture.isOngoing()) ? String.valueOf(lecture.getTimeRemaining())
				: "null";

		return super.toString() + " Lecture Hall Name: " + name + ", Current Lecture Remaining Time: " + lectureTime;
	}

}
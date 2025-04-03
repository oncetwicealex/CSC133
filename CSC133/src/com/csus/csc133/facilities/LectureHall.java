package com.csus.csc133.facilities;

import java.util.Random;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Component;
import com.codename1.ui.Graphics;
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

	@Override
	public void draw(Graphics g, Component c) {
		int size = getSize();
		int halfSize = size / 2; 
		
		//convert center (x,y) to top left coordinates 
		int xPos = (int)((int) getX() + c.getX() - halfSize);
		int yPos = (int)((int) getY() + c.getY() - halfSize);
		
		g.setColor(ColorUtil.rgb(50, 66, 168));
		g.fillRect(xPos, yPos, size, size);
		
		g.setColor(ColorUtil.BLACK);
		g.drawString(name, xPos, yPos + size);
		
		
		
	}

}
package com.csus.csc133.facilities;

import java.util.Random;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Component;
import com.codename1.ui.Graphics;
import com.csus.csc133.GameModel;
import com.csus.csc133.lecture.Lecture;
import com.csus.csc133.student.Student;
import com.csus.csc133.student.StudentPlayer;

public class LectureHall extends Facility {
	private GameModel gm;
	private String name;
	private Lecture lecture;
	private int lectureSize = 90;

	private Random random = new Random();

	/**
	 * Constructor for LectureHall
	 * 
	 * @param name The name of the lecture hall
	 */

	public LectureHall(String name, GameModel gm) {
		super();
		this.gm = gm;
		this.name = name;
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
		if (name != null) {
			this.name = name;
		}
		else {
			name = "null";
		}

	}

	public String getLectureName() {
		return this.name;
	}

	@Override
	public void handleCollide(Student s) {
		if (s instanceof StudentPlayer) {
			StudentPlayer player = (StudentPlayer) s;

			if (lecture != null && lecture.isOngoing()) {
				gm.updateMessage("Player attended the lecture at " + name);
				player.markAttendance();
				lecture.endLecture();
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

		// convert center (x,y) to top left coordinates
		int xPos = (int) ((int) getX() + c.getX() - halfSize);
		int yPos = (int) ((int) getY() + c.getY() - halfSize);

		g.setColor(ColorUtil.rgb(50, 66, 168));
		g.fillRect(xPos, yPos, size, size);

		g.setColor(ColorUtil.BLACK);
		g.drawString(name, xPos, yPos + size);
		
		if (isSelected()) {
			g.setColor(ColorUtil.rgb(255, 0, 0));
			g.drawRect(xPos, yPos, size, size);
			
		}

	}

}
package com.csus.csc133.student;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Component;
import com.codename1.ui.Graphics;
import com.csus.csc133.GameModel;

public class StudentPlayer extends Student {
	private GameModel gm;
	private static StudentPlayer instance;
	private boolean attended = false;

	private StudentPlayer() {
		super();

	}

	public void setGameModel(GameModel gm) {
		this.gm = gm;
	}

	public static StudentPlayer getStudentPlayer() {
		if (instance == null) {
			instance = new StudentPlayer();
		}
		return instance;
	}

	@Override
	public void move() {
		if (getSpeed() > 0) {
			super.move();
		}
	}

	public void startMoving() {
		this.setSpeed(getDefaultSpeed());
		System.out.println("Player is moving.)");
		gm.updateMessage("Player is moving.");

	}

	public void stopMoving() {
		this.setSpeed(0);
		System.out.println("Player stopped moving.");
		gm.updateMessage("Player stopped moving.");
	}

	public void turnLeft() {
		this.setHead((this.getHead() - 5 + 360) % 360);
		stopMoving();
		System.out.println("Player turned left to head: " + getHead());
		gm.updateMessage("Player turned left to head: " + getHead());
	}

	public void turnRight() {
		this.setHead((this.getHead() + 5) % 360);
		stopMoving();
		System.out.println("Player turned right to head: " + getHead());
		gm.updateMessage("Player turned right to head: " + getHead());
	}

	@Override
	public String toString() {
		return super.toString() + ", Absence Time: " + getAbsenceTime() + ", Water Intake: " + getWaterIntake();
	}

	public void markAttendance() {
		attended = true;
	}

	public boolean attendedLecture() {
		return attended;
	}

	public void resetAttendance() {
		attended = false;

	}

	public int getAbsenceTime() {
		return super.getAbsenceTime();
	}

	public void countAbsenceTime() {
		setAbsenceTime(getAbsenceTime() + 1);
	}

	public double getTimeRemain() {
		return super.getTimeRemain();
	}

	@Override
	public String getTypeName() {
		// TODO Auto-generated method stub
		return "Player 1";
	}

	@Override
	public void draw(Graphics g, Component c) {
		int size = getSize();
		int halfSize = size / 2;
		int xCenter = (int) getX() + c.getX();
		int yCenter = (int) getY() + c.getY();
		
		String label = getTypeName();
		if (label == null) {
			label = "";
		}
		int labelwidth = g.getFont().stringWidth(label);
		int textX = xCenter - (labelwidth / 2);
		int textY = yCenter + halfSize + 5;
		

		int[] xPoints = { xCenter, xCenter - halfSize, xCenter + halfSize };
		int[] yPoints = { yCenter - halfSize, yCenter + halfSize, yCenter + halfSize };
		
		g.setColor(getColor());
		g.fillPolygon(xPoints, yPoints, 3);
		
		g.setColor(ColorUtil.BLACK);
		g.drawString(label, textX, textY);
		
		
	}
}
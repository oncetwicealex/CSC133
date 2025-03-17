package com.csus.csc133.student;

public class StudentPlayer extends Student {

	private boolean attended = false;

	public StudentPlayer() {
		super();

	}

	@Override
	public void move() {
		if (speed > 0) {
			super.move();
		}
	}

	public void startMoving() {
		this.speed = DEFAULT_SPEED;
		System.out.println("Player started moving.");
	}

	public void stopMoving() {
		this.speed = 0;
		System.out.println("Player stopped moving.");
	}

	public void turnLeft() {
		this.head = (this.head - 5 + 360) % 360;
		System.out.println("Player turned left.");
	}

	public void turnRight() {
		this.head = (this.head + 5) % 360;
		System.out.println("Player turned right.");
	}

	@Override
	public String toString() {
		return super.toString() + ", Absence Time: " + absenceTime + ", Water Intake: " + waterIntake;
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
		return absenceTime;
	}

	public void countAbsenceTime() {
		absenceTime++;
	}
}
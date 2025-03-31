package com.csus.csc133.student;

import java.util.Random;

import com.codename1.charts.util.ColorUtil;
import com.csus.csc133.GameModel;
import com.csus.csc133.GameObject;
import com.csus.csc133.IMoveable;

public abstract class Student extends GameObject implements IMoveable {
	private static final double DEFAULT_SPEED = 200.0;
	private static final double DEFAULT_TALKATIVELEVEL = 2.0;
	private static final double DEFAULT_HYDRATION = 150.0;
	private static final double DEFAULT_WATERINTAKE = 0;
	private static final double DEFAULT_SWEATINGRATE = 3.0;
	private static final Random random = new Random();

	private int head;
	private double speed;
	private double talkativeLevel;
	private int timeRemain;
	private double hydration;
	private double waterIntake;
	private double sweatingRate;
	private int absenceTime;

	public abstract String getTypeName();

	public Student() {
		super();

		this.head = 0;
		this.setSpeed(getDefaultSpeed());
		this.setTalkativeLevel(getDefaultTalkativelevel());
		this.hydration = DEFAULT_HYDRATION;
		this.waterIntake = DEFAULT_WATERINTAKE;
		this.setSweatingRate(DEFAULT_SWEATINGRATE);
		this.setAbsenceTime(0);

		int studentSize = 40 + random.nextInt(21);
		this.setSize(studentSize);

	}

	@Override
	public void move() {

		if (getTimeRemain() > 0) {
			setColor(ColorUtil.rgb(201, 139, 172));
		} else {
			setColor(ColorUtil.rgb(255, 0, 0));
		}

		if (getTimeRemain() > 0) {
			setTimeRemain(getTimeRemain() - 1);
			setHydration(getHydration() - getSweatingRate());
			return;

		}

		// convert degrees to radians
		double radians = Math.toRadians(90.0 - head);

		// get the next x and y positions
		double next_X = getX() + Math.cos(radians) * speed;
		double next_Y = getY() + Math.sin(radians) * speed;

		// but first, boundary Checks
		boolean outOfBounds = false;
		if (next_X < 0 || next_X > GameModel.getGAMEWORLD_WIDTH()) {
			next_X = Math.max(0, Math.min(next_X, GameModel.getGAMEWORLD_WIDTH()));
			outOfBounds = true;
		}

		if (next_Y < 0 || next_Y > GameModel.getGAMEWORLD_HEIGHT()) {
			next_Y = Math.max(0, Math.min(next_Y, GameModel.getGAMEWORLD_HEIGHT()));
			outOfBounds = true;
		}
		if (outOfBounds) {
			setHead((getHead() + 180) % 360);
		}

		// set the new x and y coordinates
		setX(next_X);
		setY(next_Y);

		setHydration(getHydration() - getSweatingRate());

	}

	@Override
	public void handleCollide(Student s) {
		if (s != null) {
			int talkTime = (int) Math.max(this.getTalkativeLevel(), s.getTalkativeLevel());
			this.setTimeRemain(talkTime);
			s.setTimeRemain(talkTime);
		}
	}

	public void drinkWater() {
		double beforeHydration = this.hydration;
		setHydration(DEFAULT_HYDRATION);

		double diffHydration = DEFAULT_HYDRATION - beforeHydration;
		setWaterIntake(getWaterIntake() + (diffHydration));

		System.out.println("Student drank water. Hydration: " + beforeHydration + " to " + DEFAULT_HYDRATION);
		System.out.println(
				"Water intake increased by: " + diffHydration + ". New Water Intake: " + this.getWaterIntake());

	}

	public void useRestroom() {
		setWaterIntake(DEFAULT_WATERINTAKE);
	}

	public int getAbsenceTime() {
		return absenceTime;
	}

	public void setAbsenceTime(int absenceTime) {
		this.absenceTime = absenceTime;
	}

	public static double getDefaultTalkativelevel() {
		return DEFAULT_TALKATIVELEVEL;
	}

	public static double getDefaultSpeed() {
		return DEFAULT_SPEED;
	}

	public int getHead() {
		return head;
	}

	public void setHead(int head) {
		this.head = head;
	}

	public double getHydration() {
		return hydration;
	}

	public int getTimeRemain() {
		return timeRemain;
	}

	public void setHydration(double hydration) {
		this.hydration = Math.max(0, Math.min(hydration, DEFAULT_HYDRATION));
	}

	public static double getDefaultHydration() {
		return DEFAULT_HYDRATION;
	}

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public double getSweatingRate() {
		return sweatingRate;
	}

	public void setSweatingRate(double sweatingRate) {
		this.sweatingRate = sweatingRate;
	}

	public double getTalkativeLevel() {
		return talkativeLevel;
	}

	public void setTalkativeLevel(double talkativeLevel) {
		this.talkativeLevel = talkativeLevel;
	}

	public void setTimeRemain(int timeRemain) {
		this.timeRemain = timeRemain;
	}

	public double getWaterIntake() {
		return waterIntake;
	}

	public void setWaterIntake(double waterIntake) {
		this.waterIntake = Math.max(0, waterIntake);

	}

	public static double getDefaultWaterIntake() {
		return DEFAULT_WATERINTAKE;
	}

	public String toString() {
		String fullname = getClass().toString();
		fullname = fullname.substring(fullname.lastIndexOf('.') + 1);

		return super.toString() + ", head: " + Math.round(getHead()) + ", speed: " + getSpeed() + ", hydration: "
				+ hydration + ", talkative level: " + getTalkativeLevel() + ", time remaining: " + getTimeRemain();

	}

}
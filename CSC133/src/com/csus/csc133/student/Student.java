package com.csus.csc133.student;


import com.csus.csc133.GameModel;
import com.csus.csc133.GameObject;
import com.csus.csc133.IMoveable;

public abstract class Student extends GameObject implements IMoveable {
	protected static final double DEFAULT_SPEED = 200.0;
	protected static final double DEFAULT_TALKATIVELEVEL = 2.0;
	protected static final double DEFAULT_HYDRATION = 150.0;
	protected static final double DEFAULT_WATERINTAKE = 0;
	protected static final double DEFAULT_SWEATINGRATE = 3.0;
	
	protected int head;
	protected double speed;
	protected double talkativeLevel;
	protected int timeRemain;
	protected double hydration;
	protected double waterIntake;
	protected double sweatingRate;
	protected int absenceTime;

	public Student() {
		super();
		this.speed = DEFAULT_SPEED;
		this.talkativeLevel = DEFAULT_TALKATIVELEVEL;
		this.hydration = DEFAULT_HYDRATION;
		this.waterIntake = DEFAULT_WATERINTAKE;
		this.sweatingRate = DEFAULT_SWEATINGRATE;
		this.absenceTime = 0;

	}

	@Override
	public void move() {
		if (timeRemain > 0) {
			timeRemain--;
			setHydration(getHydration()-sweatingRate);
			return;

		}

		// convert degrees to radians
		double radians = Math.toRadians(90.0 - head);

		// get the next x and y positions
		double next_X = getX() + Math.cos(radians) * speed;
		double next_Y = getY() + Math.sin(radians) * speed;

		// but first, boundary Checks
		if (next_X < 0 || next_X > GameModel.getGAMEWORLD_WIDTH()) {
			next_X = Math.max(0, Math.min(next_X, GameModel.getGAMEWORLD_WIDTH()));
			head = (head + 180) % 360;
		}

		if (next_Y < 0 || next_Y > GameModel.getGAMEWORLD_HEIGHT()) {
			next_Y = Math.max(0, Math.min(next_Y, GameModel.getGAMEWORLD_HEIGHT()));
			head = (head + 180) % 360;
		}

		// set the new x and y coordinates
		setX(next_X);
		setY(next_Y);

		setHydration(getHydration() - sweatingRate);

	}

	

	@Override
	public void handleCollide(Student s) {
		if (s != null) {
			int talkTime = (int) Math.max(this.talkativeLevel, s.talkativeLevel);
			this.timeRemain = talkTime;
			s.timeRemain = talkTime;
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

	public double getHydration() {
		return hydration;
	}

	public void setHydration(double hydration) {
		this.hydration = Math.max(0, Math.min(hydration, DEFAULT_HYDRATION));
	}

	public static double getDefaultHydration() {
		return DEFAULT_HYDRATION;
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

		return super.toString() + ", head: " + Math.round(head) + ", speed: " + speed + ", hydration: " + hydration
				+ ", talkative level: " + talkativeLevel + ", time remaining: " + timeRemain;

	}

}
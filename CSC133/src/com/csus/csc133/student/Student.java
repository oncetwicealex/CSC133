package com.csus.csc133.student;

import java.util.Random;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Component;
import com.codename1.ui.Graphics;
import com.csus.csc133.GameModel;
import com.csus.csc133.GameObject;
import com.csus.csc133.IMoveable;

public abstract class Student extends GameObject implements IMoveable {
	private static final double DEFAULT_SPEED = 2;
	private static final double DEFAULT_TALKATIVELEVEL = 10.0;
	private static final double DEFAULT_HYDRATION = 150.0;
	private static final double DEFAULT_WATERINTAKE = 0;
	private static final double DEFAULT_SWEATINGRATE = 3.0;
	private static final Random random = new Random();

	private int head;
	private double speed;
	private double talkativeLevel;
	private double timeRemain;
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
		g.drawPolygon(xPoints, yPoints, 3);

		g.setColor(ColorUtil.BLACK);
		g.drawString(label, textX, textY);

	}

	@Override
	public void move() {

		double elapsed = GameModel.getFrameElapsedTime();

		if (getTimeRemain() > 0) {
			setColor(ColorUtil.rgb(219, 96, 133));
		} else {
			setColor(ColorUtil.rgb(171, 22, 22));
		}

		if (timeRemain > 0) {
			timeRemain -= elapsed;
			setHydration(getHydration() - getSweatingRate() * elapsed);
			return;

		}

		// convert degrees to radians
		double radians = Math.toRadians(90.0 - head);

		// get the next x and y positions
		double next_X = getX() + Math.cos(radians) * speed;
		double next_Y = getY() + Math.sin(radians) * speed;

		// but first, boundary Checks
		int halfSize = getSize() / 2;

		boolean outOfBounds = false;

		if (next_X - halfSize < 0) {
			next_X = halfSize;
			outOfBounds = true;
		} else if (next_X + halfSize > GameModel.getGAMEWORLD_WIDTH()) {
			next_X = GameModel.getGAMEWORLD_WIDTH() - halfSize;
			outOfBounds = true;
		}

		if (next_Y - halfSize < 0) {
			next_Y = halfSize;
			outOfBounds = true;
		} else if (next_Y + halfSize > GameModel.getGAMEWORLD_HEIGHT()) {
			next_Y = GameModel.getGAMEWORLD_HEIGHT() - halfSize;
			outOfBounds = true;
		}

		if (outOfBounds) {
			setHead((getHead() + 180) % 360);
		}

		// set the new x and y coordinates
		setX(next_X);
		setY(next_Y);

		setHydration(getHydration() - getSweatingRate() * elapsed);

		clampPosition();

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

	public double getTimeRemain() {
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

	public void setTimeRemain(double d) {
		this.timeRemain = d;
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
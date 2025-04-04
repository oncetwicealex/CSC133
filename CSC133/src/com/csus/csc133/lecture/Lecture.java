package com.csus.csc133.lecture;

import java.util.Random;

import com.csus.csc133.GameModel;

public class Lecture {
	private double timeRemaining;
	private static final Random rand = new Random();

	public Lecture() {
		this.timeRemaining = 0;
	}
	

	public boolean isOngoing() {
		return timeRemaining > 0;
	}

	public double getTimeRemaining() {
		return timeRemaining;
	}

	public void startLecture() {
		this.timeRemaining = 20 + rand.nextInt(11);
		System.out.println("Lecture has started. Time remaining: " + timeRemaining);
	}

	public void endLecture() {
		timeRemaining = 0;
		System.out.println("Lecture has ended.");
	}

	public void decreaseTime() {
		if (timeRemaining > 0) {
			timeRemaining -= GameModel.getFrameElapsedTime();
			if (timeRemaining <= 0) {
				timeRemaining = 0;
				System.out.println("Lecture is over.");
			}
		}
	}

	@Override
	public String toString() {
		return "Lecture Time Remaining: " + (isOngoing() ? timeRemaining : "None");
	}
}
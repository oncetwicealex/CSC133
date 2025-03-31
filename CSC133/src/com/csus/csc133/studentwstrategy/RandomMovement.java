package com.csus.csc133.studentwstrategy;

import java.util.Random;

public class RandomMovement extends MovementStrategy {
	private static final Random random = new Random();

	@Override
	public void apply(StudentWithStrategy student) {
		int change = random.nextInt(21) - 10;
		int newHead = (student.getHead() + change) % 360;

		if (newHead < 0) {
			newHead += 360;
		}

		student.setHead(newHead);

	}

	@Override
	public String getStrategyName() {
		return "Random Movement";
	}

}

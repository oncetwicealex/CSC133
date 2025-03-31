package com.csus.csc133.studentwstrategy;

import java.util.Random;

public class HorizontalMovement extends MovementStrategy {
	private static final Random random = new Random();

	@Override
	public void apply(StudentWithStrategy student) {
		student.setHead(random.nextBoolean() ? 90 : 270);

	}

	@Override
	public String getStrategyName() {
		return "Horizontal Movement";
	}

}

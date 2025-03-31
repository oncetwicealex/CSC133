package com.csus.csc133.studentwstrategy;

import java.util.Random;

public class VerticalMovement extends MovementStrategy {
	private static final Random random = new Random();

	@Override
	public void apply(StudentWithStrategy student) {
		student.setHead(random.nextBoolean() ? 0 : 180);

	}

	@Override
	public String getStrategyName() {
		return "Vertical Movement";
	}

}

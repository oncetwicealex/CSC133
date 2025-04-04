package com.csus.csc133.studentwstrategy;

import java.util.Random;

public class HorizontalMovement extends MovementStrategy {
	private Integer head1 = null;
	private static final Random random = new Random();

	@Override
	public void apply(StudentWithStrategy student) {
		if(head1 == null) {
			head1 = random.nextBoolean() ? 90 : 270;
		}
		student.setHead(head1);

	}

	@Override
	public String getStrategyName() {
		return "Horizontal Movement";
	}

}

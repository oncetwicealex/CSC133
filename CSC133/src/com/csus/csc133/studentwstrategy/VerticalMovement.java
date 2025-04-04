package com.csus.csc133.studentwstrategy;

import java.util.Random;

public class VerticalMovement extends MovementStrategy {
	private Integer head1;
	private static final Random random = new Random();

	@Override
	public void apply(StudentWithStrategy student) {
		if(head1 == null) {
			head1 = random.nextBoolean() ? 0 : 180;
		}
		student.setHead(head1);

	}

	@Override
	public String getStrategyName() {
		return "Vertical Movement";
	}

}

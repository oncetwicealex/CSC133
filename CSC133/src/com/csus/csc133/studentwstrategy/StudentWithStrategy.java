package com.csus.csc133.studentwstrategy;

import java.util.Random;

import com.csus.csc133.student.Student;

public class StudentWithStrategy extends Student {
	private MovementStrategy strategy;
	private static final Random random = new Random();

	public StudentWithStrategy() {
		super();

	}

	public int getHead() {
		return super.getHead();
	}

	public void setHead(int newHead) {
		super.setHead(newHead);

	}

	@Override
	public void move() {
		if (strategy != null) {
			strategy.apply(this);
		}

		super.move();
	}

	public void changeStrategy() {
		MovementStrategy[] strategies = new MovementStrategy[] { new RandomMovement(), new VerticalMovement(),
				new HorizontalMovement() };

		strategy = strategies[random.nextInt(strategies.length)];

	}

	public String getStrategyName() {
		return strategy != null ? strategy.getStrategyName() : "None";
	}

	@Override
	public String toString() {
		return super.toString() + ", Strategy Name: " + getStrategyName();
	}

	@Override
	public String getTypeName() {
		// TODO Auto-generated method stub
		return "StudentWithStrategy";
	}

}

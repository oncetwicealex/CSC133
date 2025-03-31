package com.csus.csc133.studentwstrategy;

public abstract class MovementStrategy {

	public abstract void apply(StudentWithStrategy student);

	public abstract String getStrategyName();
}

package core.filter;

import core.filterTypes.MatrixFilter;

public class Identisch extends MatrixFilter {

	@Override
	public int getDivisor() {

		return 1;
	}

	private double[][] matrix = { { 0, 0, 0 }, { 0, 1, 0 }, { 0, 0, 0 } };

	@Override
	public double[][] getMatrix() {

		return matrix;
	}

	@Override
	public int getSize() {

		return 3;
	}

}

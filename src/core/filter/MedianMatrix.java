package core.filter;

import core.MatrixFilter;

public class MedianMatrix extends MatrixFilter {

	static double[][] matrix = { { 1, 1, 1 }, { 1, 1, 1 }, { 1, 1, 1 } };

	@Override
	public int getDivisor() {
		return 3;
	}

	@Override
	public double[][] getMatrix() {
		return matrix;
	}

	@Override
	public int getSize() {
		return 3;
	}

}
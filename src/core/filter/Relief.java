package core.filter;

import core.MatrixFilter;

public class Relief extends MatrixFilter {

	static double[][] matrix = { { -2, -1, 0 }, { -1, 1, 1 }, { 0, 1, 2 } };

	@Override
	public int getDivisor() {
		return 1;
	}

	@Override
	public double[][] getMatrix() {
		// TODO Auto-generated method stub
		return matrix;
	}

	@Override
	public int getSize() {
		// TODO Auto-generated method stub
		return 3;
	}

}

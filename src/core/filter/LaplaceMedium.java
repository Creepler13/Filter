package core.filter;

import core.filterTypes.MatrixFilter;

public class LaplaceMedium extends MatrixFilter {

	private double[][] matrix = { { 0, 1, 0 }, { 1, -4, 1 }, { 0, 1, 0 } };

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

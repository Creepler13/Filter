package core.filter.filters;

import core.filter.filterTypes.MatrixFilter;

public class LaplaceMin extends MatrixFilter {

	private double[][] matrix = { { 1, 0, -1 }, { 0, 0, 0 }, { -1, 0, 1 } };

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

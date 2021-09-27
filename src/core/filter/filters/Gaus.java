package core.filter.filters;

import core.filter.filterTypes.MatrixFilter;

public class Gaus extends MatrixFilter {

	double[][] matrix = { { 1, 2, 1 }, { 2, 4, 2 }, { 1, 2, 1 } };

	@Override
	public int getDivisor() {
		return 16;
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

package core.filter.filters;

import core.filter.filterTypes.MatrixFilter;

public class Schärfung extends MatrixFilter {

	static double[][] matrix = { { 0, -1, 0 }, { -1, 5, -1 }, { 0, -1, 0 } };

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

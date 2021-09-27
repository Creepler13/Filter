package core.filter.filters;

import core.filter.filterTypes.MatrixFilter;

public class LaplaceMax extends MatrixFilter {

	private double[][] matrix = { { -1, -1, -1 }, { -1, 8, -1 }, { -1, -1, -1 } };

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

package core.filter;

import core.filterTypes.MatrixFilter;

public class Gaus5 extends MatrixFilter {

	double[][] matrix = { { 1, 4, 6, 4, 1 }, { 4, 16, 24, 16, 4 }, { 6, 24, 36, 24, 6 }, { 4, 16, 24, 16, 4 },
			{ 1, 4, 6, 4, 1 } };

	@Override
	public int getDivisor() {
		return 256;
	}

	@Override
	public double[][] getMatrix() {
		// TODO Auto-generated method stub
		return matrix;
	}

	@Override
	public int getSize() {
		// TODO Auto-generated method stub
		return 5;
	}

}

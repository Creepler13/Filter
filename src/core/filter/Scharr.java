package core.filter;

import core.MatrixFilter;

public class Scharr extends MatrixFilter {

	double[][] matrix = { { 47, 162, 47 }, { 0, 0, 0 }, { -47, -162, -47 } };

	@Override
	public int getDivisor() {
		// TODO Auto-generated method stub
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

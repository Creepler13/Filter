package core.filter;

import core.MatrixFilter;

public class Sobel2 extends MatrixFilter {

	double[][] matrix = { { 1, 0, -1 }, { 2, 0, -2 }, { 1, 0, -1 } };

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

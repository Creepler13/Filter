package core.filter.filters;

import core.filter.filterTypes.MatrixGSqrtFilter;

public class Sobel extends MatrixGSqrtFilter {

	double[][] SobelG1 = { { 1, 2, 1 }, { 0, 0, 0 }, { -1, -2, -1 } };
	double[][] SobelG2 = { { 1, 0, -1 }, { 2, 0, -2 }, { 1, 0, -1 } };

	@Override
	public int getSize() {
		return 3;
	}

	@Override
	public double[][] getG1() {
		// TODO Auto-generated method stub
		return SobelG1;
	}

	@Override
	public double[][] getG2() {
		// TODO Auto-generated method stub
		return SobelG2;
	}

	@Override
	public int getDivisor() {
		// TODO Auto-generated method stub
		return 1;
	}

}

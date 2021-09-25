package core.filter;

import core.filterTypes.MatrixGSqrtFilter;

public class Scharr extends MatrixGSqrtFilter {

	private static double[][] ScharrG1 = { { 47, 162, 47 }, { 0, 0, 0 }, { -47, -162, -47 } };
	private static double[][] ScharrG2 = { { 47, 0, -47 }, { 162, 0, -162 }, { 47, 0, -47 } };

	@Override
	public int getSize() {

		return 3;
	}

	@Override
	public double[][] getG1() {
		// TODO Auto-generated method stub
		return ScharrG1;
	}

	@Override
	public double[][] getG2() {
		// TODO Auto-generated method stub
		return ScharrG2;
	}

	@Override
	public int getDivisor() {
		// TODO Auto-generated method stub
		return 64;
	}

}

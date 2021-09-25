package core.filter;

import core.filterTypes.MatrixGSqrtFilter;

public class Prewitt extends MatrixGSqrtFilter {

	double[][] PrewittG1 = { { 1, 1, 1 }, { 0, 0, 0 }, { -1, -1, -1 } };
	double[][] PrewittG2 = { { 1, 0, -1 }, { 1, 0, -1 }, { 1, 0, -1 } };

	@Override
	public int getSize() {
		return 3;
	}

	@Override
	public double[][] getG1() {
		// TODO Auto-generated method stub
		return PrewittG1;
	}

	@Override
	public double[][] getG2() {
		// TODO Auto-generated method stub
		return PrewittG2;
	}

	@Override
	public int getDivisor() {
		// TODO Auto-generated method stub
		return 1;
	}

}

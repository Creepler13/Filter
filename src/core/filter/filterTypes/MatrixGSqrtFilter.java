package core.filter.filterTypes;

import java.awt.Color;

public abstract class MatrixGSqrtFilter extends Filter {

	@Override
	public Color calculate(Color[][] grid) {

		double rG1 = 0, rG2 = 0, gG1 = 0, gG2 = 0, bG1 = 0, bG2 = 0;

		for (int i = 0; i < getSize(); i++) {
			for (int j = 0; j < getSize(); j++) {

				rG1 += grid[i][j].getRed() * getG1()[i][j];
				rG2 += grid[i][j].getRed() * getG2()[i][j];
				gG1 += grid[i][j].getGreen() * getG1()[i][j];
				gG2 += grid[i][j].getGreen() * getG2()[i][j];
				bG1 += grid[i][j].getBlue() * getG1()[i][j];
				bG2 += grid[i][j].getBlue() * getG2()[i][j];

			}
		}

		rG1 = Math.sqrt(rG1 * rG1 + rG2 * rG2);
		gG1 = Math.sqrt(gG1 * gG1 + gG2 * gG2);
		bG1 = Math.sqrt(bG1 * bG1 + bG2 * bG2);

		rG1 = rG1 / getDivisor();
		gG1 = gG1 / getDivisor();
		bG1 = bG1 / getDivisor();

		rG1 = rG1 < 0 ? 0 : rG1 > 255 ? 255 : rG1;
		gG1 = gG1 < 0 ? 0 : gG1 > 255 ? 255 : gG1;
		bG1 = bG1 < 0 ? 0 : bG1 > 255 ? 255 : bG1;

		return new Color((int) rG1, (int) gG1, (int) bG1);

	}

	public abstract double[][] getG1();

	public abstract double[][] getG2();

	public abstract int getDivisor();

}

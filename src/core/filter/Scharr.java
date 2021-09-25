package core.filter;

import java.awt.Color;

import core.Filter;

public class Scharr extends Filter {

	double[][] ScharrX = { { 47, 162, 47 }, { 0, 0, 0 }, { -47, -162, -47 } };
	double[][] ScharrY = { { 47, 0, -47 }, { 162, 0, -162 }, { 47, 0, -47 } };

	@Override
	public Color calculate(Color[][] grid) {

		double grayX = 0, grayY = 0, gray = 0;

		for (int i = 0; i < getSize(); i++) {
			for (int j = 0; j < getSize(); j++) {

				double grayTemp = (grid[i][j].getRed() * 0.299 + grid[i][j].getGreen() * 0.567
						+ grid[i][j].getBlue() * 0.114);

				grayX = grayX + grayTemp * ScharrX[i][j];
				grayY = grayY + grayTemp * ScharrY[i][j];

			}
		}

		gray = Math.sqrt(grayX * grayX + grayY * grayY) / 64;

		if (gray < 0)
			gray = 0;
		if (gray > 255)
			gray = 255;

		return new Color((int) gray, (int) gray, (int) gray);

	}

	@Override
	public int getSize() {

		return 3;
	}

}

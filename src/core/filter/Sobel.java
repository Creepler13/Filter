package core.filter;

import java.awt.Color;

import core.Filter;

public class Sobel extends Filter {

	double[][] SobelX = { { 1, 2, 1 }, { 0, 0, 0 }, { -1, -2, -1 } };
	double[][] SobelY = { { 1, 0, -1 }, { 2, 0, -2 }, { 1, 0, -1 } };

	@Override
	public Color calculate(Color[][] grid) {

		double grayX = 0, grayY = 0, gray = 0;

		for (int i = 0; i < getSize(); i++) {
			for (int j = 0; j < getSize(); j++) {

				double grayTemp = (grid[i][j].getRed() * 0.299 + grid[i][j].getGreen() * 0.567
						+ grid[i][j].getBlue() * 0.114);

				grayX = grayX + grayTemp * SobelX[i][j];
				grayY = grayY + grayTemp * SobelY[i][j];

			}
		}

		gray = Math.sqrt(grayX * grayX + grayY * grayY);

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

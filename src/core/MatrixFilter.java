package core;

import java.awt.Color;

public abstract class MatrixFilter extends Filter {

	@Override
	public Color calculate(Color[][] grid) {

		double gray = 0;

		for (int i = 0; i < getSize(); i++) {
			for (int j = 0; j < getSize(); j++) {

				gray = gray
						+ (grid[i][j].getRed() * 0.299 + grid[i][j].getGreen() * 0.567 + grid[i][j].getBlue() * 0.114)
								* getMatrix()[i][j];

			}
		}

		gray = gray / getDivisor();

		if (gray < 0)
			gray = 0;
		if (gray > 255)
			gray = 255;

		return new Color((int) gray, (int) gray, (int) gray);

	}

	public abstract int getDivisor();

	public abstract double[][] getMatrix();

}

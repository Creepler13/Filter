package core;

import java.awt.Color;

public abstract class MatrixFilter extends Filter {

	@Override
	public Color calculate(Color[][] grid) {

		double r = 0, g = 0, b = 0;

		for (int i = 0; i < getSize(); i++) {
			for (int j = 0; j < getSize(); j++) {

				r = r + grid[i][j].getRed() * getMatrix()[i][j];
				g = g + grid[i][j].getGreen() * getMatrix()[i][j];
				b = b + grid[i][j].getBlue() * getMatrix()[i][j];

			}
		}

		r = r / getDivisor();
		g = g / getDivisor();
		b = b / getDivisor();

		r = r < 0 ? 0 : r > 255 ? 255 : r;
		g = g < 0 ? 0 : g > 255 ? 255 : g;
		b = b < 0 ? 0 : b > 255 ? 255 : b;

		return new Color((int) r, (int) g, (int) b);

	}

	public abstract int getDivisor();

	public abstract double[][] getMatrix();

}

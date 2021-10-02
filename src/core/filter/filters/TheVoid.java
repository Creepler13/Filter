package core.filter.filters;

import java.awt.Color;

import core.filter.filterTypes.Filter;

public class TheVoid extends Filter {

	@Override
	public Color calculate(Color[][] grid) {
		boolean[][] gray = new boolean[3][3];

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				Color c = grid[i][j];
				gray[i][j] = (c.getRed() * 0.299 + c.getGreen() * 0.567 + c.getBlue() * 0.114) < 126.5;
			}
		}

		if (gray[0][1] || gray[1][1] || gray[2][1] || gray[0][2] || gray[1][2] || gray[2][2])
			return Color.BLACK;

		if (gray[0][0] || gray[1][0] || gray[2][0])
			return Color.WHITE;

		return grid[1][1];

	}

	@Override
	public int getSize() {
		// TODO Auto-generated method stub
		return 3;
	}

}

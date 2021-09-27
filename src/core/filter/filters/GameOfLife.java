package core.filter.filters;

import java.awt.Color;

import core.filter.filterTypes.Filter;

public class GameOfLife extends Filter {

	@Override
	public Color calculate(Color[][] grid) {
		// TODO Auto-generated method stub

		int lives = 0;
		int main = 0;

		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid.length; j++) {

				Color c = grid[i][j];
				double gray = (c.getRed() * 0.299 + c.getGreen() * 0.567 + c.getBlue() * 0.114);

				if (i == 0 && j == 0)
					main = gray > 127.5 ? 1 : 0;
				else
					lives += gray > 127.5 ? 1 : 0;
			}
		}

		main = lives > 3 || lives < 2 ? 0 : 1;

		return main == 1 ? Color.WHITE : Color.BLACK;
	}

	@Override
	public int getSize() {
		// TODO Auto-generated method stub
		return 3;
	}

}

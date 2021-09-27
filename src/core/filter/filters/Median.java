package core.filter.filters;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Comparator;

import core.filter.filterTypes.Filter;

public class Median extends Filter {

	public Color calculate(Color[][] grid) {

		ArrayList<Color> grid2 = new ArrayList<>();

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {

				grid2.add(grid[i][j]);

			}
		}

		grid2.sort(new Comparator<Color>() {

			@Override
			public int compare(Color o1, Color o2) {

				if (o1.getRGB() > o2.getRGB())
					return 1;

				if (o1.getRGB() == o2.getRGB())
					return 0;

				if (o1.getRGB() < o2.getRGB())
					return -1;

				return 0;
			}

		});

		Color newColor = grid2.get(4);

		return newColor;

	}

	@Override
	public int getSize() {
		// TODO Auto-generated method stub
		return 3;
	}

}

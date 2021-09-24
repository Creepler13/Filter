package core;

import java.awt.Color;

import de.informatics4kids.Picture;

public abstract class Filter {

	public abstract Color calculate(Color[][] grid);

	public abstract int getSize();

	public Picture generate(Picture pic) {
		Picture picGenerated = new Picture(pic.widthX(), pic.heightY());

		for (int x = 0; x < pic.widthX() - getSize() + 1; x++) {
			for (int y = 0; y < pic.heightY() - getSize() + 1; y++) {

				Color[][] grid = new Color[getSize()][getSize()];

				for (int i = 0; i < getSize(); i++) {
					for (int j = 0; j < getSize(); j++) {

						grid[i][j] = pic.getColor(x + i, y + j);

					}
				}

				Color newColor = calculate(grid);

				picGenerated.setColor(x + (int) getSize() / 2, y + (int) getSize() / 2, newColor);

			}
		}

		return picGenerated;

	}

	public Picture apply(Picture pic) {
		Picture picApplied = new Picture(pic.widthX(), pic.heightY());

		Picture filterLayer = generate(pic);

		for (int x = 0; x < pic.widthX(); x++) {
			for (int y = 0; y < pic.heightY(); y++) {

				Color picColor = pic.getColor(x, y);
				Color filterLayerColor = filterLayer.getColor(x, y);

				Color newColor = new Color((picColor.getRed() + filterLayerColor.getRed()) % 255,
						(picColor.getGreen() + filterLayerColor.getGreen()) % 255,
						(picColor.getBlue() + filterLayerColor.getBlue()) % 255);

				picApplied.setColor(x, y, newColor);

			}

		}

		return picApplied;
	}

}

package core.filter.filterTypes;

import java.awt.Color;

import de.informatics4kids.Picture;

public abstract class Filter {

	public abstract Color calculate(Color[][] grid);

	public abstract int getSize();

	public Picture generate(Picture pic) {
		long t = System.nanoTime();

		Picture picGenerated = new Picture(pic.widthX(), pic.heightY());

		for (int x = 0; x < pic.widthX() - getSize() + 1; x++) {
			for (int y = 0; y < pic.heightY() - getSize() + 1; y++) {

				Color[][] grid = new Color[getSize()][getSize()];

				for (int i = 0; i < getSize(); i++) {
					for (int j = 0; j < getSize(); j++) {

						Color c = pic.getColor(x + i, y + j);
						int gray = (int) (c.getRed() * 0.299 + c.getGreen() * 0.567 + c.getBlue() * 0.114);
						grid[i][j] = new Color(gray, gray, gray);

					}
				}

				Color newColor = calculate(grid);

				picGenerated.setColor(x + (int) getSize() / 2, y + (int) getSize() / 2, newColor);

			}
		}

		if (time)
			System.out.println(((System.nanoTime() - t) / 100000) + " ms");
		return picGenerated;

	}

	private boolean time = false;

	public void timed() {
		time = !time;
	}

	public Picture apply(Picture pic) {

		long t = System.nanoTime();

		Picture picApplied = new Picture(pic.widthX(), pic.heightY());

		for (int x = 0; x < pic.widthX() - getSize() + 1; x++) {
			for (int y = 0; y < pic.heightY() - getSize() + 1; y++) {

				Color[][] grid = new Color[getSize()][getSize()];

				for (int i = 0; i < getSize(); i++) {
					for (int j = 0; j < getSize(); j++) {

						grid[i][j] = pic.getColor(x + i, y + j);

					}
				}

				Color newColor = calculate(grid);

				picApplied.setColor(x + (int) getSize() / 2, y + (int) getSize() / 2, newColor);

			}
		}
		if (time)
			System.out.println(((System.nanoTime() - t) / 100000) + " ms");
		return picApplied;
	}

	public Picture apply(Picture pic, int count) {
		Picture picApplied = pic;
		for (int i = 0; i < count; i++)
			picApplied = apply(picApplied);
		return picApplied;
	}
}

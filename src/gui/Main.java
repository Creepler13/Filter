package gui;

import de.informatics4kids.Picture;

public class Main {

	public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException {

		Picture p = new Picture("src/lib/Wiener_filter_-_my_dog.JPG");

		ImageEditor viewerO = new ImageEditor(p);

		viewerO.openGui();

	}

}

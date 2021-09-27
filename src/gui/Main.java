package gui;

import core.filter.FilterLib;
import core.filter.filterTypes.Filter;
import de.informatics4kids.Picture;

public class Main {

	public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException {

		for (int i = 0; i < filter.length; i++) {
			filter[i] = (Filter) FilterLib.class.getDeclaredFields()[i].get(null);
		}

		Picture p = new Picture("src/lib/Wiener_filter_-_my_dog.JPG");

		Viewer viewerO = new Viewer(p);

		viewerO.addFilter(filter);

		viewerO.show();

	}

	private static Filter[] filter = new Filter[FilterLib.class.getDeclaredFields().length];

}

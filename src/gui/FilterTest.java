package gui;

import java.io.File;

import javax.swing.JFileChooser;

import core.FilterLib;
import core.filterTypes.Filter;
import de.informatics4kids.Picture;
import de.informatics4kids.PictureViewer;

public class FilterTest {

	public static void main(String[] args) {

		JFileChooser jfc = new JFileChooser();
		jfc.showOpenDialog(null);
		File file = jfc.getSelectedFile();

		Picture p = new Picture(file.getAbsolutePath());

		Filter filter = FilterLib.MEDIAN;

		Picture out = filter.apply(p);

		PictureViewer pv = new PictureViewer(out.getPicture());

		pv.show();

		


		PictureViewer pv2 = new PictureViewer(p.getPicture());

		pv2.show();

	}

}

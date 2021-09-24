package gui;

import java.io.File;

import javax.swing.JFileChooser;

import core.Filter;
import core.filter.Scharr;
import core.filter.Scharr2;
import core.filter.Sobel;
import core.filter.Sobel2;
import de.informatics4kids.Picture;
import de.informatics4kids.PictureViewer;

public class FilterTest {

	public static void main(String[] args) {

		JFileChooser jfc = new JFileChooser();
		jfc.showOpenDialog(null);
		File file = jfc.getSelectedFile();

		Picture p = new Picture(file.getAbsolutePath());

		Filter filter = new Scharr();

		Picture out = filter.generate(p);

		PictureViewer pv = new PictureViewer(out.getPicture());

		pv.show();

		filter = new Scharr2();

		out = filter.generate(p);

		PictureViewer pv4 = new PictureViewer(out.getPicture());

		pv4.show();


		PictureViewer pv2 = new PictureViewer(p.getPicture());

		pv2.show();

	}

}

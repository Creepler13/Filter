package gui;

import java.io.File;

import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import core.Filter;
import core.FilterLib;
import de.informatics4kids.Picture;

public class Main {

	public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException {

		JFileChooser jfc = new JFileChooser();
		jfc.showOpenDialog(null);
		File f = jfc.getSelectedFile();

		if (!(f.getAbsolutePath().toLowerCase().endsWith("jpg") || f.getAbsolutePath().toLowerCase().endsWith("png")
				|| f.getAbsolutePath().toLowerCase().endsWith("jpeg"))) {
			JOptionPane.showMessageDialog(null, "The selected file is is not of the right type (jpg,png)",
					"FileType Error", JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		}

		Picture p = new Picture(f.getAbsolutePath());

		Filter[] filter = new Filter[FilterLib.class.getDeclaredFields().length];
		String[] filterNames = new String[FilterLib.class.getDeclaredFields().length];

		for (int i = 0; i < filter.length; i++) {
			filter[i] = (Filter) FilterLib.class.getDeclaredFields()[i].get(null);
			filterNames[i] = filter[i].getClass().getSimpleName();
		}

		JComboBox<String> filterSelector = new JComboBox<>(filterNames);

		int cancel = JOptionPane.showConfirmDialog(null, filterSelector, "Filter selection",
				JOptionPane.OK_CANCEL_OPTION);
		if (cancel == 2 || cancel == -1)
			System.exit(0);

		Viewer viewerO = new Viewer("Original", p.getPicture());

		viewerO.show();

		Viewer viewerG = new Viewer("Original -> " + filterNames[filterSelector.getSelectedIndex()] + " Gray",
				filter[filterSelector.getSelectedIndex()].generate(p).getPicture());

		viewerG.show();
		
		Viewer viewer = new Viewer("Original -> " + filterNames[filterSelector.getSelectedIndex()],
				filter[filterSelector.getSelectedIndex()].apply(p).getPicture());

		viewer.show();

	}

}

package gui;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

import de.informatics4kids.Picture;

public class Main {

	static JFrame frame = new JFrame();

	public static void main(String[] args) {

		JFileChooser jfc = new JFileChooser();
		jfc.showOpenDialog(null);
		File f = jfc.getSelectedFile();

		Picture p = new Picture(f.getAbsolutePath());

	}

}

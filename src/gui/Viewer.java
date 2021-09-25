package gui;

import java.awt.FileDialog;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

public class Viewer implements ActionListener {

	private BufferedImage image; // the rasterized image
	private JFrame frame; // on-screen view
	private String filename, title; // name of file

	/**
	 * Create a picture by reading in a .png, .gif, or .jpg from the given filename
	 * or URL name.
	 */
	public Viewer(String title, BufferedImage image) {
		this.image = image;
		this.title = title;
	}

	/**
	 * Return a JLabel containing this Picture, for embedding in a JPanel, JFrame or
	 * other GUI widget.
	 */
	private JLabel getJLabel() {
		if (image == null) {
			return null;
		} // no image available
		ImageIcon icon = new ImageIcon(image);
		return new JLabel(icon);
	}

	/**
	 * Zeigt das Bild innerhalb einen Fensters an. Das Bild kann als .png oder .jpg
	 * Bild gespeichert werden.
	 */
	public void show() {

		// create the GUI for viewing the image if needed
		if (frame == null) {
			frame = new JFrame(title);

			JMenuBar menuBar = new JMenuBar();
			JMenu menu = new JMenu("Datei");
			menuBar.add(menu);
			JMenuItem menuItem = new JMenuItem(" Speichern...   ");
			menuItem.addActionListener(this);
			menuItem.setAccelerator(
					KeyStroke.getKeyStroke(KeyEvent.VK_S, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
			menu.add(menuItem);

			frame.setJMenuBar(menuBar);
			frame.setContentPane(getJLabel());
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			// frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			frame.setTitle(title);
			frame.setResizable(false);
			frame.pack();
			frame.setVisible(true);
		}

		frame.repaint(/* draw */);
	}

	private void save(String name) {
		save(new File(name));
	}

	/**
	 * Save the picture to a file in a standard image format.
	 */
	private void save(File file) {
		this.filename = file.getName();
		if (frame != null) {
			frame.setTitle(filename);
		}
		String suffix = filename.substring(filename.lastIndexOf('.') + 1);
		suffix = suffix.toLowerCase();
		if (suffix.equals("jpg") || suffix.equals("png")) {
			try {
				ImageIO.write(image, suffix, file);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("Error: filename must end in .jpg or .png");
		}
	}

	/**
	 * Opens a save dialog box when the user selects "Save As" from the menu. This
	 * method should not be called programmatically.
	 */
	public void actionPerformed(ActionEvent e) {
		FileDialog chooser = new FileDialog(frame, "Dateiendung: .png oder .jpg", FileDialog.SAVE);
		chooser.setVisible(true);
		if (chooser.getFile() != null) {
			save(chooser.getDirectory() + File.separator + chooser.getFile());
		}
	}

}

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

import core.filter.GameOfLife;
import core.filterTypes.Filter;
import de.informatics4kids.Picture;

public class Viewer implements ActionListener {

	private BufferedImage image;
	private Picture picture;// the rasterized image
	private JFrame frame; // on-screen view
	private String filename, title; // name of file

	/**
	 * Create a picture by reading in a .png, .gif, or .jpg from the given filename
	 * or URL name.
	 */
	public Viewer(String title, Picture image) {
		setImage(image);
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

	public void setImage(Picture image) {
		this.image = image.getPicture();
		this.picture = image;

		// PictureViewer pv = new PictureViewer(this.image);
		// pv.show();
		if (frame != null) {
			frame.setContentPane(getJLabel());
			frame.pack();
			// frame.repaint();
		}
	}

	/**
	 * Zeigt das Bild innerhalb einen Fensters an. Das Bild kann als .png oder .jpg
	 * Bild gespeichert werden.
	 */

	private JMenuBar menuBar = new JMenuBar();

	public void show() {

		// create the GUI for viewing the image if needed
		if (frame == null) {
			frame = new JFrame(title);

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

		// frame.repaint(/* draw */);
	}

	private void save(String name) {
		save(new File(name));
	}

	private static Filter lastApplied;
	private static int lastAppliedCount = 1;

	public void addFilter(Filter[] filter) {
		JMenu menu = new JMenu("Filter");
		menuBar.add(menu);

		for (Filter f : filter) {
			JMenuItem menuItem = new JMenuItem(f.getClass().getSimpleName());
			menuItem.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {

					setImage(f.apply(picture));

					if (lastApplied != null)
						if (f == lastApplied) {
							lastAppliedCount++;

							String title = "";
							String[] titleSplit = frame.getTitle().split(" ");
							for (int i = 0; i < titleSplit.length - (lastAppliedCount == 2 ? 0 : 1); i++) {
								title += titleSplit[i] + " ";
							}

							frame.setTitle(title + "x" + lastAppliedCount);
							lastApplied = f;

							return;
						}

					frame.setTitle(frame.getTitle() + " -> " + f.getClass().getSimpleName());
					lastApplied = f;
					lastAppliedCount = 1;
				}

			});

			if (f.getClass() == GameOfLife.class)
				menuItem.setAccelerator(
						KeyStroke.getKeyStroke(KeyEvent.VK_X, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));

			menu.add(menuItem);
		}

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

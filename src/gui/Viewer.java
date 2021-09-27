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
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.plaf.DimensionUIResource;

import core.filter.filterTypes.Filter;
import core.filter.filters.GameOfLife;
import de.informatics4kids.Picture;

public class Viewer implements ActionListener {

	private BufferedImage image;
	private Picture picture;// the rasterized image
	private JFrame frame; // on-screen view
	private String filename; // name of file

	private JBackgroundPanel jbc = new JBackgroundPanel();

	/**
	 * Create a picture by reading in a .png, .gif, or .jpg from the given filename
	 * or URL name.
	 */
	public Viewer(Picture image) {
		show();
		setImage(image, true);
	}

	public void setImage(Picture picture, boolean isNew) {
		this.image = picture.getPicture();
		this.picture = picture;

		if (frame != null) {
			if (isNew) {
				frame.remove(jbc);
				jbc = new JBackgroundPanel();
				frame.add(jbc);
			}
			jbc.setBackground(this.image);
			frame.setBounds(frame.getX(), frame.getY(), picture.widthX(), picture.heightY());

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
			frame = new JFrame();

			JMenu menu = new JMenu("File");
			menuBar.add(menu);
			JMenuItem menuItemSave = new JMenuItem(" Save...   ");
			menuItemSave.addActionListener(this);
			menuItemSave.setAccelerator(
					KeyStroke.getKeyStroke(KeyEvent.VK_S, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
			menu.add(menuItemSave);

			JMenuItem menuItemOpen = new JMenuItem(" Open...   ");
			menuItemOpen.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					JFileChooser jfc = new JFileChooser();
					jfc.showOpenDialog(null);
					File f = jfc.getSelectedFile();

					if (!(f.getAbsolutePath().toLowerCase().endsWith("jpg")
							|| f.getAbsolutePath().toLowerCase().endsWith("png")
							|| f.getAbsolutePath().toLowerCase().endsWith("jpeg"))) {
						JOptionPane.showMessageDialog(null, "The selected file is is not of the right type (jpg,png)",
								"FileType Error", JOptionPane.ERROR_MESSAGE);
						return;
					}

					Picture p = new Picture(f.getAbsolutePath());
					setImage(p, true);

				}

			});
			menu.add(menuItemOpen);

			frame.setJMenuBar(menuBar);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setTitle("Original");
			frame.setResizable(false);
			// frame.pack();
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

				//	f.timed();
					setImage(f.apply(picture), false);

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

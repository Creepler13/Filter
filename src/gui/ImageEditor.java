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
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

import core.filter.FilterLib;
import core.filter.filterTypes.Filter;
import core.filter.filters.GameOfLife;
import de.informatics4kids.Picture;

public class ImageEditor {

	public BufferedImage image;
	public Picture picture;
	private String filename;
	private Filter[] filter;

	private JFrame frame;
	private JMenuBar menuBar = new JMenuBar();
	private JBackgroundPanel jbc = new JBackgroundPanel();

	/**
	 * Create a picture by reading in a .png, .gif, or .jpg from the given filename
	 * or URL name.
	 */
	public ImageEditor(Picture image) {
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

	@SuppressWarnings("deprecation")
	public void openGui() {

		// create the GUI for viewing the image if needed
		if (frame == null) {
			frame = new JFrame();

			JMenu menu = new JMenu("File");
			menuBar.add(menu);
			JMenuItem menuItemSave = new JMenuItem("Save...");
			menuItemSave.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					FileDialog chooser = new FileDialog(frame, "Dateiendung: .png oder .jpg", FileDialog.SAVE);
					chooser.setVisible(true);
					if (chooser.getFile() != null) {
						save(chooser.getDirectory() + File.separator + chooser.getFile());
					}
				}
			});
			menuItemSave.setAccelerator(
					KeyStroke.getKeyStroke(KeyEvent.VK_S, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));

			JMenuItem menuItemOpen = new JMenuItem("Open...");
			menuItemOpen.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					JFileChooser jfc = new JFileChooser();
					jfc.showOpenDialog(null);
					File f = jfc.getSelectedFile();
					open(f);
				}
			});

			menu.add(menuItemOpen);
			menu.add(menuItemSave);

			addFilter();
			setImage(picture, true);

			frame.setJMenuBar(menuBar);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setTitle("Original");
			frame.setResizable(true);
			frame.setVisible(true);
		}

		// frame.repaint(/* draw */);
	}

	public void save(String name) {
		save(new File(name));
	}

	public void open(String name) {
		open(new File(name));
	}

	public void open(File file) {
		if (!(file.getAbsolutePath().toLowerCase().endsWith("jpg")
				|| file.getAbsolutePath().toLowerCase().endsWith("png")
				|| file.getAbsolutePath().toLowerCase().endsWith("jpeg"))) {
			if (frame != null) {
				JOptionPane.showMessageDialog(null, "The selected file is is not of the right type (jpg,png)",
						"FileType Error", JOptionPane.ERROR_MESSAGE);
			} else {
				System.err.println("FileType Error - The selected file is is not of the right type (jpg,png)");

			}
			return;
		}

		Picture p = new Picture(file.getAbsolutePath());
		setImage(p, true);

	}

	private static Filter lastApplied;
	private static int lastAppliedCount = 1;

	@SuppressWarnings("deprecation")
	private void addFilter() {
		filter = new Filter[FilterLib.class.getDeclaredFields().length];

		for (int i = 0; i < filter.length; i++) {
			try {
				filter[i] = (Filter) FilterLib.class.getDeclaredFields()[i].get(null);
			} catch (IllegalArgumentException | IllegalAccessException | SecurityException e) {
				e.printStackTrace();
			}
		}

		JMenu menu = new JMenu("Filter");
		menuBar.add(menu);

		for (Filter f : filter) {
			JMenuItem menuItem = new JMenuItem(f.getClass().getSimpleName());
			menuItem.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					applyFilter(f);
				}
			});

			if (f.getClass() == GameOfLife.class)
				menuItem.setAccelerator(
						KeyStroke.getKeyStroke(KeyEvent.VK_X, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));

			menu.add(menuItem);
		}

	}

	public void applyFilter(Filter filter) {

		setImage(filter.apply(picture), false);

		if (frame != null) {
			if (lastApplied != null)
				if (filter == lastApplied) {
					lastAppliedCount++;

					String title = "";
					String[] titleSplit = frame.getTitle().split(" ");
					for (int i = 0; i < titleSplit.length - (lastAppliedCount == 2 ? 0 : 1); i++) {
						title += titleSplit[i] + " ";
					}

					frame.setTitle(title + "x" + lastAppliedCount);
					lastApplied = filter;

					return;
				}

			frame.setTitle(frame.getTitle() + " -> " + filter.getClass().getSimpleName());
			lastApplied = filter;
			lastAppliedCount = 1;

		}
	}

	/**
	 * Save the picture to a file in a standard image format.
	 */
	public void save(File file) {
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

}

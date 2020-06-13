package xyz.disarray.tegrity;

import java.awt.FileDialog;
import java.awt.Font;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.TransferHandler;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import xyz.disarray.Launcher;
import xyz.disarray.tegrity.db.Database;

public class Standalone implements Runnable {

	private JFrame frame;
	private Database db;
	private JList<File> list = new JList<File>();

	public void run() {
		frame.setVisible(true);
	}

	/**
	 * Create the application.
	 */
	public Standalone() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Tegrity - v" + Launcher.VERSION);

		list.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				// If delete is pressed
				if (e.getKeyChar() == KeyEvent.VK_DELETE || e.getKeyChar() == KeyEvent.VK_BACK_SPACE) {
					if (list.getSelectedIndex() != -1) {
						removeFromList(list.getSelectedIndex());
					}
				}
			}
		});

		list.setDragEnabled(true);
		list.setTransferHandler(new FileListTransferHandler(list));

		JLabel lblCurrentDatabase = new JLabel("Current Database:");
		lblCurrentDatabase.setFont(new Font("DialogInput", Font.PLAIN, 12));

		JLabel lblDatabase = new JLabel("None");
		lblDatabase.setFont(new Font("DialogInput", Font.PLAIN, 12));

		JLabel lblItems = new JLabel("Items:");
		lblItems.setFont(new Font("DialogInput", Font.PLAIN, 12));

		JCheckBox chckbxHideUnchanged = new JCheckBox("Hide Unchanged");
		chckbxHideUnchanged.setEnabled(false);
		chckbxHideUnchanged.setFont(new Font("DialogInput", Font.PLAIN, 12));

		JButton btnCompare = new JButton("Compare");
		btnCompare.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnCompare.setEnabled(false);
		btnCompare.setFont(new Font("DialogInput", Font.PLAIN, 12));

		JButton btnOverwrite = new JButton("Save");
		btnOverwrite.setEnabled(false);
		btnOverwrite.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int r = JOptionPane.showConfirmDialog(frame,
						"Saving will overwrite all hashes in the database with the files current hash.\n" + "Continue?",
						"Database Overwrite Confirmation", JOptionPane.YES_NO_OPTION);
				if (r == 0)
					db.save();
			}
		});
		btnOverwrite.setFont(new Font("DialogInput", Font.PLAIN, 12));

		JButton btnExportSelected = new JButton("Export Selected...");
		btnExportSelected.setEnabled(false);
		btnExportSelected.setFont(new Font("DialogInput", Font.PLAIN, 12));

		JCheckBox chckbxHideChanged = new JCheckBox("Hide Changed");
		chckbxHideChanged.setEnabled(false);
		chckbxHideChanged.setFont(new Font("DialogInput", Font.PLAIN, 12));

		JButton button = new JButton("...");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FileDialog fd = new FileDialog(frame, "Select database file", FileDialog.LOAD);
				fd.setDirectory("C:\\");
				fd.setFile("*.json");
				fd.setVisible(true);
				String filePath = fd.getDirectory() + fd.getFile();

				if (filePath != null) {
					File f = new File(filePath);
					System.out.println(f.getAbsolutePath());
					try {
						// Only created if it doesn't exist already
						f.createNewFile();
					} catch (IOException e) {
						e.printStackTrace();
					}
					
					db = new Database();
					db.load(f.getAbsolutePath());

					list.setEnabled(true);

					// Read from the database and add to the list if possible
					DefaultListModel<File> listModel = new DefaultListModel<File>();
					for (String p : db.getFilePaths()) {
						listModel.addElement(new File(p));
						list.setModel(listModel);
					}
					lblDatabase.setText(f.getAbsolutePath());

					chckbxHideUnchanged.setEnabled(true);
					chckbxHideChanged.setEnabled(true);
					btnExportSelected.setEnabled(true);
					btnOverwrite.setEnabled(true);
					btnCompare.setEnabled(true);
				}

			}
		});
		button.setFont(new Font("DialogInput", Font.PLAIN, 12));

		JScrollPane scrollPane = new JScrollPane();

		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(Alignment.TRAILING,
				groupLayout.createSequentialGroup().addContainerGap().addGroup(groupLayout
						.createParallelGroup(Alignment.TRAILING)
						.addComponent(scrollPane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 347, Short.MAX_VALUE)
						.addGroup(Alignment.LEADING,
								groupLayout.createSequentialGroup().addComponent(btnCompare)
										.addPreferredGap(ComponentPlacement.RELATED).addComponent(btnOverwrite)
										.addPreferredGap(ComponentPlacement.RELATED).addComponent(btnExportSelected))
						.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
								.addComponent(button, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED).addComponent(lblCurrentDatabase)
								.addPreferredGap(ComponentPlacement.RELATED).addComponent(lblDatabase))
						.addGroup(Alignment.LEADING,
								groupLayout.createSequentialGroup().addComponent(lblItems)
										.addPreferredGap(ComponentPlacement.RELATED, 59, Short.MAX_VALUE)
										.addComponent(chckbxHideChanged, GroupLayout.PREFERRED_SIZE, 123,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED).addComponent(chckbxHideUnchanged)))
						.addGap(11)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addContainerGap()
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(button, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblCurrentDatabase).addComponent(lblDatabase))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblItems, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
								.addComponent(chckbxHideUnchanged).addComponent(chckbxHideChanged,
										GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 198, Short.MAX_VALUE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(btnCompare)
								.addComponent(btnOverwrite, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnExportSelected, GroupLayout.PREFERRED_SIZE, 25,
										GroupLayout.PREFERRED_SIZE))
						.addGap(11)));
		list.setEnabled(false);

		list.setFont(new Font("DialogInput", Font.PLAIN, 12));
		scrollPane.setViewportView(list);
		frame.getContentPane().setLayout(groupLayout);
	}

	private void removeFromList(int index) {
		DefaultListModel<File> listModel = new DefaultListModel<File>();

		for (int i = 0; i < list.getModel().getSize(); i++) {
			if (i != index)
				listModel.addElement(list.getModel().getElementAt(i));
		}

		list.setModel(listModel);

		db.removeFile(index);
	}

	public void addFile(File file) {
		db.addFile(file);
	}

}

@SuppressWarnings("serial")
class FileListTransferHandler extends TransferHandler {
	private JList<File> list;

	public FileListTransferHandler(JList<File> list) {
		this.list = list;
	}

	public int getSourceActions(JComponent c) {
		return COPY_OR_MOVE;
	}

	public boolean canImport(TransferSupport ts) {
		return ts.isDataFlavorSupported(DataFlavor.javaFileListFlavor);
	}

	public boolean importData(TransferSupport ts) {
		try {
			@SuppressWarnings("rawtypes")
			List data = (List) ts.getTransferable().getTransferData(DataFlavor.javaFileListFlavor);
			if (data.size() < 1) {
				return false;
			}

			DefaultListModel<File> listModel = new DefaultListModel<File>();
			// Transfer over previous elements
			for (int i = 0; i < list.getModel().getSize(); i++) {
				listModel.addElement(list.getModel().getElementAt(i));
			}

			for (Object item : data) {
				File file = (File) item;
				listModel.addElement(file);
				Launcher.STANDALONE.addFile(file);
			}

			list.setModel(listModel);
			return true;
		} catch (UnsupportedFlavorException e) {
			return false;
		} catch (IOException e) {
			return false;
		}
	}
}

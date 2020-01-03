package xyz.disarray.tegrity;

import java.awt.Font;
import java.awt.SystemColor;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.TransferHandler;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.LineBorder;

import xyz.disarray.Launcher;

public class TegrityGUI extends JPanel implements Runnable {

	private JList<File> list = new JList<File>();

	/**
	 * Create the panel.
	 */
	public TegrityGUI() {

		// Read from the database and add to the list if possible
		DefaultListModel<File> listModel = new DefaultListModel<File>();
		for (String p : Launcher.TEGRITY.getDb().getFilePaths()) {
			listModel.addElement(new File(p));
			list.setModel(listModel);
		}

		JLabel lblDragFilesTo = new JLabel("Drag files and folders to add them into the DB:");
		lblDragFilesTo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDragFilesTo.setVerticalAlignment(SwingConstants.TOP);

		JScrollPane scrollPane = new JScrollPane();

		JButton saveButton = new JButton("Save");
		saveButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
		saveButton.setToolTipText("Saves the changes made to the database.");
		saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// TODO: Make it so it only adds/removes from the txt rather than rewriting the
				// entire thing
				Launcher.TEGRITY.getDb().save();
			}
		});
		scrollPane.setViewportView(list);
		list.setBorder(new LineBorder(SystemColor.textHighlight, 2, true));

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
		
		JButton btnCheck = new JButton("Check");
		btnCheck.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnCheck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// TODO: Check file integrity
				/*
				 * for each file,
				 *  - compare hash of db to disk
				 *  - if equal
				 *    - highlight green
				 *  - else
				 *    - highlight red
				 *    
				 *  - output stats & info to console?
				 */
			}
		});
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(10)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
							.addContainerGap())
						.addComponent(lblDragFilesTo)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(saveButton)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnCheck)
							.addContainerGap())))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(11)
					.addComponent(lblDragFilesTo)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(saveButton)
						.addComponent(btnCheck))
					.addGap(11))
		);
		setLayout(groupLayout);

	}

	@Override
	public void run() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException ex) {
		}

		JFrame frame = new JFrame("Database Editor");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent windowEvent) {
				// Finishing adding items
			}
		});

		frame.setResizable(true);
		frame.getContentPane().add(new TegrityGUI());
		frame.pack();
		// frame.setFocusable(true);
		// frame.requestFocus();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	private void removeFromList(int index) {
		DefaultListModel<File> listModel = new DefaultListModel<File>();

		for (int i = 0; i < list.getModel().getSize(); i++) {
			if (i != index)
				listModel.addElement(list.getModel().getElementAt(i));
		}

		list.setModel(listModel);

		Launcher.TEGRITY.getDb().removeFile(index);
	}
}
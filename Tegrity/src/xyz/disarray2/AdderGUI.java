package xyz.disarray2;

import java.awt.BorderLayout;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.TransferHandler;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AdderGUI extends JPanel implements Runnable {

	private JList list = new JList();
	
	/**
	 * Create the panel.
	 */
	public AdderGUI() {
		setLayout(new BorderLayout(0, 0));

		list.setDragEnabled(true);
		list.setTransferHandler(new FileListTransferHandler(list));
		
		JLabel lblDragFilesTo = new JLabel("Drag files to add them into the DB:");
		lblDragFilesTo.setVerticalAlignment(SwingConstants.TOP);
		add(lblDragFilesTo, BorderLayout.NORTH);

		JScrollPane scrollPane = new JScrollPane(list);
		add(scrollPane, BorderLayout.CENTER);

		JButton btnNewButton = new JButton("Save");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// TODO: Save changes to DB
			}
		});
		add(btnNewButton, BorderLayout.SOUTH);

	}

	@Override
	public void run() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException ex) {
		}

		JFrame frame = new JFrame("Add Files to DB");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent windowEvent) {
				// Finishing adding items
			}
		});

		frame.setResizable(true);
		frame.getContentPane().add(new AdderGUI());
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

}

@SuppressWarnings("serial")
class FileListTransferHandler extends TransferHandler {
	private JList list;

	public FileListTransferHandler(JList list) {
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

			DefaultListModel listModel = new DefaultListModel();
			for (Object item : data) {
				File file = (File) item;
				listModel.addElement(file);
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
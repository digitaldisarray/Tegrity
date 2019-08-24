package xyz.disarray;

import java.awt.BorderLayout;
import java.awt.datatransfer.DataFlavor;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileReader;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class FileAdding extends JPanel implements DropTargetListener, Runnable {

	public FileAdding() {
		add(new JLabel("Drag files here to add them to the database"));

	}

	@Override
	public void run() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException ex) {
		}

		JFrame frame = new JFrame("Add Files to DB here");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent windowEvent) {
				// Finishing adding items
			}
		});

		frame.setBounds(100, 100, 300, 350);
		frame.setLayout(new BorderLayout());
		frame.add(new FileAdding());
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	@Override
	public void dragEnter(DropTargetDragEvent e) {

	}

	@Override
	public void dragExit(DropTargetEvent e) {

	}

	@Override
	public void dragOver(DropTargetDragEvent e) {

	}

	@Override
	public void drop(DropTargetDropEvent e) {
		try {
			e.acceptDrop(DnDConstants.ACTION_COPY_OR_MOVE);
			List list = (List) e.getTransferable().getTransferData(DataFlavor.javaFileListFlavor);
			
			
			for(int i = 0; i < list.size(); i++) {
				// Add (File) list.get(i); to the parent file list
			}

		} catch (Exception ex) {
			
		}
	}

	@Override
	public void dropActionChanged(DropTargetDragEvent e) {

	}

}

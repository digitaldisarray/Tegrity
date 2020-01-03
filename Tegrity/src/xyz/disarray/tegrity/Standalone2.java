package xyz.disarray.tegrity;

import java.awt.BorderLayout;
import java.awt.FileDialog;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;

public class Standalone2 extends JFrame {

	private JPanel contentPane;

	public void run() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent windowEvent) {
				// Finishing adding items
			}
		});

		this.setResizable(true);
		this.getContentPane().add(contentPane);
		this.pack();
		// frame.setFocusable(true);
		// frame.requestFocus();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	

	/**
	 * Create the frame.
	 */
	public Standalone2() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JLabel lblCurrentDatabase = new JLabel("Current Database:");
		lblCurrentDatabase.setFont(new Font("DialogInput", Font.PLAIN, 12));
		
		JFrame thisFrame = this;
		JButton button = new JButton("...");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FileDialog fd = new FileDialog(thisFrame, "Select database file", FileDialog.LOAD);
				fd.setDirectory("C:\\");
				fd.setFile("*.json");
				fd.setVisible(true);
				String filename = fd.getFile();
				
				if (filename != null) {
					File f = new File(fd.getFile());
					if(f.exists()) {
						
					}
				}
					
			}
		});
		button.setFont(new Font("DialogInput", Font.PLAIN, 12));
		
		JLabel lblDatabase = new JLabel("None");
		lblDatabase.setFont(new Font("DialogInput", Font.PLAIN, 12));
		
		JScrollPane scrollPane = new JScrollPane();
		
		JLabel lblItems = new JLabel("Items:");
		lblItems.setFont(new Font("DialogInput", Font.PLAIN, 12));
		
		JCheckBox chckbxHideUnchanged = new JCheckBox("Hide Unchanged");
		chckbxHideUnchanged.setFont(new Font("DialogInput", Font.PLAIN, 12));
		
		JButton btnCompare = new JButton("Compare");
		btnCompare.setFont(new Font("DialogInput", Font.PLAIN, 12));
		
		JButton btnOverwrite = new JButton("Overwrite");
		btnOverwrite.setFont(new Font("DialogInput", Font.PLAIN, 12));
		
		JButton btnExportSelected = new JButton("Export Selected...");
		btnExportSelected.setFont(new Font("DialogInput", Font.PLAIN, 12));
		
		JCheckBox chckbxHideChanged = new JCheckBox("Hide Changed");
		chckbxHideChanged.setFont(new Font("DialogInput", Font.PLAIN, 12));
		GroupLayout groupLayout = new GroupLayout(this.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(scrollPane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 347, Short.MAX_VALUE)
						.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
							.addComponent(btnCompare)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnOverwrite)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnExportSelected))
						.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
							.addComponent(button, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblCurrentDatabase)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblDatabase))
						.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
							.addComponent(lblItems)
							.addPreferredGap(ComponentPlacement.RELATED, 59, Short.MAX_VALUE)
							.addComponent(chckbxHideChanged, GroupLayout.PREFERRED_SIZE, 123, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(chckbxHideUnchanged)))
					.addGap(11))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(button, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblCurrentDatabase)
						.addComponent(lblDatabase))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblItems, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
						.addComponent(chckbxHideUnchanged)
						.addComponent(chckbxHideChanged, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 198, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnCompare)
						.addComponent(btnOverwrite, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnExportSelected, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
					.addGap(11))
		);
		
		JList list = new JList();
		list.setFont(new Font("DialogInput", Font.PLAIN, 12));
		scrollPane.setViewportView(list);
		setLayout(groupLayout);
	}

}

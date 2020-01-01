package xyz.disarray.tegrity;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JCheckBox;

public class Standalone extends JPanel {

	/**
	 * Create the panel.
	 */
	public Standalone() {
		
		JLabel lblCurrentDatabase = new JLabel("Current Database:");
		lblCurrentDatabase.setFont(new Font("DialogInput", Font.PLAIN, 12));
		
		JButton button = new JButton("...");
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
		GroupLayout groupLayout = new GroupLayout(this);
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
					.addGap(93))
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
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnCompare)
						.addComponent(btnOverwrite, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnExportSelected, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
					.addGap(10))
		);
		
		JList list = new JList();
		scrollPane.setViewportView(list);
		setLayout(groupLayout);

	}
}


/*
 * Name: Rachel Sun
 * File: NameSurfer.java
 * ---------------------
 * When it is finished, this program will implements the viewer for
 * the baby-name database described in the assignment handout.
 */

import acm.program.*;
import java.awt.event.*;
import javax.swing.*;

public class NameSurfer extends Program implements NameSurferConstants {

	// Define interactors as instance variables.
	private JLabel nameLabel = new JLabel("Name");
	private JTextField textField = new JTextField(TF_LEN);
	private JButton graphButton = new JButton("Graph");
	private JButton clearButton = new JButton("Clear");
	
	// Define the database to be used.
	private NameSurferDataBase namesData;
	
	// Define the canvas to display data.
	private NameSurferGraph graph;

	public void init() {

		// Create a database and load the canvas.
		loadDatabase();
		loadGraph();

		// Add interactors to the application.
		add(nameLabel, NORTH);
		add(textField, NORTH);
		add(graphButton, NORTH);
		add(clearButton, NORTH);

		addActionListeners();

		// For the textField, add keyListener that responds to pressing enter.
		// Reference:
		// http://stackoverflow.com/questions/25007113/pressing-jbutton-with-the-enter-key
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					
					// Detailed comments see method ActionPerformed
					String nameEntry = textField.getText();
					if (namesData.findEntry(nameEntry) != null) {
						NameSurferEntry newInquiry = new NameSurferEntry(namesData.findEntry(nameEntry).toString());
						graph.addEntry(newInquiry);
					}
				}
			}
		});

	}

	/*
	 * Method loadGraph adds a new NameSurferGraph to the application.
	 */
	private void loadGraph() {
		graph = new NameSurferGraph();
		add(graph);
	}

	/*
	 * Method loadDatabase initializes the db that stores the information
	 * from the names-data.txt.
	 */
	private void loadDatabase() {
		namesData = new NameSurferDataBase(NAMES_DATA_FILE);
	}


	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Graph")) {
			String nameEntry = textField.getText();
			if (namesData.findEntry(nameEntry) != null) {
				NameSurferEntry newInquiry = new NameSurferEntry(namesData.findEntry(nameEntry).toString());
				graph.addEntry(newInquiry);
			}
		}
		if (e.getActionCommand().equals("Clear")) {
			graph.clear();
		}
	}
}

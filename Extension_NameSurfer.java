
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

public class Extension_NameSurfer extends Program implements NameSurferConstants {

	// Define interactors as instance variables.
	private JLabel nameLabel = new JLabel("Name");
	private JTextField textField = new JTextField(TF_LEN);
	private JButton graphButton = new JButton("Graph");
	private JButton clearButton = new JButton("Clear");
	private JButton removeButton = new JButton ("Remove");
	
	// Define the database to be used.
	private Extension_NameSurferDataBase namesData;
	
	// Define the canvas to display data.
	private Extension_NameSurferGraph graph;

	public void init() {

		// Create a database and load the canvas.
		loadDatabase();
		loadGraph();

		// Add interactors to the application.
		add(nameLabel, NORTH);
		add(textField, NORTH);
		add(graphButton, NORTH);
		add(clearButton, NORTH);
		add(removeButton, NORTH);

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
						Extension_NameSurferEntry newInquiry = new Extension_NameSurferEntry(namesData.findEntry(nameEntry).toString());
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
		graph = new Extension_NameSurferGraph();
		add(graph);
	}

	/*
	 * Method loadDatabase initializes the db that stores the information
	 * from the names-data.txt.
	 */
	private void loadDatabase() {
		namesData = new Extension_NameSurferDataBase(NAMES_DATA_FILE);
	}

	
	public void actionPerformed(ActionEvent e) {
		
		// Click on JButon graph will add an entry to the NameSurferGraph
		// if the name entered can be traced with record in the database.
		if (e.getActionCommand().equals("Graph")) {
			
			// Get name from the textbox.
			String nameEntry = textField.getText();
			
			// Ask the database to find the related record if any.
			if (namesData.findEntry(nameEntry) != null) {
				
				// Return the result from database, 
				// Generate a new entry from the result.
				Extension_NameSurferEntry newInquiry = new Extension_NameSurferEntry(namesData.findEntry(nameEntry).toString());
				
				// Add that new entry to the graph.
				graph.addEntry(newInquiry);
			}
		}
		
		// Click on JButton clear asks the NameSurferGraph to clear its contents.
		if (e.getActionCommand().equals("Clear")) {
			graph.clear();
		}
		
		if (e.getActionCommand().equals("Remove")){
			String nameEntry = textField.getText().toLowerCase();
			
			for (int i = 0; i < graph.entryList.size(); i++){
				if (nameEntry.equals(graph.entryList.get(i).getName().toLowerCase())){
					graph.removeEntry(graph.entryList.get(i));
				}
			}			
			
		}
	}
}

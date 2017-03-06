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

	// Define some interactors as instance variables.
	private JLabel nameLabel = new JLabel ("Name");
	private JTextField textField = new JTextField (TF_LEN);
	private JButton graphButton = new JButton ("Graph");
	private JButton clearButton = new JButton ("Clear");
	private NameSurferDataBase namesData;
	private NameSurferGraph graph;
	
/* Method: init() */
/**
 * This method has the responsibility for reading in the data base
 * and initializing the interactors at the top of the window.
 */
	public void init() {
		
		loadDatabase();
		loadGraph();
		
		add (nameLabel, NORTH);
	    add (textField, NORTH);
	    add (graphButton, NORTH);
	    add (clearButton, NORTH);
	    
	    addActionListeners();  

	    // http://stackoverflow.com/questions/25007113/pressing-jbutton-with-the-enter-key
	    textField.addKeyListener(new KeyAdapter() {
	    	@Override
	    	public void keyPressed (KeyEvent e){
	    		if (e.getKeyCode() == KeyEvent.VK_ENTER){
	    			String nameEntry = textField.getText();
	    			if (namesData.findEntry(nameEntry) != null){
	    				NameSurferEntry newInquiry = new NameSurferEntry (namesData.findEntry(nameEntry).toString());
	    				graph.addEntry(newInquiry);
	    		}
	    	}
	    }
	    	
	    });
	    
	    
	   
	}
	
	public void run() {
		
	}
	

	


private void loadGraph() {
	graph = new NameSurferGraph();
	add(graph);
	
}

private void loadDatabase() {
	namesData = new NameSurferDataBase(NAMES_DATA_FILE);
	
}

/* Method: actionPerformed(e) */
/**
 * This class is responsible for detecting when the buttons are
 * clicked, so you will have to define a method to respond to
 * button actions.
 */
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Graph")){
			String nameEntry = textField.getText();
			if (namesData.findEntry(nameEntry) != null){
				NameSurferEntry newInquiry = new NameSurferEntry (namesData.findEntry(nameEntry).toString());
				graph.addEntry(newInquiry);
			}			
		}
		if (e.getActionCommand().equals("Clear")){
			graph.clear();
		}
	}
}

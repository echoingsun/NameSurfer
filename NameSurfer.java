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

public class NameSurfer extends ConsoleProgram implements NameSurferConstants {

	// Define some interactors as instance variables.
	private JLabel nameLabel = new JLabel ("Name");
	private JTextField textField = new JTextField (TF_LEN);
	private JButton graph = new JButton ("Graph");
	private JButton clear = new JButton ("Clear");
	
/* Method: init() */
/**
 * This method has the responsibility for reading in the data base
 * and initializing the interactors at the top of the window.
 */
	public void init() {
	    add (nameLabel, NORTH);
	    add (textField, NORTH);
	    add (graph, NORTH);
	    add (clear, NORTH);
	    
	    addActionListeners();
	   
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
			println ("Graph: " + nameEntry);
		}
		if (e.getActionCommand().equals("Clear")){
			println ("Clear");
		}
	}
}

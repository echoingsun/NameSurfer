/*
 * File: NameSurferGraph.java
 * ---------------------------
 * This class represents the canvas on which the graph of
 * names is drawn. This class is responsible for updating
 * (redrawing) the graphs whenever the list of entries changes
 * or the window is resized.
 */

import acm.graphics.*;
import java.awt.event.*;
import java.util.*;
import java.awt.*;

public class NameSurferGraph extends GCanvas
	implements NameSurferConstants, ComponentListener {



	/**
	* Creates a new NameSurferGraph object that displays the data.
	*/
	public NameSurferGraph() {
		addComponentListener(this);
	}
	
	
	/**
	* Clears the list of name surfer entries stored inside this class.
	*/
	public void clear() {
		// You fill this in //
	}
	
	
	/* Method: addEntry(entry) */
	/**
	* Adds a new NameSurferEntry to the list of entries on the display.
	* Note that this method does not actually draw the graph, but
	* simply stores the entry; the graph is drawn by calling update.
	*/
	public void addEntry(NameSurferEntry entry) {
		int top = GRAPH_MARGIN_SIZE;
		int bottom = this.getHeight() - GRAPH_MARGIN_SIZE;
		int yRange = bottom - top;
		
		double interval = this.getWidth() / NDECADES;
		
		for (int i = 1; i < NDECADES; i ++){
			int value = entry.getRank(i);
			int _value = entry.getRank(i-1);
			double xi = interval * i;
			double yi = top + yRange * (value * 1.00 / MAX_RANK );
			double _xi = interval * (i-1);
			double _yi = top + yRange * (_value * 1.00 / MAX_RANK );
			GLine graphLine = new GLine (_xi,_yi,xi,yi);
			this.add(graphLine);
		}
	}
	
	
	/**
	* Updates the display image by deleting all the graphical objects
	* from the canvas and then reassembling the display according to
	* the list of entries. Your application must call update after
	* calling either clear or addEntry; update is also called whenever
	* the size of the canvas changes.
	*/
	public void update() {
		this.removeAll();
		this.drawBackground();
	}
	
	private void drawBackground(){
		
		double interval = this.getWidth() / NDECADES;
		for (int i = 0; i < NDECADES - 1; i ++){
			GLine line = new GLine (interval * (i+1), 0, interval * (i+1), this.getHeight());
			this.add(line);

		}
		
		for (int i = 0; i < NDECADES; i ++){
			int decade = START_DECADE + 10 * i;
			String decadeStr = Integer.toString(decade);
			GLabel decadeLabel = new GLabel (decadeStr);
			add (decadeLabel, IDT + interval * i, this.getHeight() - IDT);
		}
		
		GLine upperMargin = new GLine(0, GRAPH_MARGIN_SIZE, this.getWidth(), GRAPH_MARGIN_SIZE);
		GLine bottomMargin = new GLine(0, this.getHeight() - GRAPH_MARGIN_SIZE, this.getWidth(), this.getHeight() - GRAPH_MARGIN_SIZE);
		
		this.add(upperMargin);
		this.add(bottomMargin);
		

	}
	
	
	/* Implementation of the ComponentListener interface */
	public void componentHidden(ComponentEvent e) { }
	public void componentMoved(ComponentEvent e) { }
	public void componentResized(ComponentEvent e) { update(); }
	public void componentShown(ComponentEvent e) { }
}

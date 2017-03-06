
/*
 * Name: Rachel Sun
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

public class NameSurferGraph extends GCanvas implements NameSurferConstants, ComponentListener {

	// Define an array list that will be used to store the entries.
	ArrayList<NameSurferEntry> entryList = new ArrayList<NameSurferEntry>();

	/*
	 * The constructor creates a new NameSurferGraph (GCanvas).
	 */
	public NameSurferGraph() {
		addComponentListener(this);
	}

	/*
	 * Method clear empties the record in the arrayList entryList, and
	 * simultaneously update (clear) the graph.
	 */
	public void clear() {
		entryList.clear();
		this.update();
	}

	/*
	 * Method addEntry takes a NameSurferEntry and records it into the arrayList
	 * entryList. By doing so this particular entry will be ready to be put on
	 * canvas.
	 */
	public void addEntry(NameSurferEntry entry) {

		// Add this entry to the array list.
		entryList.add(entry);

		// Update graph.
		this.update();

	}

	/*
	 * Method drawGraph defines how a single entry is displayed on the graph. It
	 * is to be called by the update method every time the graph is updated.
	 */
	private void drawGraph(int entryListIndex) {

		// The method takes in an arrayList index,
		// and gets that entry first.
		NameSurferEntry entry = entryList.get(entryListIndex);

		// For convenience, make variables to store the size,
		// dimensions of the graph, label, line, etc.
		int top = GRAPH_MARGIN_SIZE;
		int bottom = this.getHeight() - GRAPH_MARGIN_SIZE;
		int yRange = bottom - top;

		double interval = this.getWidth() / NDECADES;

		for (int i = 1; i < NDECADES; i++) {
			String nameNRank = "";
			String _nameNRank = "";

			int value = entry.getRank(i);
			if (value == 0) {
				value = MAX_RANK;
				nameNRank = entry.getName() + " *";
			} else {
				nameNRank = entry.getName() + " " + Integer.toString(value);
			}

			int _value = entry.getRank(i - 1);
			if (_value == 0) {
				_value = MAX_RANK;
				_nameNRank = entry.getName() + " *";
			} else {
				_nameNRank = entry.getName() + " " + Integer.toString(_value);
			}

			double xi = interval * i;
			double yi = top + yRange * (value * 1.00 / MAX_RANK);
			double _xi = interval * (i - 1);
			double _yi = top + yRange * (_value * 1.00 / MAX_RANK);

			GLine graphLine = new GLine(_xi, _yi, xi, yi);
			GLabel nameNRankLabel = new GLabel(nameNRank);
			GLabel _nameNRankLabel = new GLabel(_nameNRank);

			Color c = null;
			switch (entryListIndex % 4) {
			case 0:
				c = Color.BLACK;
				break;
			case 1:
				c = Color.RED;
				break;
			case 2:
				c = Color.BLUE;
				break;
			case 3:
				c = Color.MAGENTA;
				break;
			default:
				c = Color.BLACK;
				break;
			}

			graphLine.setColor(c);
			nameNRankLabel.setColor(c);

			this.add(graphLine);

			if (i == 1) {
				_nameNRankLabel.setColor(nameNRankLabel.getColor());
				this.add(_nameNRankLabel, _xi + IDT, _yi - IDT);
			}
			this.add(nameNRankLabel, xi + IDT, yi - IDT);

		}

	}

	/**
	 * Updates the display image by deleting all the graphical objects from the
	 * canvas and then reassembling the display according to the list of
	 * entries. Your application must call update after calling either clear or
	 * addEntry; update is also called whenever the size of the canvas changes.
	 */
	public void update() {
		this.removeAll();
		this.drawBackground();

		for (int i = 0; i < entryList.size(); i++) {
			drawGraph(i);
		}
	}

	private void drawBackground() {

		double interval = this.getWidth() / NDECADES;
		for (int i = 0; i < NDECADES - 1; i++) {
			GLine line = new GLine(interval * (i + 1), 0, interval * (i + 1), this.getHeight());
			this.add(line);

		}

		for (int i = 0; i < NDECADES; i++) {
			int decade = START_DECADE + 10 * i;
			String decadeStr = Integer.toString(decade);
			GLabel decadeLabel = new GLabel(decadeStr);
			add(decadeLabel, IDT + interval * i, this.getHeight() - IDT);
		}

		GLine upperMargin = new GLine(0, GRAPH_MARGIN_SIZE, this.getWidth(), GRAPH_MARGIN_SIZE);
		GLine bottomMargin = new GLine(0, this.getHeight() - GRAPH_MARGIN_SIZE, this.getWidth(),
				this.getHeight() - GRAPH_MARGIN_SIZE);

		this.add(upperMargin);
		this.add(bottomMargin);

	}

	/* Implementation of the ComponentListener interface */
	public void componentHidden(ComponentEvent e) {
	}

	public void componentMoved(ComponentEvent e) {
	}

	public void componentResized(ComponentEvent e) {
		update();
	}

	public void componentShown(ComponentEvent e) {
	}
}

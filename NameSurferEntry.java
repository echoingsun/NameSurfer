
/*
 * Name: Rachel Sun
 * File: NameSurferEntry.java
 * --------------------------
 * This class represents a single entry in the database.  Each
 * NameSurferEntry contains a name and a list giving the popularity
 * of that name for each decade stretching back to 1900.
 */

import acm.util.*;
import java.util.*;

public class NameSurferEntry implements NameSurferConstants {

	// Each entry will have two properties:
	// A name and the name' ranks.
	// The latter is kept in an int array.
	private String name;
	private int[] rank = new int[NDECADES];

	/*
	 * The constructor takes a string from the database's findEntry method and
	 * creates a new NameSurferEntry based on the string.
	 * The name is the first subString.
	 * The ranks are extracted from the rest subStrings.
	 */
	public NameSurferEntry(String line) {
		String[] parts = line.split(" ");
		this.name = parts[0];
		for (int i = 0; i < rank.length; i++) {
			this.rank[i] = Integer.parseInt(parts[i + 1]);
		}

	}


	/*
	 * Returns the name associated with this entry.
	 */
	public String getName() {
		return name;
	}

	/*
	 * Returns the rank associated with an entry for a particular decade. 
	 */
	public int getRank(int decadeIndex) {
		return rank[decadeIndex];
	}


	/*
	 * Returns a string that makes it easy to see the value of a
	 * NameSurferEntry.
	 */
	public String toString() {
		String s = "";
		for (int i = 0; i < rank.length; i++) {
			s += Integer.toString(rank[i]) + " ";
		}
		return (name + " " + s);
	}
}

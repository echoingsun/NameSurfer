
/*
 * Name: Rachel Sun
 * File: NameSurferDataBase.java
 * -----------------------------
 * This class keeps track of the complete database of names.
 * The constructor reads in the database from a file, and
 * the only public method makes it possible to look up a
 * name and get back the corresponding NameSurferEntry.
 * Names are matched independent of case, so that "Eric"
 * and "ERIC" are the same names.
 */

import acm.util.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Extension_NameSurferDataBase implements NameSurferConstants {

	// The database is actually a HashMap -
	// It reads the data from the file and stores them into the HashMap.
	HashMap<String, Extension_NameSurferEntry> dataBase = new HashMap<String, Extension_NameSurferEntry>();

	/*
	 * The constructor creates a new NameSurferDataBase and initializes it using
	 * the data in the specified file. The constructor throws an error exception
	 * if the requested file does not exist or if an error occurs as the file is
	 * being read.
	 */
	public Extension_NameSurferDataBase(String filename) {

		// Read file
		try {
			BufferedReader br = new BufferedReader(new FileReader(filename));
			while (true) {
				String line = br.readLine();
				if (line == null)
					break;

				// For each line read, generate a new entry
				// and put it in the hashmap.
				Extension_NameSurferEntry newEntry = new Extension_NameSurferEntry(line);
				dataBase.put(newEntry.getName().toLowerCase(), newEntry);
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/*
	 * Returns the NameSurferEntry associated with this name, if one exists. If
	 * the name does not appear in the database, this method returns null.
	 */
	public Extension_NameSurferEntry findEntry(String name) {
		if (dataBase.containsKey(name.toLowerCase())) {
			return dataBase.get(name.toLowerCase());
		}
		return null;

	}
}

package ca.mcgill.ecse.flexibook.persistence;

import ca.mcgill.ecse.flexibook.model.FlexiBook;

public class FlexibookPersistence {
private static String filename = "data.flexibook";
	
	public static void setFilename(String filename) {
		FlexibookPersistence.filename = filename;
	}
	
	public static void save(FlexiBook btms) {
	    PersistenceObjectStream.serialize(btms);
	}

	public static FlexiBook load() {
	    PersistenceObjectStream.setFilename(filename);
	    FlexiBook flexibook = (FlexiBook) PersistenceObjectStream.deserialize();
	    // model cannot be loaded - create empty BTMS
	    if (flexibook == null) {
	        flexibook = new FlexiBook();
	    }
	    else {
			flexibook.reinitialize();
		}
	    return flexibook;
	}
}

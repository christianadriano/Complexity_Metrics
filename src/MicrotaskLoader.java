import java.util.HashMap;

/**
 * Load microtasks from a csv file and source code files
 * The csv file contains the start and end line of the source code of each microtask
 * The source code file contains the code of the Java method
 * 
 * @author Christian Adriano
 *
 */
public class MicrotaskLoader {

	HashMap<String,MicrotaskComplexity> microtaskComplexityMap;
	
	
	
	public  HashMap<String,MicrotaskComplexity> loadMicrotasks(String sourceCodeFilePath, String csvFilePath) {
		this.microtaskComplexityMap = new HashMap<String,MicrotaskComplexity>();
		return null;
	}
	
	
}

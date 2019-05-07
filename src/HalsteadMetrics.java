import java.util.HashMap;

/**
 * Computes Halstead metrics of 
 * 
 * @author Chris Adriano
 *
 */
public class HalsteadMetrics {

	/** 
	 * List of Operators (Java operators and types)
	 */
	String[] operatorsList = {"if","else","for","while","{","}","case","Class",
			"catch","try","final","static","public","private","protected",
			"=","+","-","?",":","/","\\","*",".","break","do"};
	
	HashMap<String,Integer> operatorsHash;
	
	HashMap<String,Integer> operandsHash;
		
	public HalsteadMetrics() {
		this.operatorsHash = new HashMap<String,Integer>();
		for(String operator:operatorsList) {
			this.operatorsHash.put(operator, new Integer(0));
		}
	}
	
}

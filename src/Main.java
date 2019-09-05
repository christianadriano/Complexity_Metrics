import java.util.HashMap;

/**
 * Computes Halstead metrics of 
 * 

 * 
 * @author Chris Adriano
 *
 */
public class Main {

	/** 
	 * List of Operators (Java keywords and operators)
	 */
	String[] operatorsList = {
			"if","else","for","while","case","Class","switch",
			"catch","try","finally","final","throws","throw","return",
			"static","public","private","protected",
			"break","do","continue","goto",
			"instanceof","long","native","new","this","package","interface",
			"super","strictfp","synchronized",
			"transient","volatile","void","extends","implements",
			"byte","assert","boolean","char","const","double","int","float","short",
			"enum"};
	
	String[] javaOperationsList = {"=","+","-","?","!","%",">","<",":","/","\\","*",".","&","^","|","~"};
	
	HashMap<String,Integer> operatorsHash;
	
	HashMap<String,Integer> operandsHash;
		
	/**  
	 * Constructor initializes the data structure with operators
	 */
	public Main() {
		this.operatorsHash = new HashMap<String,Integer>();
		for(String operator:operatorsList) {
			this.operatorsHash.put(operator, new Integer(0));
		}
	}
	
	
	
}

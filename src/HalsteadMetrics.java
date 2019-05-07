import java.util.HashMap;

/**
 * Computes Halstead metrics of 
 * 
 * @author Chris Adriano
 *
 */
public class HalsteadMetrics {

	/** 
	 * List of Operators (Java keywords and operators)
	 */
	String[] operatorsList = {"if","else","for","while","{","}","case","Class",
			"catch","try","finally","final","throws","throw","static","public","private","protected",
			"=","+","-","?","!","%",">","<",":","/","\\","*",".","&","^","|","~",
			"break","do","continue",
			"instanceof","long","native","new","this","package","interface",
			"return","short","super","strictfp","switch","synchronized",
			"transient","volatile","void","goto","extends","implements",
			"byte","assert","boolean","char","const","double","int","float",
			"enum"};
	
	HashMap<String,Integer> operatorsHash;
	
	HashMap<String,Integer> operandsHash;
		
	public HalsteadMetrics() {
		this.operatorsHash = new HashMap<String,Integer>();
		for(String operator:operatorsList) {
			this.operatorsHash.put(operator, new Integer(0));
		}
	}
	
}

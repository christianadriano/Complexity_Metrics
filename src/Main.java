import java.util.HashMap;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Computes Halstead metrics of 
 * 

 * 
 * @author Chris Adriano
 *
 */

import java.util.*;

public class Main {

	/** 
	 * List of Operators (Java keywords and operators)
	 */
	String[] keywordsList= {
			"if","else","for","while","case","Class","switch",
			"catch","try","finally","final","throws","throw","return",
			"static","public","private","protected",
			"break","do","continue","goto",
			"instanceof","long","native","new","this","package","interface",
			"super","strictfp","synchronized",
			"transient","volatile","void","extends","implements",
			"byte","assert","boolean","char","const","double","int","float","short",
			"enum"};
	
	String[] operatorsList = {"=","+","-","?","!","%",">","<","*","&","^","|","~"};
	
	String[] stopKeysList = {",",";",":","/","//",".","(",")","[","]","{","}"};

	
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
		for(String keyword:keywordsList) {
			this.operatorsHash.put(keyword, new Integer(0));
		}
	}
	
	public List<String> readFileToList(String filePath) {
		try {
			return(Files.readAllLines(Paths.get(filePath)));
		}
		catch(Exception e) {
			return null;
		}
	}

	
	private String substituteStopWords(String line) {
		for(String stopWord: this.stopKeysList) {
			line = line.replace(stopWord, " ");
		}
		return line;
	}
	
	private void load(String filePath) {
		List<String> list_lines = this.readFileToList(filePath);
		for(String line:list_lines) {
			//tokenize it
			String[] token_list = line.split("");
			for(String token:token_list) {
				if(this.operatorsHash.containsKey(token)) {
					Integer value_obj = this.operatorsHash.get(token);
					value_obj = new Integer(value_obj.intValue() + 1); 
					this.operatorsHash.put(token, value_obj);
				}
				else {
					if(this.operandsHash.containsKey(token)) {
						Integer value_obj = this.operandsHash.get(token);
						value_obj = new Integer(value_obj.intValue() + 1); 
						this.operandsHash.put(token, value_obj);
					}	
					else {
						this.operandsHash.put(token, new Integer(1));
					}
				}	
			}
		}
	}
	
	public static void main(String args[]) {
		Main main = new Main();
		main.load("C:\\Users\\Christian\\Documents\\GitHub\\Complexity_Metrics\\test\\translate.java")
	}

	
	
}

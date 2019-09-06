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
			"instanceof","long","native","new","null","this","package","interface",
			"super","strictfp","synchronized",
			"transient","volatile","void","extends","implements",
			"byte","assert","boolean","char","const","double","int","float","short",
	"enum"};

	String[] singleOperatorsList = {"=","+","-","?","!","%",">","<","*","&","^","|","~"};

	String[] doubleOperatorsList = {"==","++","+=","--","-=","!=","&&","||"};

	String[] stopKeysList = {",",";",":","/","//",".","(",")","[","]","{","}"};


	HashMap<String,Integer> doubleOperatorsHash;
	HashMap<String,Integer> singleOperatorsHash;
	HashMap<String,Integer> stopKeyHash;
	HashMap<String,Integer> operandsHash;

	/**  
	 * Constructor initializes the data structure with operators
	 */
	public Main() {
		this.singleOperatorsHash = new HashMap<String,Integer>();
		this.doubleOperatorsHash = new HashMap<String,Integer>();
		this.stopKeyHash = new HashMap<String,Integer>();

		for(String operator:singleOperatorsList) {
			this.singleOperatorsHash.put(operator, new Integer(0));
		}
		for(String operator:doubleOperatorsList) {
			this.doubleOperatorsHash.put(operator, new Integer(0));
		}
		for(String keyword:keywordsList) {
			this.singleOperatorsHash.put(keyword, new Integer(0));
		}
		this.operandsHash = new HashMap<String,Integer>();
	}

	public List<String> readFileToList(String filePath) {
		try {
			return(Files.readAllLines(Paths.get(filePath)));
		}
		catch(Exception e) {
			return null;
		}
	}

	private String removeStringText(String line) {
		int start = 0;
		int end = 0;
		while(start!=-1 & end!=-1) {
			start = line.indexOf("\"");
			if(start!=-1) {
				end = line.indexOf("\"", start+1);
				if(start!=-1 & end!=-1) {
					String toRemove = line.substring(start,end);
					line = line.replace(toRemove, " ");
				}
			}
		}
		return line;
	}


	private String isolateDoubleOperators(String line) {
		for(String doubleOperator: this.doubleOperatorsList) {
			line = line.replace(doubleOperator, " "+doubleOperator+" ");
		}
		return line;
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
			line = this.substituteStopWords(line);
			line = this.isolateDoubleOperators(line);
			line = this.removeStringText(line);
			
			String[] token_list = line.split(" ");
			for(String token:token_list) {
				if(this.singleOperatorsHash.containsKey(token)) {
					Integer value_obj = this.singleOperatorsHash.get(token);
					value_obj = new Integer(value_obj.intValue() + 1); 
					this.singleOperatorsHash.put(token, value_obj);
				}
				else
					if(this.doubleOperatorsHash.containsKey(token)) {
						Integer value_obj = this.doubleOperatorsHash.get(token);
						value_obj = new Integer(value_obj.intValue() + 1); 
						this.doubleOperatorsHash.put(token, value_obj);
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

	private void print() {
		System.out.println(this.operandsHash.toString());
		System.out.println(this.singleOperatorsHash.toString());
		System.out.println(this.doubleOperatorsHash.toString());

	}

	public static void main(String args[]) {
		Main main = new Main();
		main.load("C:\\Users\\Christian\\Documents\\GitHub\\Complexity_Metrics\\test\\translate2.java");
		main.print();
	}


}

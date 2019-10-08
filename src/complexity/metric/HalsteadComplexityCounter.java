package complexity.metric;

import java.util.HashMap;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Computes Halstead metrics of 
 * 

 * 
 * @author Chris Adriano
 *
 */

import java.util.*;

public class HalsteadComplexityCounter implements MetricsCounter{

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
	
	HalsteadMetrics metrics;

	/**  
	 * Constructor initializes the data structure with operators
	 */
	public HalsteadComplexityCounter() {
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

	private List<String> readFileToList(String filePath) {
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

	/** Coordinator method. Runs all the functions  */
	public void prepare(List<String> lineList) {
		
		for(String line:lineList) {
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
	
	/**
	 * @param itemsMap
	 * @return the number of unique and total items in HashMap.
	 */
	private Integer[] countItems(HashMap<String,Integer> itemsMap){
		int total=0;
		int uniques=0;
		Iterator<String> iter = itemsMap.keySet().iterator();
		while(iter.hasNext()) {
			String key = iter.next();
			Integer value = itemsMap.get(key);
			total = total + value;
			if(value!=0) {
				uniques++;
			}
		}
		
		Integer results[] = {total,uniques};
		return results;
	}
	
	/**
	 * @return Halstead length and volume metrics in the first and second positions respectively 
	 */
	public Double[] compute() {
		Integer results1[] =  countItems(this.singleOperatorsHash);
		Integer results2[] =  countItems(this.doubleOperatorsHash);
		int totalOperators = results1[0] + results2[0];
		int distinctOperators = results1[1] + results2[1];

		Integer results3[] =  countItems(this.operandsHash);
		int totalOperands = results3[0];
		int distinctOperands = results3[1];
			
		this.metrics = new HalsteadMetrics();
		this.metrics.setParameters(distinctOperators, distinctOperands, 
								totalOperators, totalOperands);
		
		Double volume = new Double(metrics.getVolume());
		Double length = new Double(metrics.getProglen());

		Double lengthVolume[] = {length ,volume};
		return lengthVolume;
	}

	public void print() {
		System.out.println("Operands: "+ this.operandsHash.toString());
		System.out.println("Distinct operands: "+this.metrics.DistOperands);
		System.out.println("Total operands: "+this.metrics.TotOperands);
		System.out.println("-----------------");

		System.out.println("Single operators: "+this.singleOperatorsHash.toString());
		System.out.println("Double operators: "+this.doubleOperatorsHash.toString());		
		System.out.println("Distinct operators: "+this.metrics.DistOperators);
		System.out.println("Total operators: "+this.metrics.TotOperators);
	} 
	
	public static void main(String args[]) {
		HalsteadComplexityCounter main = new HalsteadComplexityCounter();
		String filePath = "C:\\Users\\Christian\\Documents\\GitHub\\Complexity_Metrics\\test\\translate2.java"; 
		List<String> lineList = main.readFileToList(filePath);
		main.prepare(lineList);
		Double lengthVolume[] = main.compute();
		System.out.println("Length="+lengthVolume[0].toString()+","+
							"Volume="+lengthVolume[1].toString());
		main.print();
	}


}

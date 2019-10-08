import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Computes the cyclomatic complexity of a set of source code lines
 * 
 * Formula for cyclomatic complexity: 
 * https://www.leepoint.net/principles_and_practices/complexity/complexity-java-method.html
 *
 * @author Christian Adriano
 */
public class CyclomaticComplexityCounter implements MetricsCounter {

	private List<String> lineList;
	
	public void prepare(List<String> lineList) {
		this.lineList = lineList;
	}
	
	/**
	 * 
	 * @param source Java Source code to measure
	 * @return Cyclomatic Complexity of the source
	 */
	public Double[] compute(){
		Double complexity=1.0;

		Pattern patif;
		String factors="if|else|for|while|case|catch|throw|throws|return|do-while|continue|break|default|finally|\\|\\|\\?|&&";

		patif = Pattern.compile(factors);

		for(String line: this.lineList){
			Matcher matif=patif.matcher(line);
			while (matif.find()){
				complexity++;
			}  
		}
		Double [] result = {complexity};
		return result;           
	}


	@Override
	public void print() {
		// TODO Auto-generated method stub
	}	    
	
	public static void main(String args[]){
		CyclomaticComplexityCounter main = new CyclomaticComplexityCounter();
		ArrayList<String> lineList =  new ArrayList<String>();
		String line = "if( a||b && c for do-while throw throws default";
		lineList.add(line);
		line = "continue break else default while case catch";
		lineList.add(line);		
		main.prepare(lineList);
		System.out.println("complexity = "+  main.compute());
	}

}
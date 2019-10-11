package complexity.metric;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Counts source code lines except for comments, empty lines, and lines with a single curly bracket
 * @author Christian Adriano
 *
 */
public class TrimmedLOCCounter implements MetricsCounter {

	private List<String> lineList;
	
	@Override
	public void prepare(List<String> lineList) {
		this.lineList = lineList;
	}

	@Override
	public Double[] compute() {
		double lineCount=0;
		for(String line:this.lineList) {
			line = line.trim();
			if(line.length()>1 &&  
				(!((line.startsWith("//")) || line.startsWith("/*") || line.startsWith("*")))){
				lineCount++;
			}
		}
		Double results[] = {lineCount};
		return results;
	}

	@Override
	public void print() {
		System.out.println(this.lineList);
	}

	private List<String> readFileToList(String filePath) {
		try {
			return(Files.readAllLines(Paths.get(filePath)));
		}
		catch(Exception e) {
			return null;
		}
	}
	
	public static void main(String[] args) {
		TrimmedLOCCounter main = new TrimmedLOCCounter();
		String filePath = "C:\\Users\\Christian\\Documents\\GitHub\\Complexity_Metrics\\test\\translate.java"; 
		List<String> lineList = main.readFileToList(filePath);
		main.prepare(lineList);
		Double results[] = main.compute();
		System.out.println(results[0]);
	}

}

package complexity.metric;

/** 
 * Represents a microtask with regards to its complexity.
 * It keeps infomation about the source code and the metrics for one microtask
 * 
 * @author Christian
 *
 */
public class MicrotaskComplexity {

	String microtaskID="";
	String fileName="";
	StringBuffer sourceCode=new StringBuffer();
	Integer startLine=0;
	Integer endLine=0;
	
	HalsteadMetrics metrics=null;
	
	String programStatementType="";
	
	
}

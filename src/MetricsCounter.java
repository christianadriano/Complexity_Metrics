import java.util.List;

/**
 * Interface to expose the metric calculation of clients
 * 
 * @author Christian Adriano
 *
 */
public interface MetricsCounter {
	
	/**
	 * Necessary pre-processing to count metrics. This method affects the internal
	 * state of the concrete class.
	 * @param the list of source code lines that will be processed
	 */
	public void prepare(List<String> lineList);
	
	/** 
	 * 
	 * @return metrics in a array of double. Check the concrete class to know how to read this array
	 */
	public Double[] compute();
	
}

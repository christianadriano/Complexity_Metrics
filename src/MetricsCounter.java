
public interface MetricsCounter {

	/**
	 * Necessary pre-processing to count metrics. This method affects the internal
	 * state of the concrete class.
	 */
	public void prepare();
	
	/** 
	 * 
	 * @return metrics in a array of double. Check the concrete class to know how to read this array
	 */
	public Double[] compute();
	
}

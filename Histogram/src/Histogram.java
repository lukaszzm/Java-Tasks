import java.util.Map;

/**
 * Histogram interface
 */
public interface Histogram {
    /**
     * Method set a way to generate histogram.
     *
     * @param threads threads number
     * @param bins    bins number
     */
    public void setup(int threads, int bins);

    /**
     * Method gives vector with Integers to histogram.
     *
     * @param vector integer Vector
     */
    public void setVector(Vector vector);

    /**
     * Method allows to check is histogram is ready to deliver.
     *
     * @return true - histogram is ready, false - histogram is not ready yet
     */
    public boolean isReady();

    /**
     * Method returns histogram
     *
     * @return histogram
     */
    public Map<Integer, Integer> histogram();
}
import java.util.Map;
import java.util.function.Consumer;

/**
 * Histogram interface
 */
public interface Histogram {

    public record HistogramResult(int vectorID, Map<Integer, Integer> histogram) {
    }

    /**
     * Method set a way to generate histogram.
     *
     * @param bins              bins number
     * @param histogramConsumer object which contains generated histograms
     */
    public void setup(int bins, Consumer<HistogramResult> histogramConsumer);

    /**
     * Method gives vector of Integer numbers to histogram.
     *
     * @param vectorID unique vector id
     * @param vector   vector
     */
    public void addVector(int vectorID, Vector vector);
}
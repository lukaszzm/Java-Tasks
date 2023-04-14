import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;

public class MultipleHistograms implements Histogram {
    Consumer<HistogramResult> histogramConsumer;

    public class MyThread implements Runnable {
        private final int vectorID;
        private final Vector vector;
        private final Map<Integer, Integer> histogram = new HashMap<>();

        MyThread(int vectorID, Vector vector) {
            this.vectorID = vectorID;
            this.vector = vector;
        }

        @Override
        public void run() {
            for (int i = 0; i < vector.getSize(); i++) {
                histogram.merge(vector.getValue(i), 1, Integer::sum);
            }
            ExecutorService executorService = Executors.newSingleThreadExecutor();
            executorService.submit(new SenderThread(vectorID, histogram));
            executorService.shutdown();
        }
    }

    public class SenderThread implements Runnable {
        private final int vectorID;
        private final Map<Integer, Integer> histogram;

        SenderThread(int vectorID, Map<Integer, Integer> histogram) {
            this.vectorID = vectorID;
            this.histogram = histogram;
        }

        @Override
        public void run() {
            histogramConsumer.accept(new HistogramResult(vectorID, histogram));
        }
    }

    @Override
    public void setup(int bins, Consumer<HistogramResult> histogramConsumer) {
        this.histogramConsumer = histogramConsumer;
    }

    @Override
    public void addVector(int vectorID, Vector vector) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.submit(new MyThread(vectorID, vector));
        executorService.shutdown();
    }
}

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class FastHistogram implements Histogram {
    int threads;
    AtomicInteger threadsDone = new AtomicInteger(0);
    final ConcurrentHashMap<Integer, AtomicInteger> histogramHelper = new ConcurrentHashMap<>();
    final Map<Integer, Integer> histogram = new HashMap<>();
    Vector vector;
    private AtomicInteger count = new AtomicInteger();
    boolean isReady;

    class MyThread implements Runnable {

        @Override
        public void run() {
            int value;
            while ((value = count.getAndIncrement()) < vector.getSize()) {
                int vectorVal = vector.getValue(value);
                histogramHelper.putIfAbsent(vectorVal, new AtomicInteger(0));
                histogramHelper.get(vectorVal).incrementAndGet();
            }
            int help = threadsDone.incrementAndGet();
            if (help == threads) isReady = true;
        }
    }


    @Override
    public void setup(int threads, int bins) {
        this.threads = threads;
    }

    @Override
    public void setVector(Vector vector) {
        this.vector = vector;
        this.isReady = false;
        this.count = new AtomicInteger(0);
        this.histogram.clear();
        this.histogramHelper.clear();
        ExecutorService executorService = Executors.newFixedThreadPool(threads);
        for (int i = 0; i < threads; i++) {
            executorService.submit(new MyThread());
        }
        executorService.shutdown();
    }

    @Override
    public boolean isReady() {
        return isReady;
    }

    @Override
    public Map<Integer, Integer> histogram() {
        if (isReady) {
            histogramHelper.forEach((key, value) -> histogram.put(key, value.intValue()));
            return histogram;
        }
        return null;
    }
}

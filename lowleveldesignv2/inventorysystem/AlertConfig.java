package lowleveldesignv2.inventorysystem;

public class AlertConfig {
    private final int threshold;
    private final AlertListener listener;

    public AlertConfig(int threshold, AlertListener listener) {
        this.threshold = threshold;
        this.listener = listener;
    }

    public int getThreshold() {
        return threshold;
    }

    public AlertListener getListener() {
        return listener;
    }
}

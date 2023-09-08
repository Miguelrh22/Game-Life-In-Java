package Main;

public class Clock {

    private boolean active = false;

    private final int nanosec = 1000000000;
    private final int secPerCycle = 2;

    private int cycle = 0;
    private int nCycle = 0;

    public void startCycle(int nCycles) {
        active = true;
        nCycle = nCycles;
        clock();

    }

    public int getCycle() {
        return cycle;
    }

    private void update() {

        System.out.print(".");
        nCycle--;
        if (nCycle == 0) {
            active = false;
            cycle++;
        }
    }

    private void clock() {

        final double NS_APS = nanosec / secPerCycle;

        long ns_start = System.nanoTime();

        double time;
        double delta = 0;

        while (active) {
            final long ns_loop = System.nanoTime();
            time = ns_loop - ns_start;
            ns_start = ns_loop;
            delta += time / NS_APS;

            while (delta >= 1) {
                update();
                delta--;
            }
        }
    }
}

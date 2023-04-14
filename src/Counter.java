public class Counter implements AutoCloseable {
    private static int counter = 0;
    private static boolean resourcesOpened;

    public Counter() throws RuntimeException {
        if (resourcesOpened) {
            throw new RuntimeException("Resource already opened!");
        }

        resourcesOpened = true;
    }

    public void add() {
        counter++;
    }

    public int getCounter() {
        return counter;
    }

    @Override
    public void close() throws Exception {
        resourcesOpened = false;
    }
}

package memory;

public class MemoryUtils {
    private static final Runtime runtime = Runtime.getRuntime();

    public static long printMemory(String status) {
        runtime.gc();

        long totalMemory = runtime.totalMemory();
        long freeMemory = runtime.freeMemory();
        long usedMemory = totalMemory - freeMemory;

        System.out.println("[" + status + "]" + " usedMemory = " + usedMemory + " bytes");

        return usedMemory;
    }

}

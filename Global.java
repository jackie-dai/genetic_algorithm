public class Global {
    private static String target = "to be or not to be";
    private static int populationSize = 500;
    private static double mutationRate = 0.0;

    public static String getTarget() {
        return Global.target;
    }

    public static int getPopulationSize() {
        return Global.populationSize;
    }

    public static double getMutationRate() {
        return Global.mutationRate;
    }
}

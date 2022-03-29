package animals;

public class Main {
    public static void main(String[] args) {
        String saverFormat = parseArgs(args);
        new App(saverFormat).run();
    }

    private static String parseArgs(String[] args) {
        if (args == null || args.length < 2) {
            return "json";
        }

        if (!args[0].equals("-type")) {
            return "json";
        }

        return args[1].toLowerCase();
    }
}

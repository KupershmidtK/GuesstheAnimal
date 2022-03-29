package animals;

import java.text.MessageFormat;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import static animals.ResourceLoader.*;

public class App {
    
    private final static Scanner scanner = new Scanner(System.in);
    private final GuessTree guessTree = new GuessTree();
    private final GuessGame guessGame = new GuessGame(guessTree);
    private final GuessTreeStatistics statistics = new GuessTreeStatistics(guessTree);

    private final TreeSaver saver;
    private final String SAVE_FILE_NAME = "animals";

    public App(String format) {
        this.saver = new TreeSaver(SAVE_FILE_NAME, format);

        String locale = Locale.getDefault().getLanguage();
        switch (locale) {
            case "eo":
                GameUtils.setLangRules(new LanguageRulesEo());
                break;
            case "en":
            default:
                GameUtils.setLangRules(new LanguageRulesEn());
                break;
        }
    }

    public void run() {
        guessTree.setRootNode(saver.load());
        message(GameUtils.sayHallo());
        printEmptyLine();

        if (guessTree.getRootNode() == null) {
            guessGame.askMostLikedAnimal();
        }

        while (true) {
            int choice = gameMenu();
            if (choice == 0) { // exit
                break;
            }

            switch (choice) {
                case 1:
                    // play game
                    guessGame.playGame();
                    break;
                case 2:
                    // List of all animals
                    printAnimalList(statistics.getAllAnimals());
                    break;
                case 3:
                    // Search for an animal
                    printAnimalFact();
                    break;
                case 4:
                    // Calculate statistics
                    printTreeInfo();
                    break;
                case 5:
                    // Print the Knowledge Tree
                    statistics.printGuessTree().forEach(System.out::println);
                    break;
                default:
                    break;
            }
        }

        printEmptyLine();
        message(GameUtils.sayGoodbye());

        saver.save(guessTree.getRootNode());
    }

    private void printTreeInfo() {
        TreeInfo info = statistics.getTreeInfo();

        message(getProperty("tree.stats.title"));
        printEmptyLine();
        message(MessageFormat.format(getProperty("tree.stats.root"), info.rootNode));
        message(MessageFormat.format(getProperty("tree.stats.nodes"),  info.numberOfNodes));
        message(MessageFormat.format(getProperty("tree.stats.animals"), info.numberOfAnimals));
        message(MessageFormat.format(getProperty("tree.stats.statements"), info.numberOfStatements));
        message(MessageFormat.format(getProperty("tree.stats.height"), info.heightOfTree));
        message(MessageFormat.format(getProperty("tree.stats.minimum"), info.minAnimalDepth));
        message(MessageFormat.format(getProperty("tree.stats.average"), info.avgAnimalDepth));
        printEmptyLine();
    }

    private void printAnimalFact() {
        String name = getAnswer(getProperty("animal.prompt"));
        name = GameUtils.parseAnimalName(name);
        List<String> list = statistics.getAnimalFacts(name);
        if (list.isEmpty()) {
            message(MessageFormat.format(getProperty("tree.search.noFacts"), GameUtils.definiteArticle(name)));
        } else {
            message(MessageFormat.format(getProperty("tree.search.facts"), GameUtils.definiteArticle(name)));
            for (String fact : list) {
                message(String.format(getProperty("tree.search.printf"), fact));
            }
        }
        printEmptyLine();
    }

    private int gameMenu() {
        while (true) {
            message(getProperty("welcome"));
            printEmptyLine();
            message(getProperty("menu.property.title"));
            printEmptyLine();
            message("1. " + getProperty("menu.entry.play"));
            message("2. " + getProperty("menu.entry.list"));
            message("3. " + getProperty("menu.entry.search"));
            message("4. " + getProperty("menu.entry.statistics"));
            message("5. " + getProperty("menu.entry.print"));
            try {
                int res = Integer.parseInt(getAnswer("0. " + getProperty("menu.property.exit")));
                if (res < 0 || res > 5) {
                    throw new Exception();
                }
                return res;
            } catch (Exception e) {
                message(MessageFormat.format(getProperty("menu.property.error"), 5));
            }

        }
    }

    private void printAnimalList(List<String> list) {
        message(getProperty("tree.list.animals"));
        for (String animal : list) {
            message(" - " + animal);
        }
        printEmptyLine();
    }

    public static void message(String string) {
        if (string == null) {
            System.out.println();
        } else {
            System.out.println(string);
        }
    }

    public static void printEmptyLine() {
        message(null);
    }

    public static String getAnswer(String request) {
        message(request);
        return scanner.nextLine();
    }
}

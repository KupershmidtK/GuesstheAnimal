package animals;

import java.util.Random;
import static animals.ResourceLoader.*;

public class GameUtils {
    private static final Random random = new Random();
    private static LanguageRules langRules = new LanguageRulesEn();

    public static void setLangRules(LanguageRules langRules) {
        GameUtils.langRules = langRules;
    }

    public static String sayHallo() {
        try {
            String[] arr = getPropertyArray("greeting");
            return arr[random.nextInt(arr.length)];
        } catch (Exception e) {
            return "Hallo!";
        }
    }

    public static String sayGoodbye() {
        try {
            String[] arr = getPropertyArray("farewell");
            return arr[random.nextInt(arr.length)];
        } catch (Exception e) {
            return "Good bye!";
        }
    }

    public static String sayNice() {
        try {
            String[] arr = getPropertyArray("animal.nice");
            return arr[random.nextInt(arr.length)];
        } catch (Exception e) {
            return "Nice!";
        }
    }

    public static String sayIWin() {
        try {
            String[] arr = getPropertyArray("game.win");
            return arr[random.nextInt(arr.length)];
        } catch (Exception e) {
            return "I win!";
        }
    }

    public static String sayThanks() {
        try {
            String[] arr = getPropertyArray("game.thanks");
            return arr[random.nextInt(arr.length)];
        } catch (Exception e) {
            return "Thank you for playing!";
        }
    }

    public static String askPlayAgain() {
        try {
            String[] arr = getPropertyArray("game.again");
            return arr[random.nextInt(arr.length)];
        } catch (Exception e) {
            return "Want to play again?";
        }
    }

    public static String askYesNoAgain() {
        try {
            String[] arr = getPropertyArray("ask.again");
            return arr[random.nextInt(arr.length)];
        } catch (Exception e) {
            return "Please enter yes or no.";
        }
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////
    public static String parseAnimalName(String name) throws IllegalArgumentException {
        if (name == null) {
            throw new IllegalArgumentException();
        }
        return langRules.validateAnimalName(name);
    }

    public static boolean isPositiveAnswer(String text) {
        return langRules.isPositiveAnswer(text);
    }

    public static boolean isNegativeAnswer(String text) {
        return langRules.isNegativeAnswer(text);
    }

    public static boolean isValidStatement(String text) {
        return langRules.isCorrectStatement(text);
    }

    public static String definiteArticle(String text) {
        try {
            text = langRules.definiteArticle(text);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return text;
    }

    public static String parseStatement(String text) {
        try {
            text = langRules.validateStatement(text);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return text;
    }

    public static String negativeStatement(String text) {
        try {
            text = langRules.makeNegativeStatement(text);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return text;
    }

    public static String makeQuestion(String text) {
        try {
            text = langRules.makeQuestion(text);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return text;
    }

    public static String animalFact(String statement, String animalName) {
        try {
            statement = langRules.animalFact(statement, animalName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return statement;
    }

    public static String capitalize(String text) {
        return text.substring(0, 1).toUpperCase() + text.substring(1);
    }

    public static String guessAnimal(String text) {
        try {
            text = langRules.guessAnimal(text);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return text;
    }
}

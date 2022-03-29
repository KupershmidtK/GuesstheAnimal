package animals;

import java.text.MessageFormat;

import static animals.App.*;
import static animals.GameUtils.*;
import static animals.ResourceLoader.*;

public class GuessGame {
    private final GuessTree guessTree;
    private TreeNode parentNode;
    private TreeNode currentNode;

    public GuessGame(GuessTree guessTree) {
        this.guessTree = guessTree;
    }

    public void askMostLikedAnimal() {
        while (true) {
            try {
                message(getProperty("animal.wantLearn"));
                String animalName = getAnswer(getProperty("animal.askFavorite"));
                animalName = parseAnimalName(animalName);
                guessTree.setRootNode(new TreeNode(animalName));
                break;
            } catch (IllegalArgumentException e) {
//                System.out.println("Wrong animal name!");
            }
        }

        message(sayNice());
        message(getProperty("animal.learnedMuch"));
        printEmptyLine();
    }

    public void playGame() {
        do {
            startGame();
            askQuestions();
        } while (playAgain());
    }

    private void startGame() {
        message(getProperty("game.letsPlay"));
        message(getProperty("game.think"));
        getAnswer(getProperty("game.enter"));
    }

    private void askQuestions() {
        String guessAnimalName;
        boolean positiveBranch = false;

        // guess animal
        parentNode = null;
        currentNode = guessTree.getRootNode();

        while (true) {
            if (!currentNode.isAnimal()) {
                String statement = currentNode.getData();
                String question = capitalize(makeQuestion(statement));
                positiveBranch = isYesAnswer(question);

                parentNode = currentNode;
                currentNode = positiveBranch ?
                        guessTree.nextPositiveNode(currentNode) : guessTree.nextNegativeNode(currentNode);
            } else {
                guessAnimalName = currentNode.getData();
                if (isYesAnswer(capitalize(guessAnimal(guessAnimalName)))) {
                    message(sayIWin());
                    printEmptyLine();
                } else {
                    TreeNode newFactNode = createNewFactAndAnimalNode(guessAnimalName, positiveBranch);
                    learnedFacts(newFactNode);
                }
                return;
            }
        }
    }

    private boolean playAgain() {
        message(sayThanks());
        return isYesAnswer(askPlayAgain());
    }


    private TreeNode createNewFactAndAnimalNode(String guessAnimalName, boolean positiveParentBranch) {
        String newAnimalName = askNewAnimalName();
        String distinguishFact = askDistinguishFact(newAnimalName, guessAnimalName);

        boolean answerYES = isYesAnswer(MessageFormat.format(getProperty("game.isCorrect"), newAnimalName));

        TreeNode newFactNode = new TreeNode(distinguishFact);
        if (parentNode == null) {
            guessTree.setRootNode(newFactNode);
        } else {
            if (positiveParentBranch) {
                parentNode.setYes(newFactNode);
            } else {
                parentNode.setNo(newFactNode);
            }
        }

        TreeNode newAnimalNode = new TreeNode(newAnimalName);

        newFactNode.setYes(answerYES ? newAnimalNode : currentNode);
        newFactNode.setNo(answerYES ? currentNode : newAnimalNode);

        return newFactNode;
    }

    private String askDistinguishFact(String newAnimalName, String guessAnimalName) {
        String distinguishFact;
        while (true) {
            distinguishFact = getAnswer(MessageFormat.format(getProperty("statement.prompt"),
                    guessAnimalName, newAnimalName));

            if (isValidStatement(distinguishFact)) {
                distinguishFact = parseStatement(distinguishFact);
                break;
            } else {
                message(getProperty("statement.error"));
            }
        }
        return distinguishFact;
    }

    private String askNewAnimalName() {
        while (true) {
            try {
                String newAnimalName = getAnswer(getProperty("game.giveUp"));
                newAnimalName = parseAnimalName(newAnimalName);
                return newAnimalName;
            } catch (IllegalArgumentException e) {
                message("Wrong animal name!");
            }
        }
    }

    private void learnedFacts(TreeNode factNode) {
        String statement = factNode.getData();
        String negativeStatement = negativeStatement(statement);

        String rightAnimal = definiteArticle(factNode.getYes().getData());
        String wrongAnimal = definiteArticle(factNode.getNo().getData());

        message(getProperty("game.learned"));
        message(String.format(" - %s.", capitalize(animalFact(statement, rightAnimal))));
        message(String.format(" - %s.", capitalize(animalFact(negativeStatement, wrongAnimal))));
    }

    private boolean isYesAnswer(String request) {
        String answer = getAnswer(request);
        while (true) {
            if (!isNegativeAnswer(answer) && !isPositiveAnswer(answer)) {
                answer = getAnswer(askYesNoAgain());
            } else {
                return isPositiveAnswer(answer);
            }
        }
    }
}

package animals;

public interface LanguageRules {
    boolean isPositiveAnswer(String text);

    boolean isNegativeAnswer(String text);

    boolean isCorrectStatement(String text);

    String validateAnimalName(String text);

    String definiteArticle(String text) throws Exception;

    String validateStatement(String text) throws Exception;

    String makeNegativeStatement(String text) throws Exception;

    String makeQuestion(String text) throws Exception;

    String animalFact(String statement, String animal) throws Exception;

    String guessAnimal(String text) throws Exception;
}

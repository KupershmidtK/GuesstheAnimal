package animals;

import static animals.ResourceLoader.*;

public class LanguageRulesEn extends LanguageRulesBase {

    @Override
    public boolean isCorrectStatement(String text) { // language specific
        return pt.get("statement.isCorrect").matcher(text).matches();
    }

    @Override
    public String validateAnimalName(String text) { // language specific
        text = text.toLowerCase().replaceFirst("^(an?|the)\\s+", "");
        return text.matches("^[aeiou].+") ? "an " + text : "a " + text;
    }

    @Override
    public String definiteArticle(String text) throws Exception { // language specific
        text = pt.get("definite.1.pattern").matcher(text).replaceFirst(getPattern("definite.1.replace"));
        return text;
    }

    @Override
    public String validateStatement(String text) throws Exception { // language specific
        text = pt.get("statement.1.pattern").matcher(text).replaceFirst(getPattern("statement.1.replace"));
        text = pt.get("statement.2.pattern").matcher(text).replaceFirst(getPattern("statement.2.replace"));
        text = pt.get("statement.3.pattern").matcher(text).replaceFirst(getPattern("statement.3.replace"));
        return text;
    }

    @Override
    public String makeNegativeStatement(String text) throws Exception { // language specific
        text = pt.get("negative.1.pattern").matcher(text).replaceFirst(getPattern("negative.1.replace"));
        text = pt.get("negative.2.pattern").matcher(text).replaceFirst(getPattern("negative.2.replace"));
        text = pt.get("negative.3.pattern").matcher(text).replaceFirst(getPattern("negative.3.replace"));
        return text;
    }

    @Override
    public String makeQuestion(String text) throws Exception { // language specific
        text = pt.get("question.1.pattern").matcher(text).replaceFirst(getPattern("question.1.replace"));
        text = pt.get("question.2.pattern").matcher(text).replaceFirst(getPattern("question.2.replace"));
        return text;
    }

    @Override
    public String animalFact(String statement, String animal) throws Exception { // language specific
        statement = pt.get("animalFact.1.pattern").matcher(statement)
                .replaceFirst(String.format(getPattern("animalFact.1.replace"), animal));
        return statement;
    }

    @Override
    public String guessAnimal(String text) throws Exception {
        text = pt.get("guessAnimal.1.pattern").matcher(text).replaceFirst(getPattern("guessAnimal.1.replace"));
        return text;
    }
}

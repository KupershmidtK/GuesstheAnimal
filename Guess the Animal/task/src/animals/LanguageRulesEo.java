package animals;

import static animals.ResourceLoader.getPattern;

public class LanguageRulesEo extends LanguageRulesBase {

    @Override
    public String validateAnimalName(String text) { // language specific
        return text;
    }

    @Override
    public String definiteArticle(String text) throws Exception { // language specific
        text = pt.get("definite.1.pattern").matcher(text).replaceFirst(getPattern("definite.1.replace"));
        return text;
    }

    @Override
    public String validateStatement(String text) throws Exception { // language specific
        text = pt.get("statement.1.pattern").matcher(text).replaceFirst(getPattern("statement.1.replace"));
        return text;
    }

    @Override
    public String makeNegativeStatement(String text) throws Exception { // language specific
        text = pt.get("negative.1.pattern").matcher(text).replaceFirst(getPattern("negative.1.replace"));
        return text;
    }

    @Override
    public String makeQuestion(String text) throws Exception { // language specific
        text = pt.get("question.1.pattern").matcher(text).replaceFirst(getPattern("question.1.replace"));
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

package animals;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import static animals.ResourceLoader.getPattern;
import static animals.ResourceLoader.getPatternKeys;

public abstract class LanguageRulesBase implements LanguageRules {
    Map<String, Pattern> pt = new HashMap<>();

    public LanguageRulesBase() {
        loadPatterns();
    }

    protected void loadPatterns() {
        getPatternKeys()
                .forEach(key -> {
                    try {
                        pt.put(key, Pattern.compile(getPattern(key), Pattern.CASE_INSENSITIVE));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
    }

    @Override
    public boolean isPositiveAnswer(String text) {
        return pt.get("positiveAnswer.isCorrect").matcher(text).matches();
    }

    @Override
    public boolean isNegativeAnswer(String text) {
        return pt.get("negativeAnswer.isCorrect").matcher(text).matches();
    }

    @Override
    public boolean isCorrectStatement(String text) { // language specific
        return pt.get("statement.isCorrect").matcher(text).matches();
    }
}

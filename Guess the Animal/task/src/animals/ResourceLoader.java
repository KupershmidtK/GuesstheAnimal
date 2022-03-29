package animals;

import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Set;

public class ResourceLoader {
    private final static ResourceBundle rbMessages = ResourceBundle.getBundle("messages", Locale.getDefault());
    private final static ResourceBundle rbPatterns = ResourceBundle.getBundle("patterns", Locale.getDefault());

    public static String getProperty(String name) {
        return rbMessages.getString(name);
    }

    public static String[] getPropertyArray(String name) {
        return getProperty(name).split("\f");
    }

    public static String getPattern(String name) {
        return rbPatterns.getString(name);
    }

    public static Set<String> getPatternKeys() {
        return rbPatterns.keySet();
    }
}

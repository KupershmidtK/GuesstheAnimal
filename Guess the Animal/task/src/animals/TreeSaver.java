package animals;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class TreeSaver {

    private final ObjectMapper objectMapper;
    private final String fileName;

    public TreeSaver(String fileName, String format) {
        if (fileName == null || fileName.isBlank()) {
            fileName = "animals";
        }

        List<String> formats = List.of("json", "xml", "yaml");
        if (format == null || !formats.contains(format)) {
            format = "json";
        }

        // todo change Locale.getDefault().getLanguage()
        String language = Locale.getDefault().getLanguage();
        switch (language) {
            case "eo":
                language = "_" + language;
                break;
            default:
                language = "";
                break;
        }

        this.fileName = fileName
                + language
                + "." + format;

        switch (format) {
            case "xml":
                objectMapper = new XmlMapper();
                break;
            case "yaml":
                objectMapper = new YAMLMapper();
                break;
            case "json":
            default:
                objectMapper = new JsonMapper();
                break;
        }
    }

    public void save(TreeNode root) {
        try {
            objectMapper
                    .writerWithDefaultPrettyPrinter()
                    .writeValue(new File(fileName), root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public TreeNode load() {
        try {
            return objectMapper.readValue(new File(fileName), TreeNode.class);
        } catch (IOException e) {
            return null;
        }
    }
}

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by Mykyta Sirobaba on 13.11.2025.
 * email mykyta.sirobaba@gmail.com
 */
public class TextAnalyzerService {

    public AnalysisReesult analyzeText(String text) {
        if (text == null || text.isBlank()) {
            return new AnalysisReesult(0, 0, 0, 0,"-", 0.0);
        }

        long lines = text.lines().count();

        List<String> words = Arrays.stream(text.split("\\s+"))
                .filter(w -> !w.isBlank())
                .toList();

        long wordsCount = words.size();
        long charsWithSpaces = text.length();
        long charsNoSpaces = text.replaceAll("\\s+", "").length();

        String mostCommon = words.stream()
                .collect(Collectors.groupingBy(String::toLowerCase, Collectors.counting()))
                .entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse("-");

        double avgWordLength = words.stream()
                .mapToInt(String::length)
                .average()
                .orElse(0.0);

        return new AnalysisReesult(lines, wordsCount, charsWithSpaces, charsNoSpaces, mostCommon, avgWordLength);
    }
}

import java.io.Serializable;
import java.text.DecimalFormat;

/**
 * Created by Mykyta Sirobaba on 13.11.2025.
 * email mykyta.sirobaba@gmail.com
 */
public class AnalysisReesult implements Serializable {
    private final long lines;
    private final long words;
    private final long charsWithSpaces;
    private final long charsWithoutSpaces;
    private final String mostCommonWord;
    private final double avgWordLength;

    public AnalysisReesult(long lines, long words, long charsWithSpaces, long charsWithoutSpaces, String mostCommonWord, double avgWordLength) {
        this.lines = lines;
        this.words = words;
        this.charsWithSpaces = charsWithSpaces;
        this.charsWithoutSpaces = charsWithoutSpaces;
        this.mostCommonWord = mostCommonWord;
        this.avgWordLength = avgWordLength;
    }

    public long getLines() {
        return lines;
    }

    public long getWords() {
        return words;
    }

    public long getCharsWithSpaces() {
        return charsWithSpaces;
    }

    public long getCharsWithoutSpaces() {
        return charsWithoutSpaces;
    }

    public String getMostCommonWord() {
        return mostCommonWord;
    }

    public double getAvgWordLength() {
        return avgWordLength;
    }

    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("#0.00");
        return String.format(
                "Lines: %d%nWords: %d%nChars(with spaces):  %d%nChars(no spaces): %d%nMost common: %s%nAvg word length: %s",
                lines, words, charsWithSpaces, charsWithoutSpaces, mostCommonWord, df.format(getAvgWordLength())
        );
    }

    public String toConsoleString() {
        return "\n=== Text Report ===\n" + this.toString();
    }
}

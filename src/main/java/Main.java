import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by Mykyta Sirobaba on 13.11.2025.
 * email mykyta.sirobaba@gmail.com
 */
public class Main {
    public static void main(String[] args) {
        Path inputPath = (args.length > 0) ? Paths.get(args[0]) : Paths.get("src/main/java/data/input.txt");
        LogService logService = new LogService(Paths.get("src/main/java/logs/app.log"));
        FileService fs = new FileService(logService);
        TextAnalyzerService analyzerService = new TextAnalyzerService();


        try {
            logService.log("Starting to analyze text files..." + inputPath);
            String content = fs.readAllLines(inputPath);
            AnalysisReesult reesult = analyzerService.analyzeText(content);

            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
            Path outTxt = Paths.get("results/result_" + timestamp + ".txt");
            Path outSer = Paths.get("results/result_" + timestamp + ".ser");

            fs.saveTextResult(outTxt, reesult.toConsoleString());
            fs.saveTextResult(outSer, reesult.toString());

            logService.log("Finished analyzing text files..." + inputPath);
            System.out.println(reesult.toConsoleString());
        } catch (FileProcessingException e) {
            logService.log("Error reading file: " + e.getMessage());
            System.err.println("Error reading file: " + e.getMessage());
        }
    }
}

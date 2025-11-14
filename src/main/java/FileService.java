import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.stream.Collectors;

/**
 * Created by Mykyta Sirobaba on 13.11.2025.
 * email mykyta.sirobaba@gmail.com
 */
public class FileService {
    private final LogService logService;

    public FileService(LogService logService) {
        this.logService = logService;
    }

    public String readAllLines(Path inputPath) {
        try {
            if (Files.notExists(inputPath)) {
                logService.log("File not found: " + inputPath);
                Files.createDirectories(inputPath.getParent() != null ? inputPath.getParent() : Path.of("."));
                Files.writeString(inputPath, "", StandardOpenOption.APPEND);
                throw new FileProcessingException("File not found: " + inputPath);
            }
            String content = Files.lines(inputPath).collect(Collectors.joining(System.lineSeparator()));
            logService.log("File: " + inputPath);
            return content;
        } catch (IOException e) {
            throw new FileProcessingException("Error reading file: " + inputPath, e);
        }
    }

    public void saveTextResult(Path outTxt, String readable) {
        try {
            Files.createDirectories(outTxt.getParent());
            Files.writeString(outTxt, readable, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
            logService.log("File: " + outTxt);
        } catch (IOException e) {
            throw new FileProcessingException("Error saving file: " + outTxt, e);
        }
    }

    public void saveSerializedResult(Path outSerialized, AnalysisReesult result) {
        try {
            Files.createDirectories(outSerialized.getParent());
            try (var os = Files.newOutputStream(outSerialized, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
                 ObjectOutputStream oos = new ObjectOutputStream(os)){
                oos.writeObject(result);
            }
            logService.log("File: " + outSerialized);
        } catch (IOException e) {
            throw new FileProcessingException("Error saving file: " + outSerialized, e);
        }
    }
}

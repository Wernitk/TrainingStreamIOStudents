import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by Mykyta Sirobaba on 13.11.2025.
 * email mykyta.sirobaba@gmail.com
 */
public class LogService {
    private final Path logFile;
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public LogService(Path logFile) {
        this.logFile = logFile;

        try {
            Files.createDirectories(logFile.getParent());
            if (Files.notExists(logFile)) {
                Files.createFile(logFile);
            }
        } catch (IOException e) {
            throw new RuntimeException("Could not create log file " + logFile, e);
        }
    }

    public synchronized void log(String message) {
        String entry = String.format("[%s] %s%n", LocalDateTime.now().format(dateTimeFormatter), message);
        try {
            Files.writeString(logFile, entry, StandardOpenOption.APPEND);
        } catch (IOException e) {
            throw new RuntimeException("Could not create log file " + logFile, e);
        }
    }
}

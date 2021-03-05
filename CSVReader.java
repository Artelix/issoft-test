import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class CSVReader {
    private Path path;

    public CSVReader(String filePath) {
        this.path = Paths.get(filePath);
    }

    public String[] readCSVFile() {
        try {
            List<String> lines = Files.readAllLines(path);
            if (lines != null) {
                lines.remove(0);
                String [] output = lines.toArray(new String[lines.size()]);
                return lines.toArray(new String[lines.size()]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}

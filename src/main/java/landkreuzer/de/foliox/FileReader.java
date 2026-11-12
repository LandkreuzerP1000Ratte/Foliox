package landkreuzer.de.foliox;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public final class FileReader {

    private FileReader() {}

    public static ArrayList<String> readAll(File filepath) {
        List<String[]> rows = new ArrayList<>();


        try (var reader = Files.newBufferedReader(filePath.toPath(), StandardCharsets.UTF_8)) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.isBlank()) {
                    continue;
                }
                String[] cells = line.split("\\|", -1);
                for (int i = 0; i < cells.length; i++) {
                    cells[i] = cells[i].trim();
                }
                rows.add(cells);
            }
        } catch (IOException e) {
            System.out.println("Datei konnte nicht gelesen werden: " + e.getMessage());
            e.printStackTrace();
            return new String[0][0];
        }

    }



}

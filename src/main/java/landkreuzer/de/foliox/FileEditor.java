package landkreuzer.de.foliox;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public final class FileEditor {

    private FileEditor() {}

    public static List<String[]> readAll(File filePath) {
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
            return new ArrayList<>();
        }

        return rows;
    }

    public static List<String> readSingleLine(File filePath, int lineIndex) throws IOException {
        List<String> row = new ArrayList<>();

        try (var reader = Files.newBufferedReader(filePath.toPath(), StandardCharsets.UTF_8)) {
            String line;
            int current = 0;
            while ((line = reader.readLine()) != null) {
                if (current == lineIndex) {
                    for (String cell : line.split("\\|", -1)) {
                        row.add(cell.trim());
                    }
                    break;
                }
                current++;
            }
        } catch (IOException e) {
            System.out.println("Datei konnte nicht gelesen werden: " + e.getMessage());
            e.printStackTrace();
            return new ArrayList<>();
        }

        return row;
    }

    public static void writeNewLineToFile(File filePath, Media line) {

    }
}
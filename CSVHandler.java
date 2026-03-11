package fashiony;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CSVHandler {
    private static final String COMMA_DELIMITER = ",";
    private static final String NEW_LINE_SEPARATOR = "\n";
    private static final String FILE_HEADER = "id,createdAt,author,tags,notes,filepaths";
    public static ArrayList<Vesion> read(String filepath){
        ArrayList<Vesion> data = new ArrayList<>();
        String cvsSplitBy = ",";
        data.clear();
        try (BufferedReader br = new BufferedReader(new FileReader(filepath))) {
            // Skip header
            String line = br.readLine();
            while ((line = br.readLine()) != null) {
                // Use comma as separator of rows
                String[] data_temp = line.split(cvsSplitBy);
                int id = Integer.parseInt(data_temp[0]);
                long createdAt = Long.parseLong(data_temp[1]);
                String author= data_temp[2];
                List<String> tags = Arrays.asList(data_temp[3].split("\\|"));
                String notes = data_temp[4];
                List<String> filepaths = Arrays.asList(data_temp[5].split("\\|"));

                data.add(new Vesion(id, author, createdAt, tags, notes, filepaths));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }
    public static void write(String filepath, List<Vesion> versions){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filepath))) {
            // Adds header to start with
            writer.append(FILE_HEADER);
            writer.append(NEW_LINE_SEPARATOR);

            // Goes through record rows to add
            for (Vesion version : versions) {
                String[] row = version.toCSVRow();
                // Loop to go through row's data
                for (int i = 0; i < row.length; i++) {
                    String value = row[i];
                    writer.append(value);
                    
                    // Makes sure that last column doesn't get comma ending
                    if (i < row.length - 1) {
                        writer.append(COMMA_DELIMITER);
                    }
                }
                writer.append(NEW_LINE_SEPARATOR);
            }
            // Success message
            System.out.println("CSV file was created successfully!");

        } catch (IOException e) {
            System.err.println("Error writing CSV file: " + e.getMessage());
        }
    }
}

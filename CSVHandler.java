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

    private final String COMMA_DELIMITER = ",";
    private final String NEW_LINE_SEPARATOR = "\n";
    private final String FILE_HEADER = "id,createdAt,lastChanged,author,tags,notes,filepaths,commited";
    
    // Read and write methods implemented from our class and optimized using GenAI
    public ArrayList<Version> read(String filepath){ 
        ArrayList<Version> data = new ArrayList<>();
        String cvsSplitBy = ",";
        data.clear();
        try (BufferedReader br = new BufferedReader(new FileReader(filepath))) {
            // Skip header
            String line = br.readLine();
            while ((line = br.readLine()) != null) {
                // Use comma as separator of rows
                String[] data_temp = line.split(cvsSplitBy);
                int id = Integer.parseInt(data_temp[0]);
                String createdAt = data_temp[1];
                String lastChanged = data_temp[2];
                String author= data_temp[3];
                ArrayList<String> tags = new ArrayList<>(Arrays.asList(data_temp[4].split("\\|")));
                String notes = data_temp[5];
                List<String> filepaths = Arrays.asList(data_temp[6].split("\\|"));
                boolean commited = data_temp[7].equals("yes") ? true : false;

                data.add(new Version(id, author, createdAt,lastChanged, tags, notes, filepaths,commited));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch(ArrayIndexOutOfBoundsException e){
            System.out.println("Missing Data");
        }
        return data;
    }
   
    public void write(String filepath, List<Version> versions){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filepath))) {
            // Adds header to start with
            writer.append(FILE_HEADER);
            writer.append(NEW_LINE_SEPARATOR);

            // Goes through record rows to add
            for (Version version : versions) {
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

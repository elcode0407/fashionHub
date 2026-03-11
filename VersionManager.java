package fashiony;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class VersionManager {
    public static void main(String[] args) {
        // List<Version> versionsSorted = new ArrayList<>();
        // vesions.add(new Version(1, "lala", Arrays.asList(new String[]{"skibid"}), "omg hahah",  Arrays.asList(new String[]{"data.csv"})));
        // vesions.add(new Version(2, "lala", Arrays.asList(new String[]{"skid"}), "omg hahah",  Arrays.asList(new String[]{"data.csv"})));
        // vesions.add(new Version(3, "lala", Arrays.asList(new String[]{"skibid","jest"}), "mg hahah",  Arrays.asList(new String[]{"data.csv"})));
        // CSVHandler.write("fashiony/ver1.csv", vesions);
        // for (Version v : CSVHandler.read("fashiony/ver1.csv")) {
        //     System.out.println(v.toString());
        //     versionsSorted.add(v);
        // }
        
        // MergeSort.mergeSort(versionsSorted, 0, versionsSorted.size()-1);

        // for (Version version : versionsSorted) {
        //     System.out.println(version.toString());
        // }

        // System.out.println(Searcher.binarySearchDates(versionsSorted, "2026-03-11: 15:08").toString());

    }
    public static LocalDateTime getDate(Version ver){
        DateTimeFormatter formatter =
        DateTimeFormatter.ofPattern("yyyy-MM-dd: HH:mm");
        if(ver.lastChanged == null || "".equals(ver.lastChanged)){
            System.out.println("Empty, or wrong date format!!!!!!!!!!!!!!!!!!!1");
            return LocalDateTime.parse("0000-00-00: 00:00", formatter);
        }
        try {
            return LocalDateTime.parse(ver.lastChanged, formatter);
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format: " + ver.lastChanged);
            return null;
        }
    }
}

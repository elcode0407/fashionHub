package fashiony;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

public class Searcher {
    public static Version binarySearchDates(List<Version> vers, String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd: HH:mm");
        if(date == null || "".equals(date)){
            System.out.println("Empty, or wrong date format!!!!!!!!!!!!!!!!!!!1");
            return null;
        }
        try {
            LocalDateTime formatedDate = LocalDateTime.parse(date, formatter);
            int low = 0, high = vers.size() - 1;
            while (low <= high) {
                int mid = low + (high - low) / 2;
                LocalDateTime midDate = VersionManager.getDate(vers.get(mid));
                if (midDate.isEqual(formatedDate))
                    return vers.get(mid);
                if (midDate.compareTo(formatedDate)<0)
                    low = mid + 1;

                else
                    high = mid - 1;
                }
            return null;
            
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format: " + date);
            return null;
        }
        
    }
}

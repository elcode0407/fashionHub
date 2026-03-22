package fashiony;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

public class Searcher {
    //Using binary search implemented from our lectures to find versions by date, which will recieve merge sorted list of versions
    // and was adjusted to compare LocalDateTime
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
    //Using binary search implemented from our lectures to find versions by ID, assuming Version ID's stored in sorted order.
    public static Version binarySearchID(List<Version> vers, int id){
        if(id < 0){
            System.out.println("Empty, or wrong id !!!!!!!!!!!!!!!!!!!");
            return null;
        }
            int low = 0, high = vers.size() - 1;
            while (low <= high) {
                int mid = low + (high - low) / 2;
                int midId = vers.get(mid).id;
                if (midId == id)
                    return vers.get(mid);
                if (midId < id)
                    low = mid + 1;
                else
                    high = mid - 1;
                }
        return null;
    }
}

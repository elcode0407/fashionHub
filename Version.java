package fashiony;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Version {
    int id;
    String createdAt;
    String lastChanged;
    String author;
    List<String> tags;
    String notes;
    List<String> filepaths;

    public Version(int id, String author, String createdAt, String lastChanged,List<String> tags, String notes,List<String> filepaths){
        this.id = id;
        this.createdAt = createdAt;
        this.lastChanged = lastChanged;
        this.author = author;
        this.tags = tags;
        this.notes = notes;
        this.filepaths = filepaths;
    }
    public Version(int id, String author,List<String> tags, String notes,List<String> filepaths){
        this.id = id;
        LocalDateTime now = LocalDateTime.now();

        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("yyyy-MM-dd: HH:mm");

        String formatted = now.format(formatter);
        this.createdAt = formatted;
        this.lastChanged = formatted;
        this.author = author;
        this.tags = tags;
        this.notes = notes;
        this.filepaths = filepaths;
    }

    
    public String[] toCSVRow(){
        return new String[]{""+id,""+createdAt,lastChanged,author,String.join("|", tags),notes,String.join("|", filepaths)};
    }
    @Override
    public String toString() {
        return "ID: "+ id + ", "
        +"Created At: "+ createdAt + ", "
        +"Last Changed: "+ lastChanged + ", "
        +"Author: "+ author + ", "
        +"Tags: "+ tags + ", "
        +"Notes: "+ notes + ", "
        +"File Paths: "+ filepaths + ", ";
    }
    
}

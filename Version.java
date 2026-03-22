package fashiony;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

// Class Version is used to store data of versions
public class Version extends BaseEntity{
    String createdAt;
    String lastChanged;
    String author;
    List<String> tags;
    String notes;
    List<String> filepaths;
    boolean commited;

    
    //Using two constructors in cases of creating completely new version or reading CSV file with existing versions.
    public Version(int id, String author, String createdAt, String lastChanged,List<String> tags, String notes,List<String> filepaths, boolean commited){
        this.id = id;
        this.createdAt = createdAt;
        this.lastChanged = lastChanged;
        this.author = author;
        this.tags = tags;
        this.notes = notes;
        this.filepaths = filepaths;
        this.commited = commited;
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
        this.commited=false;
    }

    //Returns String array with all the data in String fromat
    public String[] toCSVRow(){
        return new String[]{""+id,""+createdAt,lastChanged,author,String.join("|", tags),notes,String.join("|", filepaths),(commited ? "yes" : "no")};
    }

    public void addTag(String tag) {
        if (!tags.contains(tag)) {
            tags.add(tag);
            LocalDateTime now = LocalDateTime.now();

            DateTimeFormatter formatter =
                    DateTimeFormatter.ofPattern("yyyy-MM-dd: HH:mm");

            String formatted = now.format(formatter);
            this.lastChanged = formatted;
        }

    }

    public void deleteTag(String tag) {
        if (tags.contains(tag)) {
            tags.remove(tag);
            LocalDateTime now = LocalDateTime.now();

            DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("yyyy-MM-dd: HH:mm");

            String formatted = now.format(formatter);
            this.lastChanged = formatted;
        }else{
            System.out.println("There is no tag "+tag+" in this version.");
        }
    }

    @Override
    public String toString() {
        return "ID: "+ id + ",\n "
        +"Created At: "+ createdAt + ",\n "
        +"Last Changed: "+ lastChanged + ",\n "
        +"Author: "+ author + ",\n "
        +"Tags: "+ tags + ",\n "
        +"Notes: "+ notes + ",\n "
        +"File Paths: "+ filepaths + ", ";
    }
    
}

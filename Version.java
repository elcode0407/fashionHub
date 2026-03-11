package fashiony;

import java.util.List;

public class Version {
    int id;
    long createdAt;
    String author;
    List<String> tags;
    String notes;
    List<String> filepaths;

    public Version(int id, String author, long createdAt,List<String> tags, String notes,List<String> filepaths){
        this.id = id;
        this.createdAt = createdAt;
        this.author = author;
        this.tags = tags;
        this.notes = notes;
        this.filepaths = filepaths;
    }
    public Version(int id, String author,List<String> tags, String notes,List<String> filepaths){
        this.id = id;
        this.createdAt = System.currentTimeMillis();
        this.author = author;
        this.tags = tags;
        this.notes = notes;
        this.filepaths = filepaths;
    }

    public String[] toCSVRow(){
        return new String[]{""+id,""+createdAt,author,String.join("|", tags),notes,String.join("|", filepaths)};
    }
    @Override
    public String toString() {
        return "ID: "+ id + ", "
        +"Created At: "+ createdAt + ", "
        +"Author: "+ author + ", "
        +"Tags: "+ tags + ", "
        +"Notes: "+ notes + ", "
        +"File Paths: "+ filepaths + ", ";
    }
    
}

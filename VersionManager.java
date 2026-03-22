package fashiony;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class VersionManager {
    ArrayList<Version> history;
    TagIndex tagIndex;
    Stack<Operation> undoStack;
    CSVHandler csv;
    String csvPath;
    String author;

    // Sets up the manager and loads version history from the CSV file.
    public VersionManager(String csvPath){
        this.history = new ArrayList<>();
        this.tagIndex = new TagIndex(history);
        this.undoStack = new Stack<>();
        this.csv = new CSVHandler();
        this.csvPath = csvPath;
        author = null;
        loadHistory();
    }
   
    // Stores the current logged-in author name.
    public void login(String author){
        this.author = author;
    }
    
    // Changes a version note and records the action for undo.
    public boolean addChangeNote(int id, String note) {
            for (int i = 0; i < history.size(); i++) {
                if (history.get(i).id == id) {
                    undoStack.push(new Operation(OpType.EDIT_NOTE,history.get(i), history.get(i).notes));
                    changeNote(note, id, history.get(i));
                    history.get(i).commited = false;
                    return true;
                }
            }
            return false;
    }

    // Adds a new tag to a version and records the action for undo.
    public boolean addNewTag(String tag, int ID){
        for (int i = 0; i < history.size(); i++) {
            if (history.get(i).id == ID) {
                undoStack.push(new Operation(OpType.ADD_TAG,history.get(i), tag));
                history.get(i).addTag(tag);
                history.get(i).commited = false;
                return true;
            }
        }
        return false;
    }

    // Removes a tag from a version and records the action for undo.
    public void deleteTag(String tag, int ID){   
        Version ver = Searcher.binarySearchID(history, ID);
        if(ver !=null){
            undoStack.push(new Operation(OpType.REMOVE_TAG, ver, tag));
            ver.deleteTag(tag);
            ver.commited = false;
            tagIndex.build(history);
        }
    }

    // Creates a new version in memory and records the action for undo.
    public void createNewVersion(List<String> tags, String notes, List<String> filepaths){
        Version ver = new Version(getNextId(), author, tags, notes, filepaths);
        tempSave(ver);
        undoStack.push(new Operation(OpType.TEMP_ADD,ver));
    }

    // Saves all uncommitted changes to the CSV file.
    public void commitSaved(){
        boolean hasChanges = false;

        for (Version v : history) {
            if (!v.commited) {
                v.commited=true;
                hasChanges = true;
            }
        }

        if (hasChanges) {
            saveHistory();
            System.out.println("Changes committed to CSV.");
        } else {
            System.out.println("No new changes to commit.");
        }
    }
   
    // Deletes a version from history and records its position for undo.
    public void deleteFromHistory(int id){
    for (int i = 0; i < history.size(); i++) {
        if (history.get(i).id == id) {
            undoStack.push(new Operation(OpType.DELETE_VERSION, history.get(i),i));
            history.remove(i);
            tagIndex.build(history);
            break;
        }
    }
}

    // Updates the note and last changed date of a version.
    public void changeNote(String changeNote,int id, Version v){
        if(changeNote != null && !changeNote.contains(",")){
             v.notes = changeNote;
             LocalDateTime now = LocalDateTime.now();

            DateTimeFormatter formatter =
                    DateTimeFormatter.ofPattern("yyyy-MM-dd: HH:mm");

            String formatted = now.format(formatter);
            v.lastChanged = formatted;
    }

    }

    // Prints all versions grouped into uncommitted and committed sections.
    public void printVersions() {
        System.out.println("Uncommitted:");
        for (Version v : history) {
            if (!v.commited) {
                System.out.println(v.toString());
            }
        }
        System.out.println("Committed:");
        for (Version v : history) {
            if (v.commited) {
                System.out.println(v.toString());
            }
        }
    }
    
    // Returns the next available version ID.
    public int getNextId() {
        int maxId = 0;
    
        for (Version v : history) {
            if (v.id > maxId) {
                maxId = v.id;
            }
        }
    
        return maxId + 1;
    }
    
    // Loads version history from the CSV file into memory.
    public void loadHistory(){
        history.clear();
        history = csv.read(csvPath);
        tagIndex.build(history);
    }
    
    // Writes the current version history to the CSV file.
    public void saveHistory(){
        csv.write(csvPath, history);
        tagIndex.build(history);
    }

    // Temporarily stores a new version in memory.
    public void tempSave(Version v){
        history.add(v);
    }

    // Returns a date-sorted copy of the version history.
    public List<Version> sortHistoryByDate(){
        List<Version> vers = new ArrayList<>(history);
        MergeSort.mergeSort(vers, 0, vers.size()-1);
        return vers;
    }

    // Finds a version by date using binary search on sorted history.
    public Version findByDate(String date){
        return Searcher.binarySearchDates(sortHistoryByDate(), date);
    }

    // Finds all versions that contain a given tag.
    public List<Version> findByTag(String tag){
        return tagIndex.find(tag);
    }
    
    // Undoes the most recent change stored in the stack, create with help of GenAI and my basic code.
    public boolean undo() {
        if (undoStack.isEmpty()) {
            return false;
        }
    
        Operation op = undoStack.pop();
    
        switch (op.type) {
            case TEMP_ADD:
                for (int i = 0; i < history.size(); i++) {
                    if (history.get(i).id == op.version.id) {
                        history.remove(i);
                        break;
                    }
                }
                break;
    
            case ADD_TAG:
                op.version.deleteTag(op.tag);
                op.version.commited = false;
                break;
    
            case REMOVE_TAG:
                op.version.addTag(op.tag);
                op.version.commited = false;
                break;
    
            case EDIT_NOTE:
                op.version.notes = op.oldNote;
                op.version.commited = false;
                break;
    
            case DELETE_VERSION:
                history.add(op.index,op.version);
                op.version.commited = false;
                break;
    
            default:
                return false;
        }
    
        tagIndex.build(history);
        return true;
    }

    // Converts a version's lastChanged string into a LocalDateTime object generated by GenAI.
    public static LocalDateTime getDate(Version ver){
        DateTimeFormatter formatter =
            DateTimeFormatter.ofPattern("yyyy-MM-dd: HH:mm");
    
        if(ver.lastChanged == null || "".equals(ver.lastChanged)){
            return LocalDateTime.MIN;
        }
    
        try {
            return LocalDateTime.parse(ver.lastChanged, formatter);
        } catch (DateTimeParseException e) {
            return LocalDateTime.MIN;
        }
    }
}
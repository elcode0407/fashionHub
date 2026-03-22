package fashiony;

import java.util.ArrayList;
import java.util.List;

//TagIndex is class that uses HashMap to hold tags --> List of versions, in order to search and access quickly.
public class TagIndex {
    private HashMap<String, ArrayList<Version>> tagToVers = new HashMap<>(10);

    public TagIndex(ArrayList<Version> vers){
        build(vers);
    }

    // Build function recieves list of version to build tag --> vers list, data set with sarting capacity 10
    public void build(List<Version> vers){
        tagToVers = new HashMap<>(10);
    
        for (Version version : vers) {
            for (String tag : version.tags) {
               if(!"".equals(tag)){ ArrayList<Version> ver = tagToVers.get(tag);
                if(ver == null){
                    ver = new ArrayList<>();
                    tagToVers.put(tag, ver);
                }
                ver.add(version);}
            }
        }
    }

    public HashMap<String, ArrayList<Version>> getMap(){
        return tagToVers;
    }

    public List<Version> find(String tag){
        if(tagToVers.get(tag) != null){
            return tagToVers.get(tag);
        }
        System.out.println("Couldn't find anything");
        return null;
    }

}

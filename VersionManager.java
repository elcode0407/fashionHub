package fashiony;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class VersionManager {
    public static void main(String[] args) {
        for (Version v : CSVHandler.read("data.csv")) {
            System.out.println(v.toString());
        }
        List<Version> vesions = new ArrayList<>();
        vesions.add(new Version(1, "lala", Arrays.asList(new String[]{"skibid"}), "omg hahah",  Arrays.asList(new String[]{"data.csv"})));
        vesions.add(new Version(2, "lala", Arrays.asList(new String[]{"skid"}), "omg hahah",  Arrays.asList(new String[]{"data.csv"})));
        vesions.add(new Version(3, "lala", Arrays.asList(new String[]{"skibid","jest"}), "mg hahah",  Arrays.asList(new String[]{"data.csv"})));
        CSVHandler.write("ver1.csv", vesions);
        for (Version v : CSVHandler.read("ver1.csv")) {
            System.out.println(v.toString());
        }
    }
}

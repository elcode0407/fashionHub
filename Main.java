package fashiony;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        //Creating instance of VersionManager and passing desired path.
        VersionManager vm = new VersionManager("fashiony/ver2.csv");

        //Welcome message
        Scanner sc = new Scanner(System.in);
        System.out.println("███████╗ █████╗ ███████╗██╗  ██╗██╗ ██████╗ ███╗   ██╗");
        System.out.println("██╔════╝██╔══██╗██╔════╝██║  ██║██║██╔═══██╗████╗  ██║");
        System.out.println("█████╗  ███████║███████╗███████║██║██║   ██║██╔██╗ ██║");
        System.out.println("██╔══╝  ██╔══██║╚════██║██╔══██║██║██║   ██║██║╚██╗██║");
        System.out.println("██║     ██║  ██║███████║██║  ██║██║╚██████╔╝██║ ╚████║");
        System.out.println("╚═╝     ╚═╝  ╚═╝╚══════╝╚═╝  ╚═╝╚═╝ ╚═════╝ ╚═╝  ╚═══╝");
        System.out.println("========================================");
        System.out.println("        FASHION VERSION MANAGER SYSTEM");
        System.out.println("========================================");
        System.out.println(" Welcome! Manage your garment versions");
        System.out.println(" with history tracking and tag, date search.");
        System.out.println();

        //Using variables to keep track of user status
        boolean isLogedIn = false;
        boolean isLogedOut = false;

        //Using while loop to keep input open while user have not loged out.
        while(!isLogedOut){
            //Checking if user had loged in already to set current Author.
            if(!isLogedIn){
                System.out.println(" Available Actions:");
                System.out.println(" [1] Login");
                String action = sc.nextLine();
                if(action.equals("1")){
                    System.out.println("Input your name:");
                    String author = sc.nextLine();
                    vm.login(author);
                    System.out.println(" Logged in as: " + vm.author);
                    isLogedIn = true;
                }
            }else{
                //Actions avaible for user to use.
                System.out.println("\n Available Actions:");
                System.out.println(" [1] Print Versions");
                System.out.println(" [2] Create New Version");
                System.out.println(" [3] Add / Edit Note");
                System.out.println(" [4] Add Tag");
                System.out.println(" [5] Delete Tag");
                System.out.println(" [6] Delete Version from Uncommited");
                System.out.println(" [7] Search by Tag");
                System.out.println(" [8] Search by Date");
                System.out.println(" [9] Commit Changes");
                System.out.println(" [10] Undo Last Action");
                System.out.println(" [0] Exit");

                //Using switch case for easier categorizing of user input and computed code.
                String action = sc.nextLine();
                switch (action) {
                    //Case one access print method for our versions in ArrayList history
                    case "1":
                        vm.printVersions();
                        break;
                    //Case two creates new version by asking user for default varibles: tags, filepaths, notes.
                    // Author and other fields are prefilled.
                    case "2":
                        System.out.println("Please input data for your new Version");
                        System.out.print("Enter tags for your current version(separated by |): ");
                        String tagInput = sc.nextLine();
                        while(tagInput.contains(",")){
                            System.out.println("Tag cannot contain any ','!");
                            tagInput = sc.nextLine();
                        }
                        List<String> tags = new ArrayList<>(Arrays.asList(tagInput.split("\\|")));
                        System.out.print("Enter any desired notes: ");
                        String note = sc.nextLine();
                        while(note.contains(",")){
                            System.out.println("Note cannot contain any ','!");
                            note = sc.nextLine();
                        }
                        System.out.print("Enter file paths for your designs(separated by |): ");
                        String fileInput = sc.nextLine();
                        while(fileInput.contains(",")){
                            System.out.println("Note cannot contain any ','!");
                            fileInput = sc.nextLine();
                        }
                        List<String> filepaths = new ArrayList<>(Arrays.asList(fileInput.split("\\|")));
                        vm.createNewVersion(tags, note, filepaths);
                        System.out.println("Yessss you just created a new DESIGN GARMENT VERSION");
                        break;
                    //Case three gives access to desired version by ID if existes, and then asks to input desired note to chnage to.
                    case "3":
                        System.out.println("Which Version do you want to use? (Input only number)");
                        int id = sc.nextInt();
                        sc.nextLine();

                        Version ver = Searcher.binarySearchID(vm.history, id);

                        while (ver == null) {
                            System.out.println("There is no note with such id, please enter again:");
                            id = sc.nextInt();
                            sc.nextLine();
                            ver = Searcher.binarySearchID(vm.history, id);
                        }
                        System.out.println("Current note:");
                        System.out.println(ver.notes);

                        System.out.println("Enter updated note (you can modify it):");
                        String edited = sc.nextLine();
                        vm.addChangeNote(id, edited);
                        break;
                    //Case four gives access to desired verison by ID if existes, and asks for one tag that can't include "|", or if already added,
                    // to add on to version selected.
                    case "4":
                        System.out.println("Which Version do you want to use? (Input only number)");
                        int idTag = sc.nextInt();
                        sc.nextLine();

                        Version verTag = Searcher.binarySearchID(vm.history, idTag);

                        while (verTag == null) {
                            System.out.println("There is no note with such id, please enter again:");
                            idTag = sc.nextInt();
                            sc.nextLine();
                            verTag = Searcher.binarySearchID(vm.history, idTag);
                        }
                        System.out.println("Write down tag to add(only 1):");
                        String tag = sc.nextLine();
                        while(tag.contains("|") || verTag.tags.contains(tag)) {
                            System.out.println("Tag should not contain '|' symbol or tag already exists!");
                            tag = sc.nextLine();
                        }
                        vm.addNewTag(tag, idTag);
                        break;
                    //Case five gives access to desired verison by ID if existes, and prints out list of existing tags of verison
                    //to select for deletion.
                    case "5":
                        System.out.println("Which Version do you want to use? (Input only number)");
                        int idDTag = sc.nextInt();
                        sc.nextLine();

                        Version verDTag = Searcher.binarySearchID(vm.history, idDTag);

                        while (verDTag == null) {
                            System.out.println("There is no note with such id, please enter again:");
                            idDTag = sc.nextInt();
                            sc.nextLine();
                            verDTag = Searcher.binarySearchID(vm.history, idDTag);
                        }
                        System.out.println("Write down tag to delete from list:");
                        for (String tagg : verDTag.tags) {
                            System.out.print(tagg+", ");
                        }
                        System.out.println("\n");
                        String tagD = sc.nextLine();
                        while(tagD.contains("|") || !verDTag.tags.contains(tagD)) {
                            System.out.println("Tag should not contain '|' symbol or tag does not exist!");
                            tagD = sc.nextLine();
                        }
                        vm.deleteTag(tagD, idDTag);
                        break;
                    //Case six gives access to desired verison by ID if existes and not commited yet, 
                    // and then deletes it from ArrayList history.
                    case "6":
                        System.out.println("Which Version do you want to delete? (Input only number)");
                        int idDV = sc.nextInt();
                        sc.nextLine();

                        Version verDV = Searcher.binarySearchID(vm.history, idDV);

                        while (verDV == null || verDV.commited) {
                            System.out.println("There is no note with such id or it is commited, please enter again:");
                            idDV = sc.nextInt();
                            sc.nextLine();
                            verDV = Searcher.binarySearchID(vm.history, idDV);
                        }
                        vm.deleteFromHistory(idDV);
                        break;
                    //Case seven prints out list of version that is associated with entred tag by using hash key search.O(n)
                    case "7":
                        System.out.println("Please enter tag you want to search for:");
                        String tagF = sc.nextLine();
                        while(tagF.contains("|")) {
                            System.out.println("Tag should not contain '|' symbol!");
                            tagF = sc.nextLine();
                        }
                        List<Version> vers = vm.findByTag(tagF);
                        if(vers !=null){for (Version version : vers) {
                            System.out.println(version.toString());
                        }}
                        break;
                    // Case eight search using binary search and sorts dates first using merge sort to search for desired date
                    // and prints out version that was first ecnoutred with desired date
                    case "8":
                        System.out.println("Please enter last changed date you want to search for in this format yyyy-MM-dd: HH:mm:");
                        String date = sc.nextLine();
                        Version versD = vm.findByDate(date);

                        while (versD == null) {
                            System.out.println("No version found. Try again:");
                            date = sc.nextLine();
                            versD = vm.findByDate(date);
                        }

                        System.out.println(versD.toString());
                        break;
                    // Case nine commits version to CSV file, which only holds commited final versions that are not removable,
                    // if there is non commited version in ArrayList history.
                    case "9":
                        vm.commitSaved();
                        break;
                    //Case 10 uses Stack that keeps track of Operations performed to undo if wanted.
                    case "10":
                        vm.undo();
                        break;
                    //Case zero logs user out and ends session.
                    case "0":
                        System.out.println("Thanks for using FASHIONYYYY)))");
                        System.out.println("You are now logged out!");
                        isLogedOut = true;
                        break;
                    // case"-1":
                    //     System.out.println(vm.undoStack);
                    //     break;
                    default:
                        System.out.println("Wrong Input");
                }
            }
        }

        
    }
}

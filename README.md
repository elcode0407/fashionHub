# fashionHub
FashionHub is a replication of GitHUB but for fashion designers.

FASHION VERSION MANAGER SYSTEM

==============================



Project overview

----------------

This is a Java console application for tracking garment design versions.

It supports:

- logging in as an author

- viewing saved versions

- creating a new version

- editing notes

- adding and deleting tags

- deleting uncommitted versions

- searching by tag

- searching by date

- committing saved versions to CSV

- undoing the last action



Project structure

-----------------

The project uses the Java package name:

fashiony



Important files:

- Main.java entry point

- VersionManager.java main application logic

- CSVHandler.java CSV read/write logic

- test_versions_10k.csv data file used at runtime



Requirements

------------

- Java JDK 8 or newer

- Command line / terminal



How to set up

-------------

1. Unzip the project.

2. Open a terminal.

3. Go to the folder that contains the "fashiony" directory.



Example:

cd path/to/project-parent-folder



Important:

Run compile and execute commands from the parent folder of "fashiony".

Do not run them from inside the "fashiony" folder, because the program

uses package "fashiony" and loads the CSV file with this relative path:

fashiony/test_versions_10k.csv



How to compile

--------------

From the parent folder of "fashiony", run:



javac fashiony/*.java



If compilation succeeds, no output is usually shown.



How to run

----------

From the same parent folder, run:



java fashiony.Main



Program flow

------------

When the program starts:

1. Choose [1] Login

2. Enter your name

3. Use the numbered menu options

4. Choose [0] Exit when finished



Main menu options after login

-----------------------------

[1] Print Versions

[2] Create New Version

[3] Add / Edit Note

[4] Add Tag

[5] Delete Tag

[6] Delete Version from Uncommited

[7] Search by Tag

[8] Search by Date

[9] Commit Changes

[10] Undo Last Action

[0] Exit



How to test the project

-----------------------

Basic manual test:

1. Compile the project:



javac fashiony/*.java



2. Run the app:



java fashiony.Main



3. Perform this quick test sequence:

- Login with any name

- Print versions

- Search by tag

- Search by date

- Create a new version

- Undo the last action

- Exit





Notes

-----

- The application is interactive and reads input continuously from the console.

- If input is cut off early during redirected testing, Java may throw

NoSuchElementException because Scanner expects more lines.
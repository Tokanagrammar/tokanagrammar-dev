tokanagrammar- Development Branch.
=============

This is the development branch of `tokanagrammar`. For stable, well tested code, see `tokanagrammar/tokanagrammar`

This is a jigsaw-like puzzle game, except each piece is token from a source file, and the 'complete picture' is the program.

`tokanagrammar/docs/*` contains source files that could be used for the puzzles

Set up Development Environment
=============

0) Install Maven (3.0.4 or later)

1) You want to make sure you have `jdk 1.7.0_u14` (or later, the current version (as of March, 22, 2013) is `jdk 1.7.0_u17`)

2) Install `JavaFx`

- Copy `C:\Program Files\Java\jre7\lib\jfxrt.jar` to your `C:\Users\Your_User\jfxrt.jar`
- Then `cd C:\Users\Your_User\`
- And execute this command 

`mvn install:install-file -Dfile=jfxrt.jar -DgroupId=com.oracle -DartifactId=javafx -Dpackaging=jar -Dversion=2.2.7`

**Note** `2.2.7` should be replaced with whatever version your `C:\Program Files\Java\jre7\lib\javafx.properties` says the version is.

CHEATSHEET
=============

This is a `maven` project, and  build phase requires every source file to have a header.
0) Java Requirement
The project uses `javafx`, hence you want to make sure you have `jdk.1.7.0-u14` (or higher)

1) To automatically prepend a header:

`mvn license:format`

2) To check for missing headers:

`mvn license:check`

3) To build runnable jar:

`mvn clean package`

4) To run all unit tests

`mvn clean test`

5) To build binary tar.gz package (containing executable)

`./build_binaries.sh`

6) To build binary zip package (on Windows)
  *Note* This script requires <a href="http://www.7-zip.org">`7z`</a> to be already installed.

`build_binaries.cmd`

7) To build Apple OSX executable app

`./build_binaries.sh macosx`
  
  
  
Directory Structures
====================
Please follow the conventions and put things in the right place.
See <a href="https://github.com/Tokanagrammar/tokanagrammar-dev/blob/master/README.txt">this</a>



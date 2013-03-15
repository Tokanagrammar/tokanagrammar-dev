tokanagrammar-dev Release 0.1.1

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

5) To build b tar.gz package (containing executable)

`./build_binaries.sh`

6) To build Apple OSX executable app

`./build_binaries.sh macosx`
  
  
  
Directory Structures
====================
Please follow the conventions and put things in the right place.
See <a href="https://github.com/Tokanagrammar/tokanagrammar-dev/blob/master/README.txt">this</a>



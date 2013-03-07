tokanagrammar- Development Branch.
=============

This is the development branch of `tokanagrammar`. For stable, well tested code, see `tokanagrammar/tokanagrammar`

This is a jigsaw-like puzzle game, except each piece is token from a source file, and the 'complete picture' is the program.

`tokanagrammar/docs/*` contains source files that could be used for the puzzles

CHEATSHEET
=============

This is a `maven` project, and  build phase requires every source file to have a header.

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
(`TODO: more to come`)

./tokanagrammar-dev

Folder PATH listing
Volume serial number is E297-6759
./tokanagrammar-dev
│
├───docs             ==> contain source files (to be used as puzzles)
├───lib              ==> Deprecated! (Will be removed soon)
├───macosx           ==> Small Ant project, needed to build OSX's app
├───src            
│   ├───etc
│   ├───main
│   │   ├───java
│   │   │   └───edu
│   │   │       └───umb
│   │   │           └───cs
│   │   │               ├───api
│   │   │               ├───gui
│   │   │               ├───parser
│   │   │               └───source
│   │   │                   └───std
│   │   ├───javacc
│   │   └───resources
│   │       ├───fxml
│   │       ├───images
│   │       │   └───ui
│   │       │       ├───buttons
│   │       │       └───tokens
│   │       └───styles
│   └───test
│       ├───java
│       │   └───edu
│       │       └───umb
│       │           └───cs
│       │               └───source
│       └───resources
│           └───sources
└───target             

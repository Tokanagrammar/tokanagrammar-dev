tokanagrammar
=============

This is a jigsaw-like puzzle game, except each piece is token from a source file, and the 'complete picture' is the program.

`tokanagrammar/docs/*` contains source files that could be used for the puzzles
=============
CHEATSHEET
=============

This is a `maven` project, and the build phase requires every source files to have a header.

1) To automatically prepend a header:

`mvn license:format`

2) To check for missing headers:

`mvn license:check`

3) To build runnable jar:

`mvn clean package`

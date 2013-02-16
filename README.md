tokanagrammar
=============

This is a jigsaw-like puzzle game, except each piece is token from a source file, and the 'complete picture' is the program.

=============
CHEATSHEET
=============

This is a `maven` project, and  build phase requires every source file to have a header.

1) To automatically prepend a header:

`mvn license:format`

2) To check for missing headers:

`mvn license:check`


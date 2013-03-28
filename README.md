Tokanagrammar-Dev Release 0.5 
=============

Set up Development Environment
=============

0) Install Maven (3.0.4 or later)
(See instructions on their site)

1) You want to make sure you have `jdk 1.7.0_u14` (or later, the current version (as of March, 22, 2013) is `jdk 1.7.0_u17`)

2) Install `JavaFx`

- Copy `C:\Program Files\Java\jre7\lib\jfxrt.jar` to your `C:\Users\Your_User\jfxrt.jar`
- Then `cd C:\Users\Your_User\`
- And execute this command 

`mvn install:install-file -Dfile=jfxrt.jar -DgroupId=com.oracle -DartifactId=javafx -Dpackaging=jar -Dversion=2.2.7`

**Note #1** `2.2.7` should be replaced with whatever your `C:\Program Files\Java\jre7\lib\javafx.properties` says the version is.

**Note #2** If you are on a Unix/Linux environment, copy the jar to `~/` (ie., your `home` directory)

This step is sort of a 'workaround' to some bug. Once Oracle fixes this (hopefully in Java8, which is released this summer), we should be able to skip this, because, as they promised, `JavaFX` will be included natively in `Java` (just like `Swing` or other built-in APIs)

CHEATSHEET
=============

This is a `maven` project, and  build phase requires every source file to have a header.

0) Be sure  you get the right version of `Java` (and `Maven`)

- Run `mvn -version` to check `Java` and `Maven` version.
- If the versions are not correct, follow instrutions in *Set up Development Environment* section.

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



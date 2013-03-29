@ECHO OFF

REM Copyright (C) 2013 Tokanagrammar Team
REM 
REM This is a jigsaw-like puzzle game,
REM except each piece is token from a source file,
REM and the 'complete picture' is the program.
REM 
REM This program is free software: you can redistribute it and/or modify
REM it under the terms of the GNU General Public License as published by
REM the Free Software Foundation, either version 3 of the License, or
REM any later version.
REM 
REM This program is distributed in the hope that it will be useful,
REM but WITHOUT ANY WARRANTY; without even the implied warranty of
REM MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
REM GNU General Public License for more details.
REM 
REM You should have received a copy of the GNU General Public License
REM along with this program.  If not, see <http://www.gnu.org/licenses/>.

REM author Vy Thao Nguyen
REM Usage: ./build_binaries.cmd
REM This will build a distribuatble zip package on windows
REM Requirements: 7z has to be installed

SETLOCAL

REM get the version
call cmd.exe /c  "mvn org.apache.maven.plugins:maven-help-plugin:2.1.1:evaluate -Dexpression=project.version"  | grep --extended-regexp "^[0-9.]" > tempFile.txt
set /p version=<tempFile.txt
del tempFile.txt

REM  run all available tests and build the distributable jar  
call mvn clean package 

REM prepare directory for packaging
MD target\tokanagrammar

REM get the jar to the dir, and rename to tokanagrammar-<version>.jar
COPY target\tokanagrammar*-with-dependencies.jar target\tokanagrammar\tokanagrammar-%version%.jar

REM get license.txt
COPY LICENSE.txt target\tokanagrammar\.

REM get puzzles
MD target\tokanagrammar\puzzles
COPY puzzles\* target\tokanagrammar\puzzles\.

REM zip them up!
call 7z a -tzip target\tokanagrammar.zip target\tokanagrammar

REM push  distributable file to website
IF DEFINED DRY_RUN GOTO EOF
call git clone git@github.com:Tokanagrammar/tokanagrammar.github.com.git
cd tokanagrammar.github.com
call git checkout -b %version%
cd ..
COPY target\tokanagrammar.zip tokanagrammar.github.com\downloads\.
cd tokanagrammar.github.com
call git add .
call git commit -am "upload downloadable for %version%"
call git push origin %version%

:EOF
echo ALL SET
ENDLOCAL


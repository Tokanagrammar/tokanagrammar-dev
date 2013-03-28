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

SET ABS_PATH=%~dp0
SET OUT_REL_PATH=target\tokanagrammar
SET OUT_DIR="%ABS_PATH%%OUT_REL_PATH%"

call mvn -Dmaven.test.skip clean package 

REM get jar 
MD target\tokanagrammar
REM workaround here! cannot rename while copying. That will corrupt the file
COPY target\tokanagrammar*-with-dependencies.jar target\tokanagrammar\.
REN target\tokanagrammar\*.jar tokanagrammar.jar

REM get license.txt
COPY LICENSE.txt target\tokanagrammar\.

REM get puzzles
MD target\tokanagrammar\puzzles
COPY puzzles target\tokanagrammar\puzzles\.

REM zip them up!
call 7z a -tzip target\tokanagrammar.zip %OUT_DIR%

REM push  distributable file to website
call git clone git@github.com:Tokanagrammar/tokanagrammar.github.com.git
cd tokanagrammar.github.com
call git checkout -b new_version
cd ..
COPY target\tokanagrammar.zip tokanagrammar.github.com\downloads\.
cd tokanagrammar.github.com
call git add .
call git commit -am "add upload downloadable"
call git push origin new_version

echo ALL SET
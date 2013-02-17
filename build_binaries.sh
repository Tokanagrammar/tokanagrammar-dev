#!/bin/bash -ex
# Copyright (C) 2013 Tokanagrammar Team
# 
# This is a jigsaw-like puzzle game,
# except each piece is token from a source file,
# and the 'complete picture' is the program.
# 
# This program is free software: you can redistribute it and/or modify
# it under the terms of the GNU General Public License as published by
# the Free Software Foundation, either version 3 of the License, or
# any later version.
# 
# This program is distributed in the hope that it will be useful,
# but WITHOUT ANY WARRANTY; without even the implied warranty of
# MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
# GNU General Public License for more details.
# 
# You should have received a copy of the GNU General Public License
# along with this program.  If not, see <http://www.gnu.org/licenses/>.

# if target is macosx
#      build an OSX app (.app) file
#
# else 
#      build tar.gz package


usage="Usage: ./build_biaries.sh [macosx]"
if [ $# -eq 1 ]; then
    platform=$1
elif [ $# -eq 0 ]; then
        platform="unix"
else
    echo "$usage"
    exit 1;
fi;

license="LICENSE.txt"
version=$(mvn org.apache.maven.plugins:maven-help-plugin:2.1.1:evaluate -Dexpression=project.version | grep -o '^[0-9.]\+'  | tail -n 1)
project_name=$(mvn org.apache.maven.plugins:maven-help-plugin:2.1.1:evaluate -Dexpression=project.name | grep '^[a-z.]\+')
bin_name="${project_name}-${version}"

# get runnable jar
mvn clean package
mkdir -p target/packages
mkdir -p target/packages/${platform}
cp -p target/${project_name}*jar-with-dependencies.jar target/packages/${platform}/${bin_name}.jar

# get license
cp ${license} target/packages/${platform}/LICENSE.txt

# get the puzzle files
cp -r docs target/packages/${platform}/.

# timestamp
#epoch=`date +%s`

# TODO: sign the jar file?

# platform-specific packaging task
if [ "${platform}" = "unix" ]; then
    pushd target/packages
    tar -pvczf ${bin_name}.tar.gz ${platform}
    popd
elif [ ${platform} == "macosx" ]; then
    pushd macosx
    outdir="target/packages/${platform}"
    ant -Dapp.name=${project_name} -Dapp.version=${version} -Dapp.jar=target/packages/${platform}/${bin_name}.jar -Dapp.outdir=${outdir}
    popd
    cp -r docs ${outdir}/*.app/Contents/Resources/.
else
    echo "Unknown platform: ${platform}"
    echo "$usage"
    exit 2
fi;

echo "DONE. See target/packages for packages"


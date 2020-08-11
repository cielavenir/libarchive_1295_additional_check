#!/bin/sh
dir=$(dirname $0)
java -classpath "$dir/commons-compress-1.20.jar:$dir/xz-1.8.jar" $dir/Main.java "$@"

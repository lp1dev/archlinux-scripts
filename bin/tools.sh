#!/bin/bash

function usage() {
    echo "usage : " $0 "[add|list|pull|clone]";
    exit 0
}

function list() {
    cat .toolsrc 2> /dev/null
}

function add() {
    repositories=`cat ./*/.git/config | grep url | cut -c 8-`;
    echo -e "$repositories" > .toolsrc
    echo -e "$repositories\ntoolsrc file created"
}

function pull() {
    find . -maxdepth 1 -type d -print -execdir git --git-dir={}/.git --work-tree=$PWD/{} pull origin master \;
}

function clone() {
    xargs git clone < .toolsrc
}

if [ "$#" -lt 1 ];
then
    usage
fi;
if [ "$1" == "list" ];
then
    list
fi;
if [ "$1" == "add" ];
then
   add
fi
if [ "$1" == "pull" ];
then
   pull
fi
if [ "$1" == "clone" ];
then clone
fi

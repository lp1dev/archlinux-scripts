#!/bin/sh
# shell script to prepend i3status with more stuff

i3status -c ~/archlinux-scripts/i3status.config | while :
do
        read line
        echo "`todo` | $line" || exit 1
done

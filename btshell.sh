#!/bin/sh

MAC="B8:27:EB:2F:FE:48"
rfcomm connect /dev/rfcomm0 $MAC 1 &
sleep 2
screen /dev/rfcomm0 115200

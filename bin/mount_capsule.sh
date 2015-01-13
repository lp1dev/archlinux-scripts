#!/bin/sh
sudo mount.cifs //192.168.1.93/Disk /media/TimeCapsule -o user=lupin,passwd=$1,sec=ntlm

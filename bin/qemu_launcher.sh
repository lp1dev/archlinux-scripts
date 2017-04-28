#!/usr/bin/bash

ram=2048

if [ $# -lt "2" ]
then
   echo "usage : ./launch.sh [install|vmware|spice] file.img"
   exit
fi

if [ $1 == "install" ]
then
    if [ $# != "3" ]
    then
	echo $# "\n"
	echo "usage : ./launch.sh [install|vmware|spice] file.img file.iso"
    else
	qemu-system-x86_64 -machine pc,accel=kvm:tcg -cpu host -m $ram -smp 2 -hda $2 -drive file=$3,media=cdrom -boot d
    fi
elif [ $1 == "vmware" ]
then
    qemu-system-x86_64 -machine pc,accel=kvm:tcg -net nic -net user -vga vmware -cpu host -m $ram -smp 8 -hda $2 -drive media=cdrom -boot d
elif [ $1 == "spice" ]
then
    qemu-system-x86_64 -machine pc,accel=kvm:tcg -vga qxl -cpu host -m $ram -smp 8 -hda $2 -drive media=cdrom -boot d -spice port=5900,addr=0.0.0.0,disable-ticketing
else
    echo "unknown parameter :" $1
fi

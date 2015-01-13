#!/usr/bin/bash
if [ $# != "1" ]
then
   echo "usage : ./launch.sh [install|vmware|spice]"
   exit
fi

if [ $1 == "install" ]
then
    qemu-system-x86_64 -machine pc,accel=kvm:tcg -cpu host -m 4096 -smp 2 -hda w81-disk -drive file=W81.iso,media=cdrom -boot d
elif [ $1 == "vmware" ]
then
    qemu-system-x86_64 -machine pc,accel=kvm:tcg -vga vmware -cpu host -m 4096 -smp 8 -hda w81-disk -drive media=cdrom -boot d
elif [ $1 == "spice" ]
then
    qemu-system-x86_64 -machine pc,accel=kvm:tcg -vga qxl -cpu host -m 4096 -smp 8 -hda w81-disk -drive media=cdrom -boot d -spice port=5900,addr=0.0.0.0,disable-ticketing
else
    echo "unknown parameter :" $1
fi

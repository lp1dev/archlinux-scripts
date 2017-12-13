#!/bin/sh
#from https://bbs.archlinux.org/viewtopic.php?id=213841
rfkill block bluetooh
rfkill unblock bluetooth
sudo systemctl restart bluetooth

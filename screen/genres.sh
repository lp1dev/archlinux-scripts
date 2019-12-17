#!/bin/sh
#DISPLAY="XWAYLAND1"
res=`cvt 2560 1080 60 | tail -n 1`
toremove='Modeline'
res=${res//$toremove}
toremove='"2560x1080_60.00"'
res=${res//$toremove}
echo $res
modeName="2560x1080"
#xrandr --delmode $modeName
xrandr --newmode $modeName $res
#xrandr --addmode $DISPLAY $modeName
#xrandr --output $DISPLAY --mode $modeName

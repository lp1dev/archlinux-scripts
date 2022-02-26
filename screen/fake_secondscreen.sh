MODE=1920x1080
OUTPUT=VIRTUAL1

xrandr --addmode $OUTPUT $MODE 
xrandr --output $OUTPUT --mode $MODE --left-of eDP1
tigervncserver -display :0 -localhost no
x11vnc -display :1 -clip xinerama0 -usepw -xrandr -forever -nonc -noxdamage -repeat

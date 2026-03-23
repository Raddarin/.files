#!/bin/bash

# Vi hämtar parametern
VAL=$1

# I Bash använder vi [ ] för tester och $ framför variabelnamnet
if [ "$VAL" = "1" ]; then
  hyprctl hyprpaper wallpaper ",/home/Raddarin/Pictures/wallpaper/ygdrasill.jpg"

elif [ "$VAL" = "2" ]; then
  hyprctl hyprpaper wallpaper ",/home/Raddarin/Pictures/wallpaper/Red_Blosom.jpg"

elif [ "$VAL" = "3" ]; then
  hyprctl hyprpaper wallpaper ",/home/Raddarin/Pictures/wallpaper/wp5712386-amoled-pc-wallpapers.png"
elif [ "$VAL" = "4" ]; then
  hyprctl hyprpaper wallpaper ",/home/Raddarin/Pictures/wallpaper/gargantua-black-3840x2160-9621.jpg"
elif [ "$VAL" = "5" ]; then
  hyprctl hyprpaper wallpaper ",/home/Raddarin/Pictures/wallpaper/gargantua-endurance-3840x2160-25445.jpg"
elif [ "$VAL" = "6" ]; then
  hyprctl hyprpaper wallpaper ",/home/Raddarin/Pictures/wallpaper/romantic-night-sky-5120x2880-25549.jpg"

else
  echo "Användning: $0 1 eller 2"
fi

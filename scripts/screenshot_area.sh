#!/usr/bin/env bash

mkdir -p "$HOME/Pictures/Screenshot"
FILE="$HOME/Pictures/Screenshot/screenshot-$(date +%Y-%m-%d_%H-%M-%S).png"

# Välj område, spara till fil
grim -g "$(slurp)" "$FILE"

# Lägg alltid i clipboard (om filen inte finns blir det bara ett fel)
wl-copy <"$FILE"

#!/bin/bash
for arg in "$@"; do chmod "u+x" "$arg"; echo "gave perms to" "$arg"; done;

#!/bin/bash
# Parameter Expansion
if [[ "$#" -eq 1 ]] ; then
touch "$1"{0..9}".dat"
exit 1 ;
fi
touch "xpto0"{0..9}".dat"

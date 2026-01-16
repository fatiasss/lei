#!/bin/bash
# Exit status
if [[ "$#" -eq 0 ]]; then
	echo "need a file"
	exit 1;
fi

for arg in "$@";do
	file "$arg"
	echo "$?"
done

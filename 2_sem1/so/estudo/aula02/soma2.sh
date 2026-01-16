#!/bin/bash
if [[ "$#" -ne 2 ]] ;then
	echo "wrong arg number";
	exit 1;
fi
echo $(( $1 + $2 ))

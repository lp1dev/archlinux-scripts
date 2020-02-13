#!/usr/bin/env bash

if [ "$#" -lt 1 ]; then
    echo "Missing parameter : filename(s)"
    exit 1
fi
for ip in `grep -hoP "\d+\.\d+\.\d+\.\d+" $* | sort -h | uniq`;do
    geoip=`geoiplookup $ip`
    if ! echo $geoip | grep "can't resolve";then
	echo "$ip,$geoip" >> geoips.csv;
    fi
done

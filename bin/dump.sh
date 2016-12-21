#!/bin/sh
# $1 -- function name; rest -- object files
fn=$1; shift 1
exec objdump -d "$@" | 
awk " /^[[:xdigit:]].*<$fn>/,/^\$/ { print \$0 }" |
awk -F: -F' '  'NR==1 {  offset=strtonum("0x"$1); print $0; } 
                NR!=1 {  split($0,a,":"); rhs=a[2]; n=strtonum("0x"$1); $1=sprintf("%x", n-offset); printf "%4s:%s\n", $1,rhs }'

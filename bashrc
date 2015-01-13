###USERDATA (AKA X0STUFF):
##Based on : https://gist.github.com/xtrm0/9749886 Thanks xtrm0 :)
 
 
###COLORS###
 
BLACK='\e[0;30m'
BLUE='\e[0;34m'
GREEN='\e[0;32m'
CYAN='\e[0;36m'
RED='\e[0;31m'
PURPLE='\e[0;35m'
BROWN='\e[0;33m'
LIGHTGRAY='\e[0;37m'
DARKGRAY='\e[1;30m'
LIGHTBLUE='\e[1;34m'
LIGHTGREEN='\e[1;32m'
LIGHTCYAN='\e[1;36m'
LIGHTRED='\e[1;31m'
MAGENTA='\e[1;35m'
YELLOW='\e[1;33m'
LIGHTYELLOW='\e[0;33m'
WHITE='\e[1;37m'
NC='\e[0m' # No Color
 
 
###STUFF###
 
alias emacs="emacs -nw"
alias ls="ls --color=auto"

export HISTFILESIZE=20000
export PATH="/usr/local/sbin:/usr/local/bin:/usr/bin:/usr/lib/jvm/default/bin:/usr/bin/site_perl:/usr/bin/vendor_perl:/usr/bin/core_perl:/home/lupin/archlinux-scripts/bin"
export HISTSIZE=10000
shopt -s histappend
shopt -s cmdhist
HISTCONTROL=ignoreboth
export HISTIGNORE="&:ls:[bf]g:exit"
 
alias ..="cd .."
alias ...="cd ../.."
alias ....="cd ../../.."
alias .....="cd ../../../.."
alias sl="ls"
alias ll="ls -l"
alias la="ls -la"
alias qemu="qemu-launcher"
 
###WELCOME SCREEN###
echo -ne "Hello, ${LIGHTBLUE}$USER${NC}. today is, "; date
echo -ne "${BLUE}Sysinfo:";uptime ;
#echo -ne "${NC}"; cal ;
echo -ne "${NC}";
 
 
function exitstatus {
EXITSTATUS="$?"
PS1="\[\e[0;36m\][\t] \[\e[1;33m\]\u\["
if (( "$EXITSTATUS" == "130" )); then
PS1=$PS1"${MAGENTA}"
elif (("$EXITSTATUS" == "1")) || (( ("$EXITSTATUS" >= "128") && ("$EXITSTATUS" <= "255") )); then
PS1=$PS1"${RED}"
elif (("$EXITSTATUS" == "2")) || (("$EXITSTATUS" == "126")) || (("$EXITSTATUS" == "127")); then
PS1=$PS1"${BLUE}"
elif (("$EXITSTATUS" == "0")); then
PS1=$PS1"${NC}"
else
PS1=$PS1"${CYAN}"e
fi
PS1=$PS1"\]@\[${NC}\]\h:\[\e[0;36m\]\w\[\e[0m\]\$ "
}
 
PROMPT_COMMAND=exitstatus

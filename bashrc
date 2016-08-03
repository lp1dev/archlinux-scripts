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
export PATH="/bin:/usr/local/sbin:/usr/local/bin:/usr/bin:/usr/lib/jvm/default/bin:/usr/bin/site_perl:/usr/bin/vendor_perl:/usr/bin/core_perl:/home/$USER/archlinux-scripts/bin:/home/$USER/.jdk/bin"
export HISTSIZE=10000
shopt -s histappend
shopt -s cmdhist
HISTCONTROL=ignoreboth
export HISTIGNORE="&:ls:[bf]g:exit"

alias eip="ssh exploit@163.5.84.143"
alias lp1="ssh lupin@lp1.eu"
alias lp2="ssh lupin@2.lp1.eu"
alias ..="cd .."
alias ...="cd ../.."
alias ....="cd ../../.."
alias .....="cd ../../../.."
alias sl="ls"
alias ll="ls -l"
alias la="ls -la"
alias qemu="qemu-launcher"
 
###WELCOME SCREEN###
#echo -ne "Hello, ${LIGHTBLUE}$USER${NC}. today is, "; date
#echo -ne "${BLUE}Sysinfo:";uptime ;
#echo -ne "${NC}"; cal ;
#echo -ne "${NC}";
 
 
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
PS1=$PS1"\]@\[${NC}\]\h\[\e[0;36m\]\[\e[0m\] \e[32;33m\w\n\e[0m-> "
}
 
PROMPT_COMMAND=exitstatus

sshfunc(){
	ssh exploit@$1
}

monitor_function(){
	sudo ifconfig $1 down
	sudo iwconfig $1 mode monitor
}

alias idea.sh="LD_PRELOAD=/lib/libglib-2.0.so idea.sh" ## Momentary patch for idea.sh crash with glib2 2.46
alias weblog="emacs scp://lupin@weblog.sh/file.md"
alias xflux="xflux -l 53.349805 -g -6.26031"
alias redis='python < echo "r = redis.StrictRedis(host='2.lp1.eu', port=6379, db=0)"'
alias rails='~/.gem/ruby/2.3.0/gems/railties-4.2.5.1/bin/rails'
export GOPATH="/home/$USER/.gopath/"
alias mon=monitor_function
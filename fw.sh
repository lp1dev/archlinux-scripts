ETH=enx34298f76c60b
WIFI=wlp2s0
TUN=tun0

ufw --force reset
ufw disable

ufw default deny incoming
ufw default deny outgoing

ufw allow out on $ETH to any port 3389,5995,8443 proto tcp
ufw allow out on $WIFI to any port 3389,5995,8443 proto tcp

ufw allow out on $ETH to any port 53 proto udp
ufw allow out on $WIFI to any port 53 proto udp

ufw allow out on $TUN to any port 443,80 proto tcp
ufw allow out on $TUN to any port 53 proto udp

sudo ufw allow out proto tcp to 192.168.1.0/24 port 22,44
sudo ufw allow out proto tcp to 10.0.1.0/24 port 22,44

ufw enable
ufw status verbose

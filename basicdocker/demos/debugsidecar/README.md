# Fancy Debuggen

1. `docker compose up`
2. `docker compose exec ub1 /bin/bash` start een terminal op de eerste Ubuntu container
3. `docker compose exec troubleshoot /bin/bash` start een terminal op de troubleshoot container
4. Op de troubleshoot container `tcpdump -i eth0 -s 1024 -w /root/trace.pcap`
5. Op de Ubuntu container `apt update -y && apt install curl`
6. Op je host: `docker compose exec troubleshoot tail -n +0 -f /root/trace.pcap | & 'C:\Program Files\Wireshark\Wireshark.exe' -k -i -`
7. Op de Ubuntu container `curl http://http.badssl.com` (of een andere http site, SSL zit niet in deze demo)
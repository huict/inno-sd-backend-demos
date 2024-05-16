# Failover

Docker-compose up 
(misschien start keepalived niet omdat de config file geen 644 permissie heeft, dat is ongetest en git-op-windows gedoe, anders even chmodden)

Vervolgens kun je op de s1/s2 met ip addr zien welke live is.
Met de client kun je pingen

En als je er dan 1 uitzet neemt de ander het floating ip adres over.

Oftewel, ip adressen zijn vloeiend

# Multi-network

Een korte demo van meerdere networks.
Op UB2 staat ip forwarding standaard aan (`/proc/sys/net/ipv4/ip_forward`).

Dus om UB2 als router tussen 192.168.42.0/24 en 192.168.44.0/24 te gebruiken moet je routes toevoegen.

Als je dat doet, dan kunnen ub1 & ub3 met elkaar communiceren, anders niet.g

Cheat sheet:
`ip route add {subnet} via {router}`
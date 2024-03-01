# Spring Boot Basics

Basis repository met wat simpele voorbeelden voor de eerste sprint van Inno.

# Inhoud

Een kleine demo-applicatie, bestaande uit een kleine counter-demo, en een iets uitgebreidere 'winkel'.

# Gebruik

* ``mvn package``, of op een andere wijze het package doel draaien. Dit maakt de te-draaien-jar.
* Nieuwe run configuration aanmaken, environment variable setten op SERVER_PORT: 8082
* ``docker-compose up --build --force-recreate``, dit maakt 2 containers van deze applicatie, en draait ze samen met een loadbalancer
  (``--build --force-recreate`` is technisch gezien de eerste keer niet nodig... maar anders komen code-updates niet betrouwbaar op je nieuwe container)

Vervolgens kun je:
* Naar http://localhost:8090 om de ge-loadbalancede versie te zien
* Naar http://localhost:8091 om altijd naar de eerste container te gaan
* Naar http://localhost:8092 om altijd naar de tweede container te gaan
* Naar http://localhost:9000/stats om de gory details van de loadbalancer te bekijkens

# Demo basicboot notities
- Met een in-memory-cache heb je al 90% van de Distributed-Systems ellende
- localhost:8080/counter/increment --> counter opvragen werkt 
- 8090 = HAproxy
- localhost:9000/stats = admin interface

- Ondanks singleton nog steeds veranderen, want twee instanties van programma
- Docker compose: dropt database, ook bij 2e instantie
- Laten zien met zowel counter als products dat caching moeilijk is ook al werk je maar met 2 instanties en 1 cache


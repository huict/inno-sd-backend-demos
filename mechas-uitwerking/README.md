# Pom Splitten met Grote Robots

Mechas zijn grote (vooral japanse) [robots](https://en.wikipedia.org/wiki/Mecha).

In deze oefening gaan we een monolithische applicatie opdelen in 2 losse services, en zorgen we voor distribution transparancy
door er een gateway voor te plaatsen.


Stappen:

1. Maak 2 nieuwe modules aan
   Dit kan tegenwoordig best goed in IntelliJ door te rechtsklikken op de pom-splitting directory en daar een new->module
   aan te maken. Denk er dan aan dat je een Maven module maakt, en de juiste parent-pom kiest.
   (1.5 jaar terug moest je hiervoor nog alles eerst buiten IntelliJ met een tekst-editor tweaken!)
2. Split de applicatie in die modules
   Dat betekent dat je sommige classes in z'n geheel kan verplaatsen, maar anderen zul je moeten kopieëren/aanpassen.
3. Zet er een Gateway voor. 
   Een Gateway is een derde applicatie die alle requests opvangt en ze doorzet naar de juiste service.
   Is een Gateway altijd nodig/handig?



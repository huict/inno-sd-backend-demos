# Bad aggregate demo
gaat over persistence ignorence en scope van entities

http://localhost:8080/registration/test/questions/?page=2&lang=nl
Stappen:
1) nl taal in query parameter
2) engelse taal
3) franse taal

Observatie: engelse/franse taal blijft staan

Probleem: behandelen van eniteiten als DTO. Entities zijn altijd verbonden met de db, dus elke wijziging kan de db inkomen.
Alle wijzigingen op entiteit  komen de db in (contract met db JPA)

Wanneer questioncontroller L:30 regel uitgecomment wordt: dan wordt de service methode niet meer aangeroepen
Deze servicemethode heeft @transactional dus entiteit wordt daar naar db opgeslagen
Deze bug/incorrecte code is zichtbaar omdat de getquestions muteert de entity question, niet de DTO.
Bug daarbovenop is "geen transactional" in service, dus ook geen db flush

DDD belangrijk want dan weet wanneer een entiteit consistent (wanneer altijd consistent, dan maakt per ongeluk naar db schrijven niet uit)
Persistency ignorance = het object is ignorant van zijn eigen persistentie. Persistence context houd map van object id's bij en bij verlaten van methode wordt de flush aangeroepen

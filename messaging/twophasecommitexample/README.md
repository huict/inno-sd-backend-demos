# Cross-datastore-commits

We praten vaak over CAP & Gedistribueerde Transacties alsof alleen heeeeule complexe systemen daar last van hebben.

Maar zelfs in onze kleine systeempjes hebben we:
* Één of meerdere service processen
* Een database proces
* Een message-broker (RabbitMQ) proces

En dat is al ruim voldoende om de problemen te ervaren.

Dit is qua messaging eigenlijk geen demo van 2pc, maar van 'het belang' van 2pc. Het probleem is namelijk dat technisch 
gezien RabbitMQ geen support heeft voor gedistribueerde transacties. Dus de @Transactional in deze demo is een
'best effort' één phase transactie. We gooien op goed geluk de commits eruit. 

En dat werkt redelijk zolang de infra werkt. Het is hierbij wel belangrijk dat -alle- commits samen op het eind gebeuren,
zonder logica er tussen. Dat wordt in deze demo geregeld door @Transactional.

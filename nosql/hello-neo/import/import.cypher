MATCH (n)
DETACH DELETE n;

load csv with headers from 'file:///klant.csv' as line
create (:Klant { klantId: toInteger(line.klantnr), name: line.naam  });

load csv with headers from 'file:///artikel.csv' as line
create (:Artikel { artikelId: toInteger(line.artnr), name: line.naam, price: line.prijs  });

load csv with headers from 'file:///bestelling.csv' as line
match (k:Klant { klantId: toInteger(line.klantnr) })
merge (o:Bestelling { bestellingId: toInteger(line.bestnr), datum: line.datum })
merge (k)-[:ORDERS]->(o);

load csv with headers from 'file:///besteldartikel.csv' as line
match (a:Artikel { artikelId: toInteger(line.artnr) })
match (b:Bestelling { bestellingId: toInteger(line.bestnr) })
merge (b)-[:CONTAINS { aantal: toInteger(line.aantal), prijs: line.bestelprijs } ]->(a);



/*
match(k:Klant)-->(:Bestelling)-->(:Artikel { artikelId: 124})
match(k)-->(:Bestelling)-->(oa:Artikel) where oa.artikelId <> 124
return oa;
*/
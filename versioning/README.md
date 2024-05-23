# Versioning

Versioning-problemen komen op een aantal vervelende momenten terug.

Stel je hebt bijv. een nette highly-available oplossing voor je service (de HaProxy uit `docker-compose.yml`).

Een aantal problemen die we zouden willen oplossen:
1) FirstName & LastName => GivenName & FamilyName
   * Via DTO, of ook in de database?
2) ProductId & ProductName zijn beiden verplicht in de POST, dat klinkt niet handig

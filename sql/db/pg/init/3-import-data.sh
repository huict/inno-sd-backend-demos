#!/bin/bash
set -e

echo 'Dropping Previous Import'
psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" --dbname tennis -f /raw/0-drop-previous.sql
echo 'Dropped Previous Import'

echo 'Importing Players'
psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" --dbname tennis -f /raw/1-import-players.sql
echo 'Imported Players'

echo 'Importing Singles'
psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" --dbname tennis -f /raw/2-import-singles.sql
echo 'Imported Singles'

echo 'Importing Doubles'
psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" --dbname tennis -f /raw/2-import-doubles.sql
echo 'Imported Doubles'

echo 'Generating Additional Schema'
psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" --dbname tennis -f /raw/3-generate-schema.sql
echo 'Generated Additional Schema'


#echo 'Importing Rankings' # Duurt te lang, niet interessant genoeg
#psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" --dbname tennis -f /raw/2-import-rankings.sql
#echo 'Imported Rankings'

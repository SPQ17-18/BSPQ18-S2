/* DELETE 'nucleus' database*/
DROP SCHEMA IF EXISTS bibliotecadb;
/* DELETE USER 'spq' AT LOCAL SERVER*/
DROP USER IF EXISTS 'spq'@'%';

/* CREATE 'nucleus' DATABASE */
CREATE SCHEMA bibliotecadb;
/* CREATE THE USER 'spq' AT LOCAL SERVER WITH PASSWORD 'spq' */
CREATE USER IF NOT EXISTS 'spq'@'%' IDENTIFIED BY 'spq';

GRANT ALL ON bibliotecadb.* TO 'spq'@'%';
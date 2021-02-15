-- Script de création de la base de données BDD_ENCHERES
-- type : SQL Server



CREATE TABLE CATEGORIES (
    no_categorie   		INTEGER IDENTITY(1,1) NOT NULL,
    libelle        		VARCHAR(30) NOT NULL
)
ALTER TABLE CATEGORIES ADD CONSTRAINT categorie_pk PRIMARY KEY (no_categorie)



CREATE TABLE UTILISATEURS (
    no_utilisateur		INTEGER IDENTITY(1,1) NOT NULL,
    pseudo           	VARCHAR(30) NOT NULL UNIQUE,
    nom              	VARCHAR(30) NOT NULL,
    prenom           	VARCHAR(30) NOT NULL,
    email            	VARCHAR(50) NOT NULL UNIQUE,
    telephone        	VARCHAR(15),
    rue              	VARCHAR(50) NOT NULL,
    code_postal      	VARCHAR(10) NOT NULL,
    ville            	VARCHAR(30) NOT NULL,
    mot_de_passe     	VARCHAR(30) NOT NULL,
    credit           	INTEGER NOT NULL,
    administrateur   	bit NOT NULL
)
ALTER TABLE UTILISATEURS ADD CONSTRAINT utilisateur_pk PRIMARY KEY (no_utilisateur)



CREATE TABLE ARTICLES_VENDUS (
    no_article			INTEGER IDENTITY(1,1) NOT NULL,
    nom_article			VARCHAR(30) NOT NULL,
    description			VARCHAR(300) NOT NULL,
	date_debut_encheres	DATE NOT NULL,
    date_fin_encheres	DATE NOT NULL,
    prix_initial		INTEGER,
    prix_vente			INTEGER,
    etat_vente			VARCHAR(15) NOT NULL,
    no_utilisateur		INTEGER NOT NULL,
    no_categorie		INTEGER NOT NULL
)
ALTER TABLE ARTICLES_VENDUS ADD CONSTRAINT article_vendu_pk PRIMARY KEY (no_article)

ALTER TABLE ARTICLES_VENDUS ADD CONSTRAINT articles_vendus_categories_fk FOREIGN KEY (no_categorie) REFERENCES CATEGORIES (no_categorie)
ON DELETE NO ACTION 
ON UPDATE NO ACTION 

ALTER TABLE ARTICLES_VENDUS ADD CONSTRAINT ventes_utilisateur_fk FOREIGN KEY (no_utilisateur) REFERENCES UTILISATEURS (no_utilisateur)
ON DELETE NO ACTION 
ON UPDATE NO ACTION 



CREATE TABLE IMAGES (
    no_image	   		INTEGER IDENTITY(1,1) NOT NULL,
    chemin_url     		VARCHAR(200) NOT NULL,
    no_article       	INTEGER NOT NULL
)
ALTER TABLE IMAGES ADD CONSTRAINT image_pk PRIMARY KEY (no_image)

ALTER TABLE IMAGES ADD CONSTRAINT images_article_fk FOREIGN KEY (no_article) REFERENCES ARTICLES_VENDUS (no_article)
ON DELETE NO ACTION 
ON UPDATE NO ACTION



CREATE TABLE RETRAITS (
	no_article       	INTEGER NOT NULL,
    rue              	VARCHAR(50) NOT NULL,
    code_postal      	VARCHAR(15) NOT NULL,
    ville            	VARCHAR(30) NOT NULL
)
ALTER TABLE RETRAITS ADD CONSTRAINT retrait_pk PRIMARY KEY  (no_article)

ALTER TABLE RETRAITS ADD CONSTRAINT retrait_article_fk FOREIGN KEY (no_article) REFERENCES ARTICLES_VENDUS (no_article)
ON DELETE NO ACTION 
ON UPDATE NO ACTION



CREATE TABLE ENCHERES(	
	no_enchere  		INTEGER IDENTITY(1,1) NOT NULL,
	date_enchere 		datetime NOT NULL,
	montant_enchere 	INTEGER NOT NULL,
	no_article 			INTEGER NOT NULL,
	no_utilisateur 		INTEGER NOT NULL
 )
ALTER TABLE ENCHERES ADD CONSTRAINT enchere_pk PRIMARY KEY (no_enchere)

ALTER TABLE ENCHERES ADD CONSTRAINT encheres_utilisateur_fk FOREIGN KEY (no_utilisateur) REFERENCES UTILISATEURS (no_utilisateur)
ON DELETE NO ACTION 
ON UPDATE NO ACTION  

ALTER TABLE ENCHERES ADD CONSTRAINT encheres_no_article_fk FOREIGN KEY (no_article) REFERENCES ARTICLES_VENDUS (no_article)
ON DELETE NO ACTION 
ON UPDATE NO ACTION  

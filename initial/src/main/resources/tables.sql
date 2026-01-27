-- ----------------------------------------------------------
-- Script POSTGRESQL pour mcd 
-- ----------------------------------------------------------


-- ----------------------------
-- Table: membre
-- ----------------------------
CREATE TABLE membre (
  id UUID NOT NULL,
  nom VARCHAR(50) NOT NULL,
  initiales VARCHAR(50) NOT NULL,
  email VARCHAR(50) NOT NULL,
  telephone VARCHAR(20) NOT NULL,
  centre_interet VARCHAR(100),
  delegue_principal VARCHAR(50) NOT NULL,
  delegue_adjoint1 VARCHAR(50),
  delegue_adjoint2 VARCHAR(50),
  delegue_adjoint3 VARCHAR(50),
  CONSTRAINT membre_PK PRIMARY KEY (id)
);


-- ----------------------------
-- Table: adherent
-- ----------------------------
CREATE TABLE adherent (
  id UUID NOT NULL,
  nom VARCHAR(50) NOT NULL,
  prenom VARCHAR(50) NOT NULL,
  email VARCHAR(50) NOT NULL,
  telephone VARCHAR(20) NOT NULL,
  password VARCHAR(200) NOT NULL,
  role VARCHAR(20) NOT NULL,
  is_active BOOLEAN NOT NULL,
  CONSTRAINT adherent_PK PRIMARY KEY (id)
);


-- ----------------------------
-- Table: adresse
-- ----------------------------
CREATE TABLE adresse (
  id UUID NOT NULL,
  numero_rue VARCHAR(10) NOT NULL,
  rue VARCHAR(100) NOT NULL,
  code_postal INTEGER NOT NULL,
  ville VARCHAR(50) NOT NULL,
  complement VARCHAR(100),
  CONSTRAINT adresse_PK PRIMARY KEY (id)
);


-- ----------------------------
-- Table: document
-- ----------------------------
CREATE TABLE document (
  id UUID NOT NULL,
  nom VARCHAR(50) NOT NULL,
  type VARCHAR(50) NOT NULL,
  fichier BYTEA NOT NULL,
  id_adherent UUID NOT NULL,
  CONSTRAINT document_PK PRIMARY KEY (id),
  CONSTRAINT document_id_adherent_FK FOREIGN KEY (id_adherent) REFERENCES adherent (id)
);


-- ----------------------------
-- Table: appartenir
-- ----------------------------
CREATE TABLE appartenir (
  id_membre UUID NOT NULL,
  id_adherent UUID NOT NULL,
  CONSTRAINT appartenir_PK PRIMARY KEY (id_membre, id_adherent),
  CONSTRAINT appartenir_id_FK FOREIGN KEY (id_membre) REFERENCES membre (id),
  CONSTRAINT appartenir_id_adherent_FK FOREIGN KEY (id_adherent) REFERENCES adherent (id)
);
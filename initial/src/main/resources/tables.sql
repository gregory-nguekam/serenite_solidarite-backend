CREATE EXTENSION IF NOT EXISTS "pgcrypto";

CREATE TABLE IF NOT EXISTS tb_membre
(
    id            UUID PRIMARY KEY      DEFAULT gen_random_uuid(),
    type_membre   VARCHAR(30)  NOT NULL,

    nom           VARCHAR(200) NOT NULL,
    initiales     VARCHAR(20),
    email         VARCHAR(255),
    telephone     VARCHAR(30),

    -- carence
    carence_jours INTEGER      DEFAULT 180

);

CREATE INDEX IF NOT EXISTS idx_tb_membre_type ON tb_membre (type_membre);
CREATE INDEX IF NOT EXISTS idx_tb_membre_nom ON tb_membre (nom);


-- 2) Table tb_adherent : utilisateur de l'application (login)
CREATE TABLE IF NOT EXISTS tb_adherent
(
    id               UUID PRIMARY KEY      DEFAULT gen_random_uuid(),

    membre_id        UUID         NOT NULL REFERENCES tb_membre (id) ON DELETE RESTRICT,

    -- au lieu d'un enum PostgreSQL => VARCHAR
    -- valeurs attendues: 'M', 'MME', 'MLLE', 'AUTRE'
    civilite         VARCHAR(10),

    nom              VARCHAR(120) NOT NULL,
    prenom           VARCHAR(120) NOT NULL,

    email            VARCHAR(255) NOT NULL UNIQUE,
    telephone        VARCHAR(30),

    login            VARCHAR(80) UNIQUE,
    password_hash    VARCHAR(255) NOT NULL,

    role             VARCHAR(30)  NOT NULL DEFAULT 'ADHERENT',

    date_inscription DATE         DEFAULT CURRENT_DATE,
    carence_fin      DATE,
    actif            BOOLEAN      DEFAULT TRUE,

    rgpd_consent     BOOLEAN      DEFAULT FALSE,
    rgpd_consent_at  TIMESTAMPTZ
);

CREATE INDEX IF NOT EXISTS idx_tb_adherent_membre ON tb_adherent (membre_id);
CREATE INDEX IF NOT EXISTS idx_tb_adherent_email ON tb_adherent (email);

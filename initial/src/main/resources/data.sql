-- 1) Créer un membre "Association"
INSERT INTO tb_membre(type_membre, nom, initiales, email, telephone)
VALUES ('ASSOCIATION', 'Fédération Nde de France', 'FNDF', 'contact@example.org', '+33600000000');

-- 2) Créer un adhérent rattaché au membre
-- IMPORTANT: password_hash doit venir de Spring (BCrypt).
-- Pour un test pure SQL (pas recommandé), tu peux mettre une valeur factice, puis tu changes via backend.
INSERT INTO tb_adherent(membre_id, nom, prenom, email, password_hash, rgpd_consent, rgpd_consent_at)
SELECT id, 'Nguekam', 'Gregory', 'greg@example.com', '$2a$10$REPLACE_ME_BCRYPT_HASH', true, now()
FROM tb_membre
WHERE nom = 'Fédération Nde de France';

INSERT INTO membre (id, nom, initiales, type, email, telephone, centre_interet, delegue_principal, delegue_adjoint1, delegue_adjoint2, delegue_adjoint3)
VALUES
  ('11111111-1111-1111-1111-111111111111', 'Association A', 'ASSO-A', 'ASSOCIATION', 'asso-a@example.com', '0600000001', 'Solidarite', 'Alice Delegue', 'Adjoint A1', 'Adjoint A2', 'Adjoint A3'),
  ('22222222-2222-2222-2222-222222222222', 'Groupe B', 'GRP-B', 'GROUPE', 'groupe-b@example.com', '0600000002', 'Entraide', 'Bruno Delegue', 'Adjoint B1', 'Adjoint B2', 'Adjoint B3'),
  ('33333333-3333-3333-3333-333333333333', 'Famille C', 'FAM-C', 'FAMILLE', 'famille-c@example.com', '0600000003', 'Soutien', 'Claire Delegue', 'Adjoint C1', 'Adjoint C2', 'Adjoint C3')
ON CONFLICT (id) DO NOTHING;

INSERT INTO adherent (id, nom, prenom, email, telephone, password, role, is_active, is_validated)
VALUES
  ('aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa', 'Durand', 'Julie', 'julie.durand@example.com', '0610000001', '$2a$10$gl.DuQmb5ldf6iC278hBoOBlZakWqnm4ceovAdI2SZCGZFlGfSHmy', 'ADHERENT', true, true),
  ('bbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbbb', 'Martin', 'Lucas', 'lucas.martin@example.com', '0610000002', '$2a$10$gl.DuQmb5ldf6iC278hBoOBlZakWqnm4ceovAdI2SZCGZFlGfSHmy', 'ADHERENT', true, false),
  ('cccccccc-cccc-cccc-cccc-cccccccccccc', 'Bernard', 'Sofia', 'sofia.bernard@example.com', '0610000003', '$2a$10$gl.DuQmb5ldf6iC278hBoOBlZakWqnm4ceovAdI2SZCGZFlGfSHmy', 'ADHERENT', true, true),
  ('dddddddd-dddd-dddd-dddd-dddddddddddd', 'Petit', 'Nadia', 'nadia.petit@example.com', '0610000004', '$2a$10$gl.DuQmb5ldf6iC278hBoOBlZakWqnm4ceovAdI2SZCGZFlGfSHmy', 'ADHERENT', false, true),
  ('eeeeeeee-eeee-eeee-eeee-eeeeeeeeeeee', 'Robert', 'Amine', 'amine.robert@example.com', '0610000005', '$2a$10$gl.DuQmb5ldf6iC278hBoOBlZakWqnm4ceovAdI2SZCGZFlGfSHmy', 'ADHERENT', true, false)
ON CONFLICT (id) DO NOTHING;

INSERT INTO appartenir (id_membre, id_adherent)
VALUES
  ('11111111-1111-1111-1111-111111111111', 'aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa'),
  ('11111111-1111-1111-1111-111111111111', 'bbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbbb'),
  ('22222222-2222-2222-2222-222222222222', 'cccccccc-cccc-cccc-cccc-cccccccccccc'),
  ('33333333-3333-3333-3333-333333333333', 'dddddddd-dddd-dddd-dddd-dddddddddddd'),
  ('33333333-3333-3333-3333-333333333333', 'eeeeeeee-eeee-eeee-eeee-eeeeeeeeeeee')
ON CONFLICT (id_membre, id_adherent) DO NOTHING;

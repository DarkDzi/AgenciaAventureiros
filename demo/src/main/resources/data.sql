-- ============================================================
-- SCHEMA: aventura
-- ============================================================

-- Aventureiros
INSERT INTO aventura.aventureiro (organizacao_id, usuario_id, nome, classe, nivel, status, created_at, updated_at)
VALUES (1, 1, 'Aldric', 'GUERREIRO', 10, 'ATIVO', NOW(), NOW())
ON CONFLICT DO NOTHING;

INSERT INTO aventura.aventureiro (organizacao_id, usuario_id, nome, classe, nivel, status, created_at, updated_at)
VALUES (1, 1, 'Lyra', 'MAGO', 8, 'ATIVO', NOW(), NOW())
ON CONFLICT DO NOTHING;

INSERT INTO aventura.aventureiro (organizacao_id, usuario_id, nome, classe, nivel, status, created_at, updated_at)
VALUES (1, 1, 'Theron', 'ARQUEIRO', 5, 'ATIVO', NOW(), NOW())
ON CONFLICT DO NOTHING;

-- Companheiro para Aldric (id=1)
INSERT INTO aventura.companheiro (aventureiro_id, nome, especie, lealdade)
VALUES (1, 'Rex', 'LOBO', 85)
ON CONFLICT DO NOTHING;

UPDATE aventura.aventureiro SET companheiro_id = 1 WHERE id = 1;

-- Missões
INSERT INTO aventura.missao (organizacao_id, titulo, nivel_perigo, status, created_at, iniciada_em, terminada_em)
VALUES (1, 'Extermínio do Dragão', 'ALTO', 'CONCLUIDA', NOW(), NOW(), NOW())
ON CONFLICT DO NOTHING;

INSERT INTO aventura.missao (organizacao_id, titulo, nivel_perigo, status, created_at, iniciada_em, terminada_em)
VALUES (1, 'Resgate na Floresta', 'MEDIO', 'EM_ANDAMENTO', NOW(), NOW(), NULL)
ON CONFLICT DO NOTHING;

INSERT INTO aventura.missao (organizacao_id, titulo, nivel_perigo, status, created_at, iniciada_em, terminada_em)
VALUES (1, 'Patrulha das Ruínas', 'BAIXO', 'PLANEJADA', NOW(), NULL, NULL)
ON CONFLICT DO NOTHING;

-- Participações
INSERT INTO aventura.participacao_missao (missao_id, aventureiro_id, papel, recompensa_ouro, mvp, created_at)
VALUES (1, 1, 'LIDER',     500, true,  NOW())
ON CONFLICT DO NOTHING;

INSERT INTO aventura.participacao_missao (missao_id, aventureiro_id, papel, recompensa_ouro, mvp, created_at)
VALUES (1, 2, 'COMBATENTE', 300, false, NOW())
ON CONFLICT DO NOTHING;

INSERT INTO aventura.participacao_missao (missao_id, aventureiro_id, papel, recompensa_ouro, mvp, created_at)
VALUES (2, 1, 'EXPLORADOR',   0, false, NOW())
ON CONFLICT DO NOTHING;

INSERT INTO aventura.participacao_missao (missao_id, aventureiro_id, papel, recompensa_ouro, mvp, created_at)
VALUES (2, 3, 'SUPORTE',    150, false, NOW())
ON CONFLICT DO NOTHING;

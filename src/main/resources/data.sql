INSERT INTO events (id, title, details, slug, maximum_attendees)
VALUES ('7b869ea0-fc05-4c3c-b965-7d1ca75a0a7f', 'Evento - Solana - Cesar', 'Evento Cesar', 'evento-solana-cesar',300);

INSERT INTO attendees (name, email, event_id, created_at)
VALUES
    ('João Silva', 'joao.silva@gmail.com', '7b869ea0-fc05-4c3c-b965-7d1ca75a0a7f', CURRENT_TIMESTAMP),
    ('Maria Santos', 'maria.santos@gmail.com', '7b869ea0-fc05-4c3c-b965-7d1ca75a0a7f', CURRENT_TIMESTAMP),
    ('Pedro Oliveira', 'pedro.oliveira@gmail.com', '7b869ea0-fc05-4c3c-b965-7d1ca75a0a7f', CURRENT_TIMESTAMP),
    ('Ana Souza', 'ana.souza@gmail.com', '7b869ea0-fc05-4c3c-b965-7d1ca75a0a7f', CURRENT_TIMESTAMP),
    ('Carlos Mendes', 'carlos.mendes@gmail.com', '7b869ea0-fc05-4c3c-b965-7d1ca75a0a7f', CURRENT_TIMESTAMP),
    ('Juliana Costa', 'juliana.costa@gmail.com', '7b869ea0-fc05-4c3c-b965-7d1ca75a0a7f', CURRENT_TIMESTAMP),
    ('Lucas Almeida', 'lucas.almeida@gmail.com', '7b869ea0-fc05-4c3c-b965-7d1ca75a0a7f', CURRENT_TIMESTAMP),
    ('Priscila Lima', 'priscila.lima@gmail.com', '7b869ea0-fc05-4c3c-b965-7d1ca75a0a7f', CURRENT_TIMESTAMP),
    ('Fernanda Lima', 'fernanda.lima@gmail.com', '7b869ea0-fc05-4c3c-b965-7d1ca75a0a7f', CURRENT_TIMESTAMP),
    ('Gustavo Pereira', 'gustavo.pereira@gmail.com', '7b869ea0-fc05-4c3c-b965-7d1ca75a0a7f', CURRENT_TIMESTAMP),
    ('Camila Santos', 'camila.santos@gmail.com', '7b869ea0-fc05-4c3c-b965-7d1ca75a0a7f', CURRENT_TIMESTAMP),
    ('Rafaela Oliveira', 'rafaela.oliveira@gmail.com', '7b869ea0-fc05-4c3c-b965-7d1ca75a0a7f', CURRENT_TIMESTAMP),
    ('Leonardo Costa', 'leonardo.costa@gmail.com', '7b869ea0-fc05-4c3c-b965-7d1ca75a0a7f', CURRENT_TIMESTAMP),
    ('Patricia Silva', 'patricia.silva@gmail.com', '7b869ea0-fc05-4c3c-b965-7d1ca75a0a7f', CURRENT_TIMESTAMP),
    ('Daniel Fernandes', 'daniel.fernandes@gmail.com', '7b869ea0-fc05-4c3c-b965-7d1ca75a0a7f', CURRENT_TIMESTAMP),
    ('Tatiane Pereira', 'tatiane.pereira@gmail.com', '7b869ea0-fc05-4c3c-b965-7d1ca75a0a7f', CURRENT_TIMESTAMP),
    ('Roberto Almeida', 'roberto.almeida@gmail.com', '7b869ea0-fc05-4c3c-b965-7d1ca75a0a7f', CURRENT_TIMESTAMP);
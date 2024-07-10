INSERT INTO company (name) VALUES
   ('Company A'),
   ('Company B'),
   ('Company C'),
   ('Company D'),
   ('Company E'),
   ('Company F');

INSERT INTO company_locales (company_id, lang, description) VALUES
    (1, 'en', 'Company A is a leading provider of software solutions'),
    (1, 'fr', 'La compagnie A est un leader dans la fourniture de solutions logicielles'),
    (2, 'en', 'Company B specializes in e-commerce platforms'),
    (2, 'fr', 'La compagnie B se spécialise dans les plateformes de commerce électronique'),
    (3, 'en', 'Company C offers consulting services for startups and small businesses'),
    (3, 'fr', 'La compagnie C offre des services de conseil aux start-ups et aux petites entreprises'),
    (4, 'en', 'Company D is a global provider of cloud computing services'),
    (4, 'fr', 'La compagnie D est un fournisseur mondial de services de cloud computing'),
    (5, 'en', 'Company E provides cybersecurity solutions for enterprises'),
    (5, 'fr', 'La compagnie E propose des solutions de cybersécurité pour les entreprises'),
    (6, 'en', 'Company F offers AI-driven marketing solutions'),
    (6, 'fr', 'La compagnie F propose des solutions marketing basées sur');

INSERT INTO users (username, birth_date, firstname, lastname, role, company_id) VALUES
    ('user1', '1990-05-15', 'John', 'Doe', 'developer', 1),
    ('user2', '1985-08-20', 'Jane', 'Smith', 'manager', 2),
    ('user3', '1992-03-10', 'Mike', 'Johnson', 'analyst', 3),
    ('user4', '1988-11-25', 'Emily', 'Brown', 'designer', 4),
    ('user5', '1995-02-28', 'Chris', 'Wilson', 'engineer', 5),
    ('user6', '1993-07-12', 'Sarah', 'Lee', 'consultant', 6);

INSERT INTO payment (amount, receiver_id) VALUES
    (100, 1),
    (150, 2),
    (200, 3),
    (120, 4),
    (180, 5),
    (250, 6);

INSERT INTO chat (name) VALUES
    ('General Chat'),
    ('Team Chat'),
    ('Project Chat'),
    ('Social Chat'),
    ('Tech Chat'),
    ('Support Chat');

INSERT INTO users_chat (user_id, chat_id) VALUES
    (1, 1),
    (2, 2),
    (3, 3),
    (4, 4),
    (5, 5),
    (6, 6);

COMMIT;
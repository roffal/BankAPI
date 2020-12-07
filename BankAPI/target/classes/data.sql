INSERT INTO clients(name, surname, birthday)
VALUES
    ('Michael', 'Smith', '20001012'),
    ('Will', 'Parry', '19901119'),
    ('Lira', 'Belaqua', '19910108'),
    ('Marissa', 'Colter', '19880926'),
    ('Asriel', 'Belaqua', '19620604'),
    ('Seraphima', 'Pekkala', '19670601'),
    ('Lee', 'Scorsby', '19721006'),
    ('Roger', 'Cook', '19910912'),
    ('Charles', 'Boreal', '19910116'),
    ('Joe', 'Parry', '19780713');

INSERT INTO accounts(account_number, client_id)
VALUES
    (40817810099910004312, (SELECT id FROM clients where id = 1)),
    (40817810099910004313, (SELECT id FROM clients where id = 4)),
    (40817810099910004314, (SELECT id FROM clients where id = 4)),
    (40817810099910004315, (SELECT id FROM clients where id = 5)),
    (40817810099910004316, (SELECT id FROM clients where id = 6)),
    (40817810099910004317, (SELECT id FROM clients where id = 9)),
    (40817810099910004318, (SELECT id FROM clients where id = 10));

INSERT INTO cards(card_number, account_id)
VALUES
    (4000001234567899, (SELECT id FROM accounts where id = 1)),
    (4000001234567900, (SELECT id FROM accounts where id = 2)),
    (4000001234567901, (SELECT id FROM accounts where id = 3)),
    (4000001234567902, (SELECT id FROM accounts where id = 4)),
    (4000001234567903, (SELECT id FROM accounts where id = 2)),
    (4000001234567904, (SELECT id FROM accounts where id = 5)),
    (4000001234567905, (SELECT id FROM accounts where id = 1));

DROP TABLE IF EXISTS cards, clients, accounts;
CREATE TABLE clients(
id SERIAL NOT NULL PRIMARY KEY,
name VARCHAR(50) NOT NULL,
surname VARCHAR(50) NOT NULL,
birthday DATE
);

CREATE TABLE accounts (
id SERIAL NOT NULL PRIMARY KEY,
account_number DECIMAL ,
balance DECIMAL(15,2) NOT NULL DEFAULT 0,
client_id INT NOT NULL references clients(id)
);

CREATE TABLE cards (
id SERIAL NOT NULL PRIMARY KEY,
card_number BIGINT,
account_id INT NOT NULL references accounts(id)
);
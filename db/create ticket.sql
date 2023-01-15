CREATE TABLE ticket (
 id INT SERIAL PRIMARY KEY,
 session_id INT NOT NULL,
 row INT NOT NULL,
 cell INT NOT NULL,
 account_id INT NOT NULL REFERENCES account(id),
 CONSTRAINT place UNIQUE(session_id, row, cell)
);